package com.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional <Usuario> findByUsername(String username);
}
