package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Solicitud;

public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
   
}
