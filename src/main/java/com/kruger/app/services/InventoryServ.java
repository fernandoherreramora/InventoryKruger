package com.kruger.app.services;

import com.kruger.app.dao.IEmpleadoDAO;
import com.kruger.app.dao.IUsuarioDAO;
import com.kruger.app.dao.IVacunacionDAO;
import com.kruger.app.dto.Empleado;
import com.kruger.app.dto.Usuario;
import com.kruger.app.dto.Vacunacion;
import com.kruger.app.enums.EstadoVacuna;
import com.kruger.app.enums.Rol;
import com.kruger.app.mapper.EmpleadoMapper;
import com.kruger.app.model.*;
import com.kruger.app.utilitys.ValidGeneric;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class InventoryServ implements IInventoryServ {
    @Autowired
    IEmpleadoDAO empleadoDAO;

    @Autowired
    EmpleadoMapper empleadoMapper;

    @Autowired
    IUsuarioDAO usuarioDAO;

    @Autowired
    IVacunacionDAO vacunacionDAO;


    /**
     * Funcion para guardar los empleados
     *
     * @param request Objeto de tipo EmpleadoReq para registrar en la base
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> guardarEmpleado(EmpleadoReq request) {

        Response resp = new Response();
        Usuario usuario = null;

        ValidGeneric valid = new ValidGeneric();
        try {
            if (valid.validNumber(request.getDni()) && valid.validStrings(request.getNombres())
                    && valid.validStrings(request.getApellidos())) {


                Empleado empleado = empleadoMapper.empleadoReqToEmpleado(request);
                log.info(empleado);


                usuario = this.generaDatosAuth(request.getApellidos(), request.getNombres(), request.getDni());
                empleado.setUsuario(usuario);
                empleadoDAO.save(empleado);
                resp.setCode(200);
                resp.setMessage("OK");
                resp.setResponse(new Usuario(empleado.getId(), usuario.getUsuario(), request.getDni(), usuario.getRol()));
                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
            } else {
                resp.setCode(400);
                resp.setMessage("Request Invalido");
                return ResponseEntity.status(HttpStatus.OK).body(resp);
            }
        } catch (Exception e) {
            if (usuario != null) {
                usuarioDAO.delete(usuario);
            }
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    /**
     * Funcion para obtener la lista de empleados
     *
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> listarEmpleados() {
        Response resp = new Response();
        try {
            List<Empleado> listEmpleado = empleadoDAO.findAll();
            List<InfoEmpleadoRes> listEmpleadoInfo = listEmpleado.stream().map(empl -> {
                Usuario user = empl.getUsuario();
                Vacunacion vacc = vacunacionDAO.findByUsuario(user);
                return empleadoMapper.toInfoEmpleado(empl, vacc, user);
            }).collect(Collectors.toList());

            resp.setCode(200);
            resp.setMessage("OK");
            resp.setResponse(listEmpleadoInfo);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    /**
     * Funcion para actualizar el registro de empleados
     *
     * @param id      Identificador de Empleado
     * @param request Objeto con los datos actualizar
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> editarEmpleado(Long id, EditAdminEmpleadoReq request) {
        Response resp = new Response();
        try {
            log.info("fecha: {}", request.getFechaNacimiento());
            Optional<Empleado> empleado = empleadoDAO.findById(id);
            if (empleado.isPresent()) {
                Empleado empl = empleado.get();
                if (request.getApellidos() != null)
                    empl.setApellidos(request.getApellidos());
                if (request.getCorreo() != null)
                    empl.setCorreo(request.getCorreo());
                if (request.getNombres() != null)
                    empl.setNombres(request.getNombres());
                if (request.getTelefono() != null)
                    empl.setTelefono(request.getTelefono());
                if (request.getDireccionDomicilio() != null)
                    empl.setDireccionDomicilio(request.getDireccionDomicilio());
                if (request.getFechaNacimiento() != null)
                    empl.setFechaNacimiento(request.getFechaNacimiento());

                empleadoDAO.updateEmpleado(empl.getApellidos(), empl.getNombres(), empl.getDireccionDomicilio(),
                        empl.getCorreo(), empl.getTelefono(), empl.getFechaNacimiento(), id);
                resp.setCode(200);
                resp.setMessage("OK");

            } else {
                resp.setCode(400);
                resp.setMessage("Empleado no encontrado");
            }

            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    /**
     * Funcion para eliminar el registro de empleado
     *
     * @param id Identificador de Empleado
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> eliminarEmpleado(Long id) {
        Response resp = new Response();
        try {
            Optional<Empleado> empleado = empleadoDAO.findById(id);
            if (empleado.isPresent()) {
                Empleado empl = empleado.get();
                Usuario user = empl.getUsuario();
                Vacunacion vacc = vacunacionDAO.findByUsuario(user);
                empleadoDAO.delete(empl);
                vacunacionDAO.delete(vacc);
                usuarioDAO.delete(user);

                resp.setCode(200);
                resp.setMessage("Empleado eliminado");

            } else {
                resp.setCode(400);
                resp.setMessage("Empleado no encontrado");
            }
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    /**
     * Funcion para generar los datos de autenticacion (Usuario, Password)
     *
     * @param apellidos Apellidos de empleado
     * @param nombres   Nombres de empleado
     * @param dni       Dni de empleado
     * @return Modelo datos Usuario.class
     */
    @Override
    public Usuario generaDatosAuth(String apellidos, String nombres, String dni) throws NoSuchPaddingException, NoSuchAlgorithmException {
        String apellido = apellidos.split(" ")[0];
        String nombre = nombres.split(" ")[0];

        StringBuilder str = new StringBuilder();
        str.append(nombre.charAt(0)).append(apellido).append(1);
        String user = str.toString();
        log.info(str.toString());
        Usuario usuario;
        while (true) {
            usuario = usuarioDAO.findByUsuario(user);
            if (usuario != null) {
                char lastChar = usuario.getUsuario().charAt(usuario.getUsuario().length() - 1);
                int num = Character.getNumericValue(lastChar);
                num++;
                user = user.substring(0, user.length() - 1);
                user += num;
            } else {
                break;
            }
        }
        usuario = new Usuario(user.toLowerCase(), dni, Rol.ROL_USER);
        usuario = usuarioDAO.save(usuario);

        return usuario;
    }

    /**
     * Funcion para obtener la infromacion del empleado
     *
     * @param user Usuario del empleado
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> getInfoEmpleado(String user) {
        Response resp = new Response();
        try {
            Usuario usuario = usuarioDAO.findByUsuario(user);
            Empleado empleado = empleadoDAO.findByUsuario(usuario);
            Vacunacion vacunacion = vacunacionDAO.findByUsuario(usuario);

            InfoEmpleadoRes infoEmpleado = empleadoMapper.toInfoEmpleado(empleado, vacunacion, usuario);
            if (infoEmpleado.getInfoVacuna().getFechaVacunacion() == null) {
                infoEmpleado.setInfoVacuna(null);
            }

            resp.setCode(200);
            resp.setMessage("OK");
            resp.setResponse(infoEmpleado);
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }

    }

    /**
     * Funcion para actualizar la informacion del empleado
     *
     * @param user    Usuario del empleado
     * @param request Modelo datos que va actualizar el empleado
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> updateInfoEmpleado(String user, InfoEmpleadoReq request) {
        Response resp = new Response();
        try {
            Usuario usuario = usuarioDAO.findByUsuario(user);
            Empleado empleado = empleadoDAO.findByUsuario(usuario);

            Vacunacion vac = new Vacunacion();
            Vacunacion vacFind = vacunacionDAO.findByUsuario(usuario);
            if (vacFind == null) {
                vac.setUsuario(usuario);
                vac.setEstadoVacuna(request.getEstadoVacuna());
                if (request.getEstadoVacuna().equals(EstadoVacuna.VACUNADO)) {
                    vac.setTipoVacuna(request.getInfoVacuna().getTipoVacuna());
                    vac.setFechaVacunacion(request.getInfoVacuna().getFechaVacunacion());
                    vac.setNumeroDosis(request.getInfoVacuna().getNumeroDosis());
                }
                vacunacionDAO.save(vac);
            } else {
                vacunacionDAO.updateVacunacion(request.getEstadoVacuna(), request.getInfoVacuna().getFechaVacunacion(),
                        request.getInfoVacuna().getNumeroDosis(), request.getInfoVacuna().getTipoVacuna(), vacFind.getId());
            }
            empleadoDAO.editEmpleado(request.getFechaNacimiento(), request.getDireccionDomicilio(), request.getTelefono(), empleado.getId());

            resp.setCode(200);
            resp.setMessage("Empleado Actualizado");
            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }

    /**
     * Funcion para obtener listado de los empleados por los criterios de filtro
     *
     * @param request Modelo datos para filtrar los registros
     * @return Status del servicio con la respuesta del formato Response.class
     */
    @Override
    public ResponseEntity<Response> filterEmpleado(FilterEmpleadoReq request) {
        Response resp = new Response();
        try {

            List<Object[]> data = null;
            List<InfoEmpleadoRes> infoEmpleado = null;

            if (request.getEstadoVacuna() != null) {
                data = empleadoDAO.filterEmpleadoByEstadoVacuna(request.getEstadoVacuna());
            } else if (request.getTipoVacuna() != null) {
                data = empleadoDAO.filterEmpleadoByTipoVacuna(request.getTipoVacuna());
            } else if (request.getFechaDesde() != null && request.getFechaHasta() != null) {
                data = empleadoDAO.filterEmpleadoByDate(request.getFechaDesde(), request.getFechaHasta());

            } else {
                resp.setCode(400);
                resp.setMessage("Filtros incorrectos");
            }
            if (data != null) {
                infoEmpleado = data.stream()
                        .map(obj -> new Inventory((Empleado) obj[0], (Vacunacion) obj[1]))
                        .map(inv -> {
                            Usuario user = inv.getEmpleado().getUsuario();
                            return empleadoMapper.toInfoEmpleado(inv.getEmpleado(), inv.getVacunacion(), user);
                        })
                        .collect(Collectors.toList());
                resp.setCode(0);
                resp.setMessage(infoEmpleado.isEmpty() ? "No hay registros" : "OK");
            }

            resp.setResponse(infoEmpleado);

            return ResponseEntity.status(HttpStatus.OK).body(resp);
        } catch (Exception e) {
            log.error(e.getMessage());
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);
        }
    }
}
