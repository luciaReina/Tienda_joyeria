package com.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "SOLICITUDES")
public class Solicitud implements Serializable {

	private static final long serialVersionUID = 4425383828624666364L;
	
	private Integer idSolicitud;
    private Usuario usuario;
    private Date fechaSolicitud;
    private String asunto;
    private String mensaje;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SOLICITUD", nullable = false)
    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name = "FECHA_SOLICITUD", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @Column(name = "ASUNTO", nullable = false, length = 255)
    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Column(name = "MENSAJE", nullable = false, length = 1000)
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitud);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Solicitud)) return false;
        Solicitud other = (Solicitud) obj;
        return Objects.equals(idSolicitud, other.idSolicitud);
    }
}
