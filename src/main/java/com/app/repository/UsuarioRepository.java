package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNombre(String nombre); 
}

