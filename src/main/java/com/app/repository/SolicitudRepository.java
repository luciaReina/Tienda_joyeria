package com.app.repository;

import com.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Solicitud;

import java.util.List;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {

    List<Solicitud> findAllByUsuario(Usuario usuario);
}
