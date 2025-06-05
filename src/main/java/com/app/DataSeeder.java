package com.app;

import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.app.model.Categoria;
import com.app.model.Envio;
import com.app.model.Pago;
import com.app.model.Pedido;
import com.app.model.Producto;
import com.app.model.Usuario;
import com.app.repository.CategoriaRepository;
import com.app.repository.EnvioRepository;
import com.app.repository.PagoRepository;
import com.app.repository.PedidoRepository;
import com.app.repository.ProductoRepository;
import com.app.repository.UsuarioRepository;

@Configuration
public class DataSeeder {

	@Bean
    public CommandLineRunner initData( ProductoRepository productoRepo,
            PedidoRepository pedidoRepo, EnvioRepository envioRepo,
            UsuarioRepository usuarioRepo, PagoRepository pagoRepo, 
            CategoriaRepository categoriaRepo) {

        return args -> {
        	if (categoriaRepo.count() == 0) { 

                Categoria c1 = new Categoria();
                c1.setTipo("anillo");

                Categoria c2 = new Categoria();
                c2.setTipo("pendientes");

                Categoria c3 = new Categoria();
                c3.setTipo("collares");

                Categoria c4 = new Categoria();
                c4.setTipo("brazaletes");

                categoriaRepo.save(c1);
                categoriaRepo.save(c2);
                categoriaRepo.save(c3);
                categoriaRepo.save(c4);

                System.out.println("Categorías insertadas con éxito.");
            }
        	
        	// Ahora insertamos productos solo si no hay productos
            if (productoRepo.count() == 0) {
                // Traemos las categorías (puede ser por tipo, o si tienes IDs)
            	Categoria anillo = categoriaRepo.findByTipo("anillo").orElseThrow(() -> new RuntimeException("Categoría 'anillo' no encontrada"));
            	Categoria pendientes = categoriaRepo.findByTipo("pendientes").orElseThrow(() -> new RuntimeException("Categoría 'pendientes' no encontrada"));
            	Categoria collares = categoriaRepo.findByTipo("collares").orElseThrow(() -> new RuntimeException("Categoría 'collares' no encontrada"));
                Categoria brazaletes = categoriaRepo.findByTipo("brazaletes").orElseThrow(() -> new RuntimeException("Categoría 'brazaletes' no encontrada"));

                Producto p1 = new Producto();
                p1.setNombre("Anillo de plata");
                p1.setDescripcion("Anillo hecho de plata pura con diseño elegante.");
                p1.setPrecio(50.0);
                p1.setStock(10);
                p1.setCategoria(anillo);
                p1.setEstilo("Oro");

                Producto p2 = new Producto();
                p2.setNombre("Pendientes de oro");
                p2.setDescripcion("Pendientes de oro amarillo, ideales para ocasiones especiales.");
                p2.setPrecio(80.0);
                p2.setStock(15);
                p2.setCategoria(pendientes);
                p2.setEstilo("Azul");

                Producto p3 = new Producto();
                p3.setNombre("Collar de perlas");
                p3.setDescripcion("Collar elegante de perlas blancas naturales.");
                p3.setPrecio(120.0);
                p3.setStock(5);
                p3.setCategoria(collares);
                p3.setEstilo("Plata");

                productoRepo.save(p1);
                productoRepo.save(p2);
                productoRepo.save(p3);

                System.out.println("Productos insertados con éxito.");
            }
            
            if (pedidoRepo.count() == 0) {
                // Traemos usuarios existentes (los primeros 2)
                List<Usuario> usuarios = usuarioRepo.findAll();
                if (usuarios.size() < 2) {
                    throw new RuntimeException("No hay suficientes usuarios para crear pedidos");
                }

                // Traemos productos existentes (los primeros 2)
                List<Producto> productos = productoRepo.findAll();
                if (productos.size() < 2) {
                    throw new RuntimeException("No hay suficientes productos para crear pedidos");
                }

                Pedido pedido1 = new Pedido();
                pedido1.setUsuario(usuarios.get(1));
                pedido1.setProducto(productos.get(1));
                pedido1.setPrecioTotal(productos.get(1).getPrecio());
                pedido1.setEstado("entregado");
                pedido1.setFechaPedido(new Date());

                Pedido pedido2 = new Pedido();
                pedido2.setUsuario(usuarios.get(2));
                pedido2.setProducto(productos.get(2));
                pedido2.setPrecioTotal(productos.get(2).getPrecio());
                pedido2.setEstado("no entregado");
                pedido2.setFechaPedido(new Date());

                pedidoRepo.save(pedido1);
                pedidoRepo.save(pedido2);

                System.out.println("Pedidos insertados con éxito.");
            }
        
        if (envioRepo.count() == 0 && pagoRepo.count() == 0) {
            List<Pedido> pedidos = pedidoRepo.findAll();
            if (pedidos.size() < 2) {
                throw new RuntimeException("No hay suficientes pedidos para crear envíos y pagos");
            }

            Pedido pedido1 = pedidos.get(0);
            Pedido pedido2 = pedidos.get(1);

            // === Envío 1 ===
            Envio envio1 = new Envio();
            envio1.setPedido(pedido1);
            envio1.setFechaEnvio(new Date());
            envio1.setEstado("entregado");
            envio1.setCalle("Calle Luna 123");
            envio1.setCiudad("Madrid");
            envio1.setCodigoPostal("28001");
            envio1.setPais("España");

            // === Envío 2 ===
            Envio envio2 = new Envio();
            envio2.setPedido(pedido2);
            envio2.setFechaEnvio(new Date());
            envio2.setEstado("no entregado");
            envio2.setCalle("Calle Sol 456");
            envio2.setCiudad("Barcelona");
            envio2.setCodigoPostal("08001");
            envio2.setPais("España");

            envioRepo.save(envio1);
            envioRepo.save(envio2);

            // === Pago 1 ===
            Pago pago1 = new Pago();
            pago1.setPedido(pedido1);
            pago1.setFechaPago(new Date());

            // === Pago 2 ===
            Pago pago2 = new Pago();
            pago2.setPedido(pedido2);
            pago2.setFechaPago(new Date());

            pagoRepo.save(pago1);
            pagoRepo.save(pago2);

            System.out.println("Envíos y pagos insertados con éxito.");
        }
       
     };
   };
}