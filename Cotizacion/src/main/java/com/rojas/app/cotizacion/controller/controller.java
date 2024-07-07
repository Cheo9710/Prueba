package com.rojas.app.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rojas.app.cotizacion.model.cliente;
import com.rojas.app.cotizacion.model.cotizacion;
import com.rojas.app.cotizacion.model.datosCotizacion;
import com.rojas.app.cotizacion.model.producto;
import com.rojas.app.cotizacion.service.service;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins="*")
public class controller {
	@Autowired
	private service ser;
	@GetMapping("/productos")
	public List<producto> productos(@RequestParam String tipos) {
		return ser.obtenerproductos(tipos);
	}
	
	@GetMapping("/clientes/{id}")
	public cliente obtClientes(@PathVariable Integer id) {
		return ser.getclientebyid(id);
	}
	@PostMapping("/guardar/cliente")
	public Integer clientes(@RequestBody cliente body) {
		return ser.guardarcliente(body);
	}
	@PostMapping("/totales")
	public cotizacion obtTotal(@RequestBody datosCotizacion body) {
		return ser.obtenerTotal(body);
	}
	@PostMapping("/creditos")
	public credito obtCredito(@RequestParam float total) {
		return ser.obtenerCredito(total);
	}
}
