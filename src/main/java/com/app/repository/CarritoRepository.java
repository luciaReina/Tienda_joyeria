package com.app.repository;

import java.util.Optional;

import com.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Integer> {
    Optional<Carrito> findByUsuario(Usuario usuario);
}