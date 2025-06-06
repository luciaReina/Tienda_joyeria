package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PiezaController {
	
    @GetMapping("/piezas")
    public String verPiezas(Model model) {
        return "pieza"; // sin .html
    }

}
