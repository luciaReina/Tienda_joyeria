package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Producto;
import com.app.model.Usuario;
import com.app.service.CarritoService;
import com.app.service.ItemCarritoService;


@Controller
@RequestMapping("/carrito")
public class CarritoControllerReal {
	
	@Autowired
	private CarritoService carritoService;
	
	@Autowired
	private ItemCarritoService itemCarritoService;
	
	 // Añade un producto al carrito
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarAlCarrito(@RequestBody Integer idProducto) {
//        Usuario usuario = (Usuario) session.getAttribute("usuario");
//        if (usuario == null) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Debes iniciar sesión.");
//        }
//
//        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
//        if (carrito == null) {
//            carrito = new ArrayList<>();
//        }
//
//        carrito.add(producto);
//        session.setAttribute("carrito", carrito);

        return ResponseEntity.ok("Producto añadido correctamente");
    }
	
}
