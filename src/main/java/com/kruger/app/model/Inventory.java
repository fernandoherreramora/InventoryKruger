package com.kruger.app.model;

import com.kruger.app.dto.Empleado;
import com.kruger.app.dto.Vacunacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory{

    private Empleado empleado;
    private Vacunacion vacunacion;

}
