package com.app.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Carrito;
import com.app.model.Producto;
import com.app.model.Usuario;
import com.app.service.CarritoService;
import com.app.service.ProductoService;
import com.app.service.UsuarioService;


@Controller
@RequestMapping("/carrito")
public class CarritoControllerReal {

	@Autowired
	ProductoService productoService;

	@Autowired
	CarritoService carritoService;

	@Autowired
	UsuarioService usuarioService;


	@GetMapping("/")
	public String mostrarCarrito(Model model, Principal principal){

		Usuario logged = usuarioService.obtenerUsuarioPorNombre(principal.getName()).get();
		Carrito carrito = carritoService.obtenerCarritoPorUsuario(logged);
		model.addAttribute("carrito", carrito);

		model.addAttribute("loggedUser", "Usuario: " + principal.getName());
		return "carrito";
	}

	@GetMapping("/agregar/{idProducto}")
	public String agregarProductoAlCarrito(@PathVariable Integer idProducto,Model model){

		Carrito carrito;

		Producto producto = productoService.findById(idProducto);
		carrito = carritoService.agregarAlCarrito(producto);
		model.addAttribute("producto", producto);

		return "pieza";
	}
	
//	// Incrementar cantidad - POST
//	@PostMapping("/incrementar/{idProducto}")
//	public String incrementarCantidad(@PathVariable Integer idProducto, Principal principal) {
//	    Usuario logged = usuarioService.obtenerUsuarioPorNombre(principal.getName()).get();
//	    carritoService.incrementarCantidad(idProducto, logged);
//	    return "redirect:/carrito/";
//	}
//
//	// Decrementar cantidad - POST
//	@PostMapping("/decrementar/{idProducto}")
//	public String decrementarCantidad(@PathVariable Integer idProducto, Principal principal) {
//	    Usuario logged = usuarioService.obtenerUsuarioPorNombre(principal.getName()).get();
//	    carritoService.decrementarCantidad(idProducto, logged);
//	    return "redirect:/carrito/";
//	}
//
//	// Eliminar producto - POST
//	@PostMapping("/eliminar/{idProducto}")
//	public String eliminarProducto(@PathVariable Integer idProducto, Principal principal) {
//	    Usuario logged = usuarioService.obtenerUsuarioPorNombre(principal.getName()).get();
//	    carritoService.eliminarProducto(idProducto, logged);
//	    return "redirect:/carrito/";
//	}

}
