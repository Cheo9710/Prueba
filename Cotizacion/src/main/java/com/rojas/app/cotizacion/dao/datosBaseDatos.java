package com.rojas.app.cotizacion.dao;

import java.util.List;
import java.util.Map;

import com.rojas.app.cotizacion.model.cliente;
import com.rojas.app.cotizacion.model.producto;

public interface datosBaseDatos {
	 public List<producto> ejecutaQueryObtenerProducto(String sql,Map<String,String>parametros);
	 public cliente ejecutaQueryObtenerClienteId(String sql,Map<String,String>parametros);
	 public void ejecutaQueryGuardaCliente(String sql,Map<String,String>parametros);
	 public Integer ejecutaQueryConsultaIdCelular(String sql,Map<String,String>parametros);
	 public void ejecutaQueryGuardaCotizacion(String sql,Map<String,String>parametros);
}
