package com.kruger.app.dto;

import com.kruger.app.enums.Rol;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@ApiModel(description = "Modelo Vacunacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Usuario {

    @ApiModelProperty(hidden = true)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String usuario;
    private String password;
    private Rol rol;

    public Usuario(String usuario, String password, Rol rol) {
        this.usuario = usuario;
        this.password = password;
        this.rol = rol;
    }
}
