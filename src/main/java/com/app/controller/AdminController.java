package com.app.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.app.service.ProductoService;
import com.app.service.UsuarioService;

@Controller
public class AdminController {
	
	 @Autowired
	 private UsuarioService usuarioService;
	 
	 @Autowired
	 private ProductoService productoService;

    @GetMapping("/admin/crud")
    public String mostrarCrud(Authentication authentication, Model model) {
//    	model.addAttribute("productos", productoService.findAll());
//    	
//    	// Mientras no tengas servicios para los otros:
    	model.addAttribute("pedidos", Collections.emptyList());
    	model.addAttribute("solicitudes", Collections.emptyList());
    	model.addAttribute("envios", Collections.emptyList());
    	model.addAttribute("pagos", Collections.emptyList());
    	model.addAttribute("categorias", Collections.emptyList());
    	return "crud"; 
    }
    
    
}

//@Controller
//public class AdminController {
//
//    @Autowired
//    private ProductoRepository productoRepo;
//
//    @Autowired
//    private UsuarioRepository usuarioRepo;
//
//    @Autowired
//    private PedidoRepository pedidoRepo;
//
//    @Autowired
//    private SolicitudRepository solicitudRepo;
//
//    @Autowired
//    private EnvioRepository envioRepo;
//
//    @Autowired
//    private PagoRepository pagoRepo;
//
//    @Autowired
//    private CategoriaRepository categoriaRepo;
//
//    @GetMapping("/admin/crud")
//    public String mostrarCrud(Authentication authentication, Model model) {
//        model.addAttribute("title", "Panel Admin");
//        model.addAttribute("productos", productoRepo.findAll());
//        model.addAttribute("usuarios", usuarioRepo.findAll());
//        model.addAttribute("pedidos", pedidoRepo.findAll());
//        model.addAttribute("solicitudes", solicitudRepo.findAll());
//        model.addAttribute("envios", envioRepo.findAll());
//        model.addAttribute("pagos", pagoRepo.findAll());
//        model.addAttribute("categorias", categoriaRepo.findAll());
//
//        return "crud"; // src/main/resources/templates/crud.html
//    }
//}