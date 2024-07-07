package com.rojas.app.cotizacion.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class credito {
	public List<String> meses;
	public List<String> mensualidad;
}
