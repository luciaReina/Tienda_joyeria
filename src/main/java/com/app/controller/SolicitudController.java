package com.app.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.DTO.SolicitudDTO;
import com.app.model.Solicitud;
import com.app.model.Usuario;
import com.app.repository.SolicitudRepository;
import com.app.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;  // para buscar el usuario en BD

    @PostMapping
    public Solicitud crearSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
    	System.out.println("Llegó petición con asunto: " + solicitudDTO.getAsunto());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario.get());
        solicitud.setFechaSolicitud(new Date());
        solicitud.setAsunto(solicitudDTO.getAsunto());
        solicitud.setMensaje(solicitudDTO.getMensaje());

        return solicitudRepository.save(solicitud);
    }
}
