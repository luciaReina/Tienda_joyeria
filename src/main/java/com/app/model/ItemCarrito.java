package com.app.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//base de datos "auxiliar" 
@Entity
@Table(name = "ITEM_CARRITO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemCarrito implements Serializable{

	private static final long serialVersionUID = -4177125890504333290L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ITEM_CARRITO", unique = true, nullable = false, precision = 9, scale = 0)
	private Integer idItemCarrito;
	@ManyToOne
	@JoinColumn(name = "ID_CARRITO")
	private Carrito carrito;
	@ManyToOne(fetch = FetchType.EAGER) // Eager loading para el producto, ya que es probable que siempre lo necesitemos
	@JoinColumn(name = "ID_PRODUCTO", nullable = false) // Un ItemCarrito siempre debe tener un Producto
	private Producto producto;
	@Builder.Default
	private Double subTotal = 0.00;
	private Integer cantidad;

	@PrePersist // Se ejecuta antes de persistir
	@PreUpdate  // Se ejecuta antes de actualizar
	public void calculateSubTotal() {
		if (this.producto != null && this.cantidad != null) {
			this.subTotal = this.producto.getPrecio() * this.cantidad;
		} else {
			this.subTotal = 0.0;
		}
	}


	
	
}


