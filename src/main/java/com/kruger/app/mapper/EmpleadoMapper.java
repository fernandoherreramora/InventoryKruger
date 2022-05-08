package com.kruger.app.mapper;

import com.kruger.app.dto.Empleado;
import com.kruger.app.dto.Usuario;
import com.kruger.app.dto.Vacunacion;
import com.kruger.app.model.EmpleadoReq;
import com.kruger.app.model.InfoEmpleadoRes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    Empleado empleadoReqToEmpleado(EmpleadoReq empleadoReq);

    @Mapping(source = "empleado.id", target = "id")
    @Mapping(source = "usuario.usuario", target = "usuario")
    @Mapping(source = "empleado.dni", target = "dni")
    @Mapping(source = "empleado.nombres", target = "nombres")
    @Mapping(source = "empleado.apellidos", target = "apellidos")
    @Mapping(source = "empleado.correo", target = "correo")
    @Mapping(source = "empleado.fechaNacimiento", target = "fechaNacimiento")
    @Mapping(source = "empleado.direccionDomicilio", target = "direccionDomicilio")
    @Mapping(source = "empleado.telefono", target = "telefono")
    @Mapping(source = "vacunacion.estadoVacuna", target = "estadoVacuna")
    @Mapping(source = "vacunacion.tipoVacuna", target = "infoVacuna.tipoVacuna")
    @Mapping(source = "vacunacion.fechaVacunacion", target = "infoVacuna.fechaVacunacion")
    @Mapping(source = "vacunacion.numeroDosis", target = "infoVacuna.numeroDosis")
   InfoEmpleadoRes toInfoEmpleado(Empleado empleado, Vacunacion vacunacion, Usuario usuario);


}
