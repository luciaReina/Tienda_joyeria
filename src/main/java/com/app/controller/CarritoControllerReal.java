package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.app.service.CarritoService;
import com.app.service.ItemCarritoService;


@Controller
public class CarritoControllerReal {
	
	@Autowired
	private CarritoService carritoService;
	
	@Autowired
	private ItemCarritoService itemCarritoService;
	
}
