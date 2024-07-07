package com.rojas.app.cotizacion.serviceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rojas.app.cotizacion.controller.credito;
import com.rojas.app.cotizacion.dao.clienteDao;
import com.rojas.app.cotizacion.model.cliente;
import com.rojas.app.cotizacion.model.cotizacion;
import com.rojas.app.cotizacion.model.datosCotizacion;
import com.rojas.app.cotizacion.model.producto;
import com.rojas.app.cotizacion.service.service;

@Service
public class serviceImpl implements service{
	@Autowired
	private clienteDao base;
	Map<String,String> parametros = new HashMap<>();
	@Override
	public List<producto> obtenerproductos(String tipo){
		parametros.clear();
		List<producto> resultado = new ArrayList<>();
		String sql = "SELECT * FROM PRODUCTOS WHERE TIPO = ?";
		parametros.put("0", tipo);
		resultado = base.ejecutaQueryObtenerProducto(sql, parametros);
		
		return resultado;
		
	}

	@Override
	public cliente getclientebyid(Integer id) {
		parametros.clear();
		String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
		parametros.put("0", id.toString());		
		return base.ejecutaQueryObtenerClienteId(sql, parametros);
	}

	@Override
	public Integer guardarcliente(cliente body) {
		parametros.clear();
		String sql = "INSERT INTO CLIENTE (NOMBRE, APELLIDOPATERNO, APELLIDOMATERNO, CELULAR,"
				+ " CORREOELECTRONICO) \n"
				+ "VALUES (?,?,?,?,?)";
		parametros.put("0", body.getNombre());
		parametros.put("1", body.getApellidoPaterno());
		parametros.put("2", body.getApellidoMaterno());
		parametros.put("3", body.getCelular());
		parametros.put("4", body.getCorreoElectronico());
		base.ejecutaQueryGuardaCliente(sql, parametros);
		String sql2 = "SELECT ID FROM CLIENTE WHERE NOMBRE = ? AND APELLIDOPATERNO = ? ORDER BY ID DESC";
		parametros.clear();
		parametros.put("0", body.getNombre());
		parametros.put("1", body.getApellidoPaterno());
		Integer rs2= base.ejecutaQueryConsultaIdCelular(sql2, parametros);
		return rs2;		
	}

	@Override
	public credito obtenerCredito(float total) {
		List<String> meses = new ArrayList<>();
		List<String> mensualidad = new ArrayList<>();
		meses.add("24");
		meses.add("36");
		meses.add("48");
		meses.add("60");
		double porcentaje = 0.125;
		double total24 = ((total*porcentaje*24)+total)/24;
		double total36 = ((total*porcentaje*36)+total)/36;
		double total48 = ((total*porcentaje*48)+total)/48;
		double total60 = ((total*porcentaje*60)+total)/60;
		mensualidad.add(String.valueOf(total24));
		mensualidad.add(String.valueOf(total36));
		mensualidad.add(String.valueOf(total48));
		mensualidad.add(String.valueOf(total60));
		return new credito(meses,mensualidad);
	}

	@Override
	public cotizacion obtenerTotal(datosCotizacion body) {
		double totalmoto = 0.0;
		double total = 0.0;
		double iva = 0.0;
		double enganche = 0.0;
		double gastosadmon = 0.0;
		double subtotal = 0.0;
		String detalles = "";
		cliente resultado = null;	
		for(producto x:body.getProductos()) {
			if("MOTO".equals(x.getTipo())) {
				totalmoto = totalmoto + x.getPrecio();
				detalles = detalles + " "+ x.getModelo();
			}
			detalles = detalles + " "+ x.getModelo();
			total = total + x.getPrecio();
		}
		parametros.clear();
		String sql = "SELECT * FROM CLIENTE WHERE ID = ?";
		parametros.put("0", String.valueOf(body.getIdCliente()));
		resultado = base.ejecutaQueryObtenerClienteId(sql, parametros);		
		enganche = ((totalmoto+(totalmoto*0.16))*0.1);
		LocalDate fechaActual = LocalDate.now();
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fechaActual.format(formato);
		gastosadmon = total*(0.10);
		subtotal = total+gastosadmon;
		iva = (subtotal*0.16);
		total= subtotal + iva;				
		parametros.clear();
		String sql2 = "INSERT INTO COTIZACION (IDCLIENTE, VERSION, FECHA, SUBTOTAL,GASTOSADMON, DETALLES, TOTAL)"
				+ "VALUES (?,?,?,?,?,?,?)";
		parametros.put("0", String.valueOf(body.getIdCliente()));
		parametros.put("1", "1.0");
		parametros.put("2", fechaFormateada);
		parametros.put("3", String.valueOf(subtotal));
		parametros.put("4", String.valueOf(gastosadmon));
		parametros.put("5", detalles);
		parametros.put("6", String.valueOf(total));
		base.ejecutaQueryGuardaCotizacion(sql2, parametros);
		return new cotizacion(resultado,"1.0",fechaFormateada,total,gastosadmon,enganche,iva,detalles,subtotal);
	}
	
}
