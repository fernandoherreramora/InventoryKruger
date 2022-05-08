package com.kruger.app.dao;

import com.kruger.app.dto.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUsuarioDAO extends JpaRepository<Usuario, Long> {

    Usuario findByUsuario(String user);


}