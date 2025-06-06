package com.app.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//base de datos "auxiliar" 
@Entity
@Table(name = "CARRITO")
public class Carrito implements Serializable{

	private static final long serialVersionUID = 8692021044211192960L;
	
	private Integer idCarrito;
	private String estado;
	private List<ItemCarrito> itemCarrito;
	private Double subTotal;
	private Double gastosEnvio;
	private Double total;
	
	
	
	//getter y setter
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARRITO", unique = true, nullable = false, precision = 9, scale = 0)
	public Integer getIdCarrito() {
		return idCarrito;
	}
	public void setIdCarrito(Integer idCarrito) {
		this.idCarrito = idCarrito;
	}
	
	@Column(name = "ESTADO", nullable = false, length = 100)
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public List<ItemCarrito> getItemCarrito() {
		return itemCarrito;
	}
	
	public void setItemCarrito(List<ItemCarrito> itemCarrito) {
		this.itemCarrito = itemCarrito;
	}
	
	public Double getSubTotal() {
		return subTotal;
	}
	
	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
	
	public Double getGastosEnvio() {
		return gastosEnvio;
	}
	
	public void setGastosEnvio(Double gastosEnvio) {
		this.gastosEnvio = gastosEnvio;
	}
	
	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}
	
	

}
