package com.rojas.app.cotizacion.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class cliente {
	@NotEmpty
	@NotNull
	@Pattern(regexp="[a-z,A-Z,ñ,Ñ]{20}")
	public String nombre;
	@NotEmpty
	@NotNull
	@Pattern(regexp="[a-z,A-Z,ñ,Ñ]{50}")
	public String apellidoPaterno;
	public String apellidoMaterno;
	@NotEmpty
	@NotNull
	@Pattern(regexp="\\d{9}")
	public String celular;
	@Pattern(regexp="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
	public String correoElectronico;
}
