package com.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "PEDIDOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pedido implements Serializable {

    private static final long serialVersionUID = -3838801358738239788L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO", nullable = false)
    private Integer idPedido;
    @ManyToOne
    @JoinColumn(
            name = "ID_USUARIO",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PEDIDO_USUARIO")
    )
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "ID_PRODUCTO", nullable = false)
    private Producto producto;
    @Column(name = "PRECIO_TOTAL", nullable = false)
    private Double precioTotal;
    @Column(name = "ESTADO", nullable = false, length = 50)
    private String estado;
    @Column(name = "FECHA_PEDIDO", nullable = false)
    private Date fechaPedido;


}