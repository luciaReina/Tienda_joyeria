package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	
}  



