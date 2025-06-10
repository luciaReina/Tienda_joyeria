package com.app.controller;

import com.app.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.service.CategoriaService;
import com.app.service.EnvioService;
import com.app.service.PagoService;
import com.app.service.PedidoService;
import com.app.service.ProductoService;
import com.app.service.SolicitudService;
import com.app.service.UsuarioService;

@Controller
public class AdminController {
	
	 @Autowired
	 private UsuarioService usuarioService;
	 
	 @Autowired
	 private ProductoService productoService;
	 
	 @Autowired
	 private PedidoService pedidoService;
	 
	 @Autowired
	 private SolicitudService solicitudService;
	 
	 @Autowired
	 private EnvioService envioService;
	 
	 @Autowired
	 private PagoService pagoService;
	 
	 @Autowired
	 private CategoriaService categoriaService;

    @GetMapping("/admin/crud")
    public String mostrarCrud(Authentication authentication, Model model) {
    	model.addAttribute("productos", productoService.findAll());
	    model.addAttribute("usuarios", usuarioService.findAll());
	    model.addAttribute("pedidos", pedidoService.findAll());
	    model.addAttribute("solicitudes", solicitudService.findAll());
	    model.addAttribute("envios", envioService.findAll());
	    model.addAttribute("pagos", pagoService.findAll());
	    model.addAttribute("categorias", categoriaService.findAll());
	    
    	return "crud"; 
    }

    
    
}
