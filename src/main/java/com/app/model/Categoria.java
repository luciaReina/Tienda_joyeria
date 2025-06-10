package com.app.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATEGORIA")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Categoria implements Serializable {

	private static final long serialVersionUID = -81984712149714253L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA", nullable = false)
	private Integer idCategoria;
    @Column(name = "TIPO", nullable = false, length = 255)
    private String tipo;

}