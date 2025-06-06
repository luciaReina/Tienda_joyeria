package com.app.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.app.model.Producto;
import com.app.repository.CarritoRepository;

@Service
@SessionScope
public class CarritoService {

	@Autowired
    private CarritoRepository carritoRepository;
	
    private final List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void limpiarCarrito() {
        productos.clear();
    }

    public BigDecimal calcularTotal() {
        return productos.stream()
                .map(p -> BigDecimal.valueOf(p.getPrecio()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean estaVacio() {
        return productos.isEmpty();
    }
}

