package com.app.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.model.Producto;
import com.app.model.Usuario;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    // Verifica si el usuario está logueado
    @GetMapping("/sesion-usuario")
    public ResponseEntity<?> checkSesion(HttpSession session) {
        return session.getAttribute("usuario") != null
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // Añade un producto al carrito
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarAlCarrito(@RequestBody Producto producto, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión.");
        }

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null) {
            carrito = new ArrayList<>();
        }

        carrito.add(producto);
        session.setAttribute("carrito", carrito);

        return ResponseEntity.ok("Producto añadido correctamente");
    }

    // Obtiene el contenido del carrito
    @GetMapping
    public ResponseEntity<?> obtenerCarrito(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión.");
        }

        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        if (carrito == null || carrito.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        return ResponseEntity.ok(carrito);
    }
}
