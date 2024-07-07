package com.rojas.app.cotizacion.model;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class datosCotizacion {
	@NotEmpty
	@NotNull
	List<producto> productos;
	@NotEmpty
	@NotNull
	Integer idCliente;
}
