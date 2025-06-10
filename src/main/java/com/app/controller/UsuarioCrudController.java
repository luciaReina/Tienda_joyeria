package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.Usuario;
import com.app.service.UsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioCrudController {

	    @Autowired
	    private UsuarioService usuarioService;


	    @GetMapping("/nuevo")
	    public String mostrarFormularioNuevo(Model model) {
	        model.addAttribute("usuario", new Usuario());
	        return "usuario";
	    }

	    @PostMapping("/guardar")
	    public String guardarUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs) {
	        boolean esNuevo = (usuario.getIdUsuario() == null);
	        usuarioService.guardarUsuario(usuario);

	        if (esNuevo) {
	            redirectAttrs.addFlashAttribute("mensaje", "El usuario se ha guardado correctamente.");
	        } else {
	            redirectAttrs.addFlashAttribute("mensaje", "El usuario se ha editado correctamente.");
	        }

	        return "redirect:/admin/crud#usuarios";
	    }

	    @GetMapping("/editar/{id}")
	    public String editarUsuario(@PathVariable Integer id, Model model) {
	    	Usuario usuario = usuarioService.findById(id);
	        model.addAttribute("usuario", usuario);
	        return "usuario";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminarUsuario(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
	        usuarioService.deleteById(id);
	        redirectAttrs.addFlashAttribute("mensaje", "El usuario se ha eliminado correctamente.");
	        return "redirect:/admin/crud#usuarios";
	    }
	    

}

