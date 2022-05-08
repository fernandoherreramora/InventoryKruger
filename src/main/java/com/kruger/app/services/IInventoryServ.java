package com.kruger.app.services;

import com.kruger.app.dto.Usuario;
import com.kruger.app.enums.EstadoVacuna;
import com.kruger.app.enums.TipoVacuna;
import com.kruger.app.model.*;
import org.springframework.http.ResponseEntity;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public interface IInventoryServ {

    ResponseEntity<Response> guardarEmpleado(EmpleadoReq request);

    ResponseEntity<Response> listarEmpleados();

    ResponseEntity<Response> editarEmpleado(Long id, EditAdminEmpleadoReq request);

    ResponseEntity<Response> eliminarEmpleado(Long id);

    Usuario generaDatosAuth(String apellidos, String nombres, String dni) throws NoSuchPaddingException, NoSuchAlgorithmException;

    ResponseEntity<Response> getInfoEmpleado(String user);

    ResponseEntity<Response> updateInfoEmpleado(String user, InfoEmpleadoReq request);

    ResponseEntity<Response> filterEmpleado(FilterEmpleadoReq request);

}
