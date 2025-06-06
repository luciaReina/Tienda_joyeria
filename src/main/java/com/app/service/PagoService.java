package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Envio;
import com.app.model.Pago;
import com.app.repository.PagoRepository;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }
    
	public void save(Pago pago) {
		pagoRepository.save(pago);
	}

	public Pago findById(Integer id) {
		return pagoRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		pagoRepository.deleteById(id);
		
	}
}

