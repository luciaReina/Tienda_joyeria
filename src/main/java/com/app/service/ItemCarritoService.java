package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.app.repository.ItemCarritoRepository;

@Service
@SessionScope
public class ItemCarritoService {

	@Autowired
    private ItemCarritoRepository itemCarritoRepository;
}

