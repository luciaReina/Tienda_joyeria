package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Pago;
import com.app.model.Pedido;
import com.app.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    
	public void save(Pedido pedido) {
		pedidoRepository.save(pedido);
	}

	public Pedido findById(Integer id) {
		return pedidoRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		pedidoRepository.deleteById(id);
		
	}
}

