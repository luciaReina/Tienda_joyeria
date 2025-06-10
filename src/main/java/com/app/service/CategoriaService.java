package com.app.service;

import java.util.List;

import com.app.model.Producto;
import com.app.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Categoria;
import com.app.repository.CategoriaRepository;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

	@Autowired
	private ProductoRepository productoRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

	public void save(Categoria categoria) {
		categoriaRepository.save(categoria);
	}

	public Categoria findById(Integer id) {
		return categoriaRepository.findById(id).orElse(null);
	}

	public void deleteById(Integer id) {

		List < Producto> productosCategoria = productoRepository.findByCategoria(categoriaRepository.findById(id).get());
		productoRepository.deleteAll(productosCategoria);
		categoriaRepository.deleteById(id);
		
	}
}