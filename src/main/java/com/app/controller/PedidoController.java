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

import com.app.model.Pedido;
import com.app.service.PedidoService;
import com.app.service.ProductoService;
import com.app.service.UsuarioService;

@Controller
@RequestMapping("/admin/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("productos", productoService.findAll());
        return "pedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute Pedido pedido, RedirectAttributes redirectAttrs) {
        boolean esNuevo = (pedido.getIdPedido() == null);
        pedidoService.save(pedido);

        if (esNuevo) {
            redirectAttrs.addFlashAttribute("mensaje", "El pedido se ha guardado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "El pedido se ha editado correctamente.");
        }
        return "redirect:/admin/crud#pedidos";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(@PathVariable Integer id, Model model) {
        Pedido pedido = pedidoService.findById(id);
        model.addAttribute("pedido", pedido);
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("productos", productoService.findAll());
        return "pedido";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        pedidoService.deleteById(id);
        redirectAttrs.addFlashAttribute("mensaje", "El pedido se ha eliminado correctamente.");
        return "redirect:/admin/crud#pedidos";
    }
}
