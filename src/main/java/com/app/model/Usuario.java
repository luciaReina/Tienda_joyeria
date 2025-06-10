package com.app.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1313229735683120584L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 9, scale = 0)
    private Integer idUsuario;
    @Column(name = "USERNAME", nullable = false, length = 100)
    private String username;
    @Column(name = "APELLIDO", nullable = false, length = 255)
    private String apellido;
    @Column(name = "EMAIL", nullable = false, length = 255)
    private String email;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "ROL", nullable = false, length = 50)
    private String rol;
    @Column(name = "TELEFONO", nullable = false, length = 20)
    private String telefono;

    // NUEVOS CAMPOS DE DIRECCIÓN
    @Column(name = "CALLE", nullable = false, length = 100)
    private String calle;
    @Column(name = "CIUDAD", nullable = false, length = 50)
    private String ciudad;
    @Column(name = "CODIGO_POSTAL", nullable = false, length = 10)
    private String codigoPostal;
    @Column(name = "PAIS", nullable = false, length = 50)
    private String pais;


}