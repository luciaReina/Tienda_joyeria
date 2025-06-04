package com.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
@Table(name = "PAGOS")
public class Pago implements Serializable {

	private static final long serialVersionUID = 4528491263519090594L;
	
	private Integer idPago;
    private Pedido pedido;
    private Date fechaPago;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAGO", nullable = false)
    public Integer getIdPago() {
        return idPago;
    }

    public void setIdPago(Integer idPago) {
        this.idPago = idPago;
    }

    @ManyToOne
    @JoinColumn(name = "ID_PEDIDO", nullable = false)
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Column(name = "FECHA_PAGO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pago)) return false;
        Pago other = (Pago) obj;
        return Objects.equals(idPago, other.idPago);
    }
}
