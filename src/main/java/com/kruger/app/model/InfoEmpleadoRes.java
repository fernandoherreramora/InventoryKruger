package com.kruger.app.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(description = "Modelo de respuesta de Informacion del Empleado", value = "InfoEmpleadoRes")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class InfoEmpleadoRes extends InfoEmpleadoReq {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String correo;
    private String usuario;

}
