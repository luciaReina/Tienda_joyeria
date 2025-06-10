package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Usuario;
import com.app.service.UsuarioService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registro")
    public String registrarUsuario( @RequestBody Usuario usuario1 ) {
    	
        Usuario usuario = new Usuario();
        usuario.setUsername(usuario1.getUsername());
        usuario.setApellido(usuario1.getApellido());
        usuario.setEmail(usuario1.getEmail());
        usuario.setTelefono(usuario1.getTelefono());
        usuario.setPassword(usuario1.getPassword());
        
        usuario.setPais(usuario1.getPais());
        usuario.setCiudad(usuario1.getCiudad());
        usuario.setCalle(usuario1.getCalle());
        usuario.setCodigoPostal(usuario1.getCodigoPostal());
        usuario.setRol("USER"); // Asignar rol por defecto, puedes cambiarlo

        usuarioService.guardarUsuario(usuario);

        return "redirect:/html/login.html"; // Redirige a la página de login en static
    }
    
    
}