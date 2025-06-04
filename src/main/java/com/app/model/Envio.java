package com.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "ENVIOS")
public class Envio implements Serializable {

    private static final long serialVersionUID = 5375732974556280611L;

    private Integer idEnvio;
    private Pedido pedido;
    private Date fechaEnvio;
    private String estado; // entregado / no entregado

    // Nueva sección: dirección
    private String calle;
    private String ciudad;
    private String codigoPostal;
    private String pais;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENVIO", nullable = false)
    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Column(name = "FECHA_ENVIO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    @Column(name = "ESTADO", nullable = false, length = 50)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // ==== NUEVOS CAMPOS DE DIRECCIÓN ====

    @Column(name = "CALLE", nullable = false, length = 100)
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Column(name = "CIUDAD", nullable = false, length = 50)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Column(name = "CODIGO_POSTAL", nullable = false, length = 10)
    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Column(name = "PAIS", nullable = false, length = 50)
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEnvio);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Envio)) return false;
        Envio other = (Envio) obj;
        return Objects.equals(idEnvio, other.idEnvio);
    }
}
