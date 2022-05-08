package com.kruger.app.controller;

import com.kruger.app.model.EditAdminEmpleadoReq;
import com.kruger.app.model.EmpleadoReq;
import com.kruger.app.model.FilterEmpleadoReq;
import org.springframework.http.ResponseEntity;


public interface IInventoryAdminController {

    ResponseEntity<?> registrarEmpleado(EmpleadoReq request);
    ResponseEntity<?> editarEmpleado(Long id, EditAdminEmpleadoReq request);
    ResponseEntity<?> listarEmpleado();
    ResponseEntity<?> eliminarEmpleado(Long id);
    ResponseEntity<?> filterEmpleado(FilterEmpleadoReq request);

}
