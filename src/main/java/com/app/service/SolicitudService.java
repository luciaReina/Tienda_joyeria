package com.app.service;

import java.util.List;

import com.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Pedido;
import com.app.model.Solicitud;
import com.app.repository.SolicitudRepository;

@Service
public class SolicitudService {
	
	@Autowired
    private SolicitudRepository solicitudRepository;

    public List<Solicitud> findAll() {
        return solicitudRepository.findAll();
    }
    
	public void save(Solicitud solicitud) {
		System.out.println(solicitud.getFechaSolicitud());
		solicitudRepository.save(solicitud);
	}

	public Solicitud findById(Integer id) {
		return solicitudRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		solicitudRepository.deleteById(id);
		
	}

}

