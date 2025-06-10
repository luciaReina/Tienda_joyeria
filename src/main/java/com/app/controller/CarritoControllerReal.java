package com.app.controller;

import com.app.model.Carrito;
import com.app.model.Producto;
import com.app.model.Usuario;
import com.app.service.CarritoService;
import com.app.service.ProductoService;
import com.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


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
		System.out.println("FEO1");
		Carrito carrito = carritoService.obtenerCarritoPorUsuario(logged);
//		System.out.println("FEO2" + carrito);
		model.addAttribute("carrito", carrito);
		System.out.println("FEO3");

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

}
