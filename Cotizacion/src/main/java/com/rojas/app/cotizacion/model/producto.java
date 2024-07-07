package com.rojas.app.cotizacion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class producto {
	public String modelo;
	public String tipo;
	public String detalles;
	public double precio;
	
}
