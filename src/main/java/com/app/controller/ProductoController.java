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

import com.app.model.Producto;
import com.app.service.CategoriaService;
import com.app.service.ProductoService;

@Controller
@RequestMapping("/admin/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.findAll());
        return "producto";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto, RedirectAttributes redirectAttrs) {
        boolean esNuevo = (producto.getIdProducto() == null);
        productoService.save(producto);

        if (esNuevo) {
            redirectAttrs.addFlashAttribute("mensaje", "El producto se ha guardado correctamente.");
        } else {
            redirectAttrs.addFlashAttribute("mensaje", "El producto se ha editado correctamente.");
        }

        return "redirect:/admin/crud#productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.findAll());
        return "producto";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        productoService.deleteById(id);
        redirectAttrs.addFlashAttribute("mensaje", "El producto se ha eliminado correctamente.");
        return "redirect:/admin/crud#productos";
    }
    
}
