package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Producto;
import com.app.repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

	public void save(Producto producto) {
		productoRepository.save(producto);
	}

	public Producto findById(Integer id) {
		return productoRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {
		productoRepository.deleteById(id);
		
	}


}

