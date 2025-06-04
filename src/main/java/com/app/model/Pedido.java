package com.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PEDIDOS")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -3838801358738239788L;
	
	 private Integer idPedido;
	 private Usuario usuario;
	 private Producto producto;
	 private Double precioTotal;
	 private String estado;
	 private Date fechaPedido;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO", nullable = false)
    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Column(name = "PRECIO_TOTAL", nullable = false)
    public Double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Double precioTotal) {
        this.precioTotal = precioTotal;
    }

    @Column(name = "ESTADO", nullable = false, length = 50)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "FECHA_PEDIDO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) 
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPedido);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pedido)) return false;
        Pedido other = (Pedido) obj;
        return Objects.equals(idPedido, other.idPedido);
    }
}