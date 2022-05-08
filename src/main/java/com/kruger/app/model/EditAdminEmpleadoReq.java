package com.kruger.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@ApiModel(description = "Modelo de request para editar informacion del empleado", value = "EditAdminEmpleadoReq")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditAdminEmpleadoReq {
    private String nombres;
    private String apellidos;
    private String correo;
    private String direccionDomicilio;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate fechaNacimiento;
    private String telefono;
}
