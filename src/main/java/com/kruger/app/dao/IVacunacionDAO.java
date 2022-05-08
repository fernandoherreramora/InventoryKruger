package com.kruger.app.dao;

import com.kruger.app.dto.Usuario;
import com.kruger.app.dto.Vacunacion;
import com.kruger.app.enums.EstadoVacuna;
import com.kruger.app.enums.TipoVacuna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Transactional
@Repository
public interface IVacunacionDAO extends JpaRepository<Vacunacion, Long> {

    Vacunacion findByUsuario(Usuario user);

    @Modifying
    @Query(value = "UPDATE Vacunacion v SET v.estadoVacuna=?1, v.fechaVacunacion=?2, v.numeroDosis=?3, v.tipoVacuna=?4 WHERE v.id=?5")
    void updateVacunacion(EstadoVacuna estadoVacuna, LocalDate fechaVacunacion, Integer numeroDosis, TipoVacuna tipoVacuna, Long id);

}