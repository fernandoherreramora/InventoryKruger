package com.kruger.app.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kruger.app.enums.TipoVacuna;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@ApiModel(description = "Modelo de informacion de registro de vacunados", value = "InfoVacuna")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoVacuna {

    private TipoVacuna tipoVacuna;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private LocalDate fechaVacunacion;

    private Integer numeroDosis;
}
