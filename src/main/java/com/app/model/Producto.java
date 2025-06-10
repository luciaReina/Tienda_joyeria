package com.app.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PRODUCTOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Producto implements Serializable {

	private static final long serialVersionUID = 6942481425718025540L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUCTO", nullable = false)
	private Integer idProducto;
    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombre;
    @Column(name = "DESCRIPCION", length = 1000)
    private String descripcion;
    @Column(name = "PRECIO", nullable = false)
    private Double precio;
    @Column(name = "STOCK", nullable = false)
    private Integer stock;
    @ManyToOne
    @JoinColumn(
            name = "ID_CATEGORIA",
            foreignKey = @ForeignKey(name = "FK_PRODUCTO_TO_CATEGORIA")
    )
    private Categoria categoria;
    @Column(name = "ESTILO", length = 255)
    private String estilo;
    @Column(name = "IMGURL", length = 255)
    private String imgUrl;





} 