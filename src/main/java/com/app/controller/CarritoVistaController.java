package com.app.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.model.Producto;

@Controller
public class CarritoVistaController {
	@GetMapping("/carrito")
	public String mostrarCarrito(Authentication authentication, Model model) {
				
//	    Usuario usuario = authentication.getPrincipal();
	    System.out.println("Usuario en sesión: " + authentication.getPrincipal()); 
	    if (authentication.getPrincipal()== null) {
	        return "redirect:/html/login.html"; // o una página de error
	    }
//	   

////	    List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
////	    boolean vacio = (carrito == null || carrito.isEmpty());
//
////	    model.addAttribute("carrito", carrito);
////	    model.addAttribute("subtotal", calcularSubtotal(carrito));
////	    model.addAttribute("total", calcularSubtotal(carrito) + 3.99);
////	    model.addAttribute("vacio", vacio);

	    return "carrito";
	}

	private double calcularSubtotal(List<Producto> carrito) {
	    if (carrito == null) return 0.0;
	    return carrito.stream().mapToDouble(Producto::getPrecio).sum();
	}

}
