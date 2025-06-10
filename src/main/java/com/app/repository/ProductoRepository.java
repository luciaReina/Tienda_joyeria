package com.app.repository;

import com.app.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Producto;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByCategoria(Categoria categoria);

	
} 


