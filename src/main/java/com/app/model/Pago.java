package com.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PAGOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Pago implements Serializable {

	private static final long serialVersionUID = 4528491263519090594L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGO", nullable = false)
	private Integer idPago;
    @ManyToOne
    @JoinColumn(
            name = "ID_PEDIDO",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PAGO_A_PEDIDO")
    )
    private Pedido pedido;
    @Column(name = "FECHA_PAGO", nullable = false)
    private Date fechaPago;



}
