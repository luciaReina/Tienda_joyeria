package com.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Envio;

public interface EnvioRepository extends JpaRepository<Envio, Integer> {
    
}
