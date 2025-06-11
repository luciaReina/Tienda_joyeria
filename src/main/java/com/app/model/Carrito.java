package com.app.model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//base de datos "auxiliar" 
@Entity
@Table(name = "CARRITO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Carrito implements Serializable{

	private static final long serialVersionUID = 8692021044211192960L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARRITO", unique = true, nullable = false)
	private Integer idCarrito;
	@OneToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	@OneToMany(
			mappedBy = "carrito",
			cascade = CascadeType.ALL, // Cambiado de MERGE a ALL para un manejo más completo
			orphanRemoval = true, // Para asegurar que los ItemCarrito se borren si se quitan de la lista
			fetch = FetchType.LAZY // Lazy loading es mejor para colecciones grandes
	)
	@Builder.Default
	private List<ItemCarrito> itemsCarrito = new ArrayList<>();
	private Double subTotal;
	@Builder.Default
	private Double gastosEnvio = 3.99;
	private Double total;

	public void calcularTotales() {
		this.subTotal = itemsCarrito.stream()
				.mapToDouble(ItemCarrito::getSubTotal)
				.sum();
		this.total = this.subTotal + this.gastosEnvio;
	}

	public void addItemCarrito(ItemCarrito item) {
		this.itemsCarrito.add(item);
		item.setCarrito(this); // 
	}

	public void removeItemCarrito(ItemCarrito item) {
		this.itemsCarrito.remove(item);
		item.setCarrito(null); // Desvincular
	}

}
