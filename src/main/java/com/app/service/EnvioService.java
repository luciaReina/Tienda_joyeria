package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Envio;
import com.app.repository.EnvioRepository;

@Service
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;

    public List<Envio> findAll() {
        return envioRepository.findAll();
    }
    
	public void save(Envio envio) {
		envioRepository.save(envio);
	}

	public Envio findById(Integer id) {
		return envioRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		envioRepository.deleteById(id);
		
	}
}
