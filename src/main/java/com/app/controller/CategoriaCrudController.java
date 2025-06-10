package com.app.controller;

import com.app.model.Categoria;
import com.app.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/categoria")
public class CategoriaCrudController {

    @Autowired
    private CategoriaService categoriaService;

//    @PostMapping("/nueva")
//    public String nuevaCategoria(@ModelAttribute Categoria categoria){
//        categoriaService.save(categoria);
//        return "redirect:/admin/crud#categorias";
//    }

	@PostMapping("/nueva")
	public String nuevaCategoria1(@RequestBody Categoria categoria){
		categoriaService.save(categoria);
		return "redirect:/admin/crud#categorias";
	}

    @GetMapping("/eliminar/{idCategoria}")
    public String eliminarCategoria(@PathVariable Integer idCategoria){
        System.out.println("AQUI LLEGO UEEE");
        categoriaService.deleteById(idCategoria);
        return "redirect:/admin/crud#categorias";
    }
}