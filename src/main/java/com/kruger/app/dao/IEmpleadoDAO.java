package com.kruger.app.dao;

import com.kruger.app.dto.Empleado;
import com.kruger.app.dto.Usuario;
import com.kruger.app.enums.EstadoVacuna;
import com.kruger.app.enums.TipoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Repository
public interface IEmpleadoDAO extends JpaRepository<Empleado, Long> {

    @Modifying
    @Query(value = "UPDATE Empleado em SET em.apellidos= ?1, em.nombres= ?2, em.direccionDomicilio=?3, " +
            "em.correo=?4, em.telefono=?5, em.fechaNacimiento=?6 WHERE em.id=?7")
    void updateEmpleado(String apellidos, String nombres, String direccion_domicilio,
                        String correo, String telefono, LocalDate fechaNacimiento, Long id);

    @Modifying
    @Query(value = "UPDATE Empleado em SET em.fechaNacimiento=?1, em.direccionDomicilio=?2, em.telefono=?3 " +
            "WHERE em.id=?4")
    void editEmpleado(LocalDate fechaNacimiento, String direccionDomicilio, String telefono, Long id);

    Empleado findByUsuario(Usuario user);

    @Query(value = "SELECT e, v \n" +
            "FROM Empleado e \n" +
            "JOIN Usuario u on e.usuario = u.id \n" +
            "JOIN Vacunacion v on v.usuario= u.id " +
            "WHERE v.fechaVacunacion between ?1 and ?2")
    List<Object[]> filterEmpleadoByDate(LocalDate fechaDesde, LocalDate fechaHasta);

    @Query(value = "SELECT e, v \n" +
            "FROM Empleado e \n" +
            "JOIN Usuario u on e.usuario = u.id \n" +
            "JOIN Vacunacion v on v.usuario= u.id " +
            "WHERE v.tipoVacuna = ?1")
    List<Object[]> filterEmpleadoByTipoVacuna(TipoVacuna tipoVacuna);

    @Query(value = "SELECT e, v \n" +
            "FROM Empleado e \n" +
            "JOIN Usuario u on e.usuario = u.id \n" +
            "JOIN Vacunacion v on v.usuario= u.id " +
            "WHERE v.estadoVacuna = ?1")
    List<Object[]> filterEmpleadoByEstadoVacuna(EstadoVacuna estadoVacuna);


}
