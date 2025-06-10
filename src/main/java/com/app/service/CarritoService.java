package com.app.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.app.model.Carrito;
import com.app.model.ItemCarrito;

import com.app.model.Usuario;
import com.app.repository.UsuarioRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.app.model.Producto;
import com.app.repository.CarritoRepository;

@Service
@SessionScope
public class CarritoService {

    private Carrito carritoSesion;

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {

        this.carritoSesion = new Carrito();
        this.carritoSesion.setUsuario(usuarioRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
        System.out.println("CarritoService inicializado para una nueva sesión. Carrito ID: " + carritoSesion.getIdCarrito());
    }

    @Transactional
    public Carrito agregarAlCarrito(Producto producto) {
        if (producto == null || producto.getIdProducto() == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo y debe tener un ID.");
        }

        if (this.carritoSesion == null) {
            this.carritoSesion = new Carrito();
        }

        Optional<ItemCarrito> existingItemOptional = carritoSesion.getItemsCarrito().stream()
                .filter(item -> item.getProducto() != null && producto.getIdProducto().equals(item.getProducto().getIdProducto()))
                .findFirst();

        if (existingItemOptional.isPresent()) {
            ItemCarrito existingItem = existingItemOptional.get();
            existingItem.setCantidad(existingItem.getCantidad() + 1);
            existingItem.calculateSubTotal();
        } else {
            // El producto no existe en el carrito, crear un nuevo ItemCarrito
            ItemCarrito newItem = ItemCarrito.builder()
                    .producto(producto)
                    .cantidad(1)
                    // subTotal se calculará automáticamente en @PrePersist/@PreUpdate del ItemCarrito
                    .build();
            carritoSesion.addItemCarrito(newItem); // Añadir al carrito y establecer la bidireccionalidad
        }



        // Persistir el carrito en la base de datos
        // Si el carrito es nuevo, lo guarda. Si ya tiene un ID, lo actualiza.
        // Gracias a CascadeType.ALL, los ItemCarrito nuevos se persistirán
        // y los existentes (que se modificaron) se actualizarán.
        this.carritoSesion = carritoRepository.save(carritoSesion);
        // Recalcular los totales del carrito después de modificar los items
        carritoSesion.calcularTotales();


        System.out.println("Producto '" + producto.getNombre() + "' agregado/actualizado en el carrito.");
        System.out.println("Estado actual del carrito ID: " + carritoSesion.getIdCarrito());
        carritoSesion.getItemsCarrito().forEach(item ->
                System.out.println("  - Item: " + item.getProducto().getNombre() + ", Cantidad: " + item.getCantidad() + ", SubTotal: " + item.getSubTotal())
        );
        System.out.println("SubTotal Carrito: " + carritoSesion.getSubTotal());
        System.out.println("Total Carrito: " + carritoSesion.getTotal());

        return this.carritoSesion;
    }

    public Carrito obtenerCarritoPorUsuario(Usuario usuario){
//        if()
        return carritoRepository.findByUsuario(usuario).orElse(null);
    }

    /**
     * Obtiene el carrito de la sesión actual.
     * @return El Carrito de la sesión.
     */
    public Carrito getCarritoSesion() {
        if (this.carritoSesion == null) {
            // Esto no debería ocurrir si @PostConstruct funciona, pero es una buena práctica defensiva.
            this.carritoSesion = new Carrito();
        }
        return this.carritoSesion;
    }

    // Puedes agregar más métodos, por ejemplo:
    // - removerDelCarrito(Producto producto)
    // - actualizarCantidad(Producto producto, int nuevaCantidad)
    // - vaciarCarrito()
    // - finalizarCompra() -> donde se limpia la sesión y el carrito podría marcarse como "comprado"
}
