package com.app.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "SOLICITUDES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Solicitud implements Serializable {

	private static final long serialVersionUID = 4425383828624666364L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITUD", nullable = false)
	private Integer idSolicitud;
    @ManyToOne
    @JoinColumn(
            name = "ID_USUARIO",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_SOLICITUD_TO_USUARIO")
    )
    private Usuario usuario;
    @Column(name = "FECHA_SOLICITUD", nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime fechaSolicitud;
    @Column(name = "ASUNTO", nullable = false, length = 255)
    private String asunto;
    @Column(name = "MENSAJE", nullable = false, length = 1000)
    private String mensaje;



}