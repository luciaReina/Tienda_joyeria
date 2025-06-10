package com.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.DTO.SolicitudDTO;
import com.app.model.Solicitud;
import com.app.model.Usuario;
import com.app.repository.SolicitudRepository;
import com.app.repository.UsuarioRepository;
import com.app.service.SolicitudService;
import com.app.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> crearSolicitud(@Valid @RequestBody SolicitudDTO solicitudDTO, BindingResult result) {
        if (result.hasErrors()) {
            // Aquí puedes enviar los errores de validación al cliente
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);
        if (usuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado o no autenticado");
        }

        Solicitud solicitud = new Solicitud();
        solicitud.setUsuario(usuario.get());
        solicitud.setFechaSolicitud(LocalDateTime.now());
        solicitud.setAsunto(solicitudDTO.getAsunto());
        solicitud.setMensaje(solicitudDTO.getMensaje());

        Solicitud savedSolicitud = solicitudRepository.save(solicitud);

        return ResponseEntity.ok(savedSolicitud);
    }

    @DeleteMapping("/eliminar/{idSolicitud}")
    public ResponseEntity<?> eliminarSolicitud(@PathVariable Integer idSolicitud){
        solicitudRepository.deleteById(idSolicitud);
        return ResponseEntity.ok().build();
    }
    
}