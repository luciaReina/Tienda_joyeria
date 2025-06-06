package com.app.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//base de datos "auxiliar" 
@Entity
@Table(name = "ITEM_CARRITO")
public class ItemCarrito implements Serializable{

	private static final long serialVersionUID = -4177125890504333290L;
	
	private Integer idItemCarrito;
	private Producto producto;
	private Double subTotal;
	private Integer cantidad;
	
	//GETTER Y SETTER
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEM_CARRITO", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getIdItemCarrito() {
		return idItemCarrito;
	}
	
	public void setIdItemCarrito(Integer idItemCarrito) {
		this.idItemCarrito = idItemCarrito;
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Double getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	public Integer getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
