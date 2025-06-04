package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.app.model.Usuario;
import com.app.repository.UsuarioRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setEmail("admin@example.com");
            admin.setTelefono(".");
            admin.setEmail("admin@example.com");
            admin.setApellido(".");
            admin.setPais(".");
            admin.setCiudad(".");
            admin.setCalle(".");
            admin.setCodigoPostal(".");
            admin.setPassword(passwordEncoder.encode("admin")); // ⚠️ inseguro para prod
            admin.setRol("ADMIN");

            usuarioRepository.save(admin);
            System.out.println("✔ Usuario ADMIN creado con contraseña 'admin'.");
        } else {
            System.out.println("ℹ Usuario admin ya existe.");
        }
    }
}
