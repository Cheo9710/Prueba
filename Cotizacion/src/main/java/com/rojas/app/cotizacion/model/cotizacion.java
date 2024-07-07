package com.rojas.app.cotizacion.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class cotizacion {
	public cliente cliente;
	public String version;
	public String fecha;
	public Double total;
	public Double gastoAdmon;
	public Double enganche;
	public Double iva;
	public String detalles;
	public Double subtotal;
}
