package com.rojas.app.cotizacion.service;

import java.util.List;

import com.rojas.app.cotizacion.controller.credito;
import com.rojas.app.cotizacion.model.cliente;
import com.rojas.app.cotizacion.model.cotizacion;
import com.rojas.app.cotizacion.model.datosCotizacion;
import com.rojas.app.cotizacion.model.producto;

public interface service {
	public List<producto> obtenerproductos(String tipo);
	public cliente getclientebyid(Integer id);
	public Integer guardarcliente(cliente body);
	public cotizacion obtenerTotal(datosCotizacion body);
	public credito obtenerCredito(float total);
}
