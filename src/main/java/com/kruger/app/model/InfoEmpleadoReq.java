package com.kruger.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kruger.app.enums.EstadoVacuna;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@ApiModel(description = "Modelo de request de informacion de empleado", value = "InfoEmpleadoReq")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoEmpleadoReq {

    @ApiModelProperty(required = true)
    @NotNull(message = "Parametro fechaNacimiento no debe ser null")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDate fechaNacimiento;

    @ApiModelProperty(required = true)
    @NotNull(message = "Parametro direccionDomicilio no debe ser null")
    private String direccionDomicilio;

    @ApiModelProperty(required = true)
    @NotNull(message = "Parametro telefono no debe ser null")
    private String telefono;

    @ApiModelProperty(required = true)
    @NotNull(message = "Parametro estadoVacuna no debe ser null")
    private EstadoVacuna estadoVacuna;

    private InfoVacuna infoVacuna;
}
