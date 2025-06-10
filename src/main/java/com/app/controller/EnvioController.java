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

import com.app.model.Envio;
import com.app.service.EnvioService;
import com.app.service.PedidoService;

@Controller
@RequestMapping("/admin/envio")
public class EnvioController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EnvioService envioService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("envio", new Envio());
        model.addAttribute("pedidos", pedidoService.findAll());
        return "envio"; // nombre del template Thymeleaf para formulario envío
    }

    @PostMapping("/guardar")
    public String guardarEnvio(@ModelAttribute Envio envio, RedirectAttributes redirectAttrs) {
        boolean esNuevo = (envio.getIdEnvio() == null);
        envioService.save(envio);

        if (esNuevo) {
            redirectAttrs.addFlashAttribute("mensaje", "El envío se ha guardado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "El envío se ha editado correctamente.");
        }

        return "redirect:/admin/crud#envios";
    }

    @GetMapping("/editar/{id}")
    public String editarEnvio(@PathVariable Integer id, Model model) {
        Envio envio = envioService.findById(id);
        model.addAttribute("envio", envio);
        model.addAttribute("pedidos", pedidoService.findAll());
        return "envio";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEnvio(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        envioService.deleteById(id);
        redirectAttrs.addFlashAttribute("mensaje", "El envío se ha eliminado correctamente.");
        return "redirect:/admin/crud#envios";
    }
}
