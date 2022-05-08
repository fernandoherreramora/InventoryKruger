package com.kruger.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@ApiModel(description = "Modelo Empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String correo;

    private LocalDate fechaNacimiento;

    private String direccionDomicilio;

    private String telefono;

    @OneToOne
    private Usuario usuario;




}
