package com.kruger.app.controller;

import com.kruger.app.model.InfoEmpleadoReq;
import com.kruger.app.model.Response;
import org.springframework.http.ResponseEntity;

public interface IInventoryEmpleadoController {

    ResponseEntity<Response> getInfo(String user);
    ResponseEntity<Response> updateInfo(String user, InfoEmpleadoReq request);

}
