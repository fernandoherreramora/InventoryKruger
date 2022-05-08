package com.kruger.app.model;

import io.swagger.annotations.ApiModel;
import lombok.*;

@ApiModel(description = "Modelo generico de respuesta", value = "Response")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Integer code;
    private String message;
    private Object response;
}
