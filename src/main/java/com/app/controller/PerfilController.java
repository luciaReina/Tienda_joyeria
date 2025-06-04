package com.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.model.Usuario;
import com.app.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class PerfilController {
	
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/perfil")
    public ResponseEntity<Usuario> obtenerPerfil(Authentication authentication) {
        // Puedes usar 'authentication' para obtener el usuario autenticado
        String username = authentication.getName();

        // Aquí puedes buscar al usuario en tu base de datos por email
        Optional <Usuario> usuario = usuarioService.obtenerUsuarioPorNombre(username);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario.get());
    }
    
}


