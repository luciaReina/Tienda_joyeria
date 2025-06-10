package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.model.Solicitud;
import com.app.service.SolicitudService;
import com.app.service.UsuarioService;

@Controller
@RequestMapping("/admin/solicitud")
public class SolicitudCrudController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SolicitudService solicitudService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNueva(Model model) {
        model.addAttribute("solicitud", new Solicitud());
        model.addAttribute("usuarios", usuarioService.findAll());

        // Suponiendo que los asuntos están definidos como lista o enum
        List<String> asuntos = List.of("Sugerencia", "Problema", "Pedir cita", "Duda");
        model.addAttribute("asuntos", asuntos);

        return "solicitud";
    }

    @PostMapping("/guardar")
    public String guardarSolicitud(@ModelAttribute Solicitud solicitud, RedirectAttributes redirectAttrs) {
        boolean esNueva = (solicitud.getIdSolicitud() == null);
        solicitudService.save(solicitud);

        if (esNueva) {
            redirectAttrs.addFlashAttribute("mensaje", "La solicitud se ha guardado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "La solicitud se ha editado correctamente.");
        }

        return "redirect:/admin/crud#solicitudes";
    }

    @GetMapping("/editar/{id}")
    public String editarSolicitud(@PathVariable Integer id, Model model) {
        Solicitud solicitud = solicitudService.findById(id);
        model.addAttribute("solicitud", solicitud);
        model.addAttribute("usuarios", usuarioService.findAll());

        List<String> asuntos = List.of("Sugerencia", "Problema", "Pedir cita", "Duda");
        model.addAttribute("asuntos", asuntos);

        return "solicitud";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarSolicitud(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        solicitudService.deleteById(id);
        redirectAttrs.addFlashAttribute("mensaje", "La solicitud se ha eliminado correctamente.");
        return "redirect:/admin/crud#solicitudes";
    }

}