package com.app;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.model.Categoria;
import com.app.model.Producto;
import com.app.model.Solicitud;
import com.app.model.Usuario;
import com.app.repository.CategoriaRepository;
import com.app.repository.EnvioRepository;
import com.app.repository.PagoRepository;
import com.app.repository.PedidoRepository;
import com.app.repository.ProductoRepository;
import com.app.repository.SolicitudRepository;
import com.app.repository.UsuarioRepository;

@Configuration
public class DataSeeder {

	@Bean
    public CommandLineRunner initData(ProductoRepository productoRepo,
                                      PedidoRepository pedidoRepo, EnvioRepository envioRepo,
                                      UsuarioRepository usuarioRepo, PagoRepository pagoRepo,
                                      SolicitudRepository solicitudRepo, CategoriaRepository categoriaRepo,
                                      PasswordEncoder passwordEncoder) {

        return args -> {

            Usuario lilly = usuarioRepo.save(
                    Usuario.builder()
                            .username("lilly")
                            .apellido("Reina")
                            .email("lilly@email.com")
                            .telefono("777777777")
                            .password(passwordEncoder.encode("1234"))
                            .pais("España")
                            .ciudad("Sevilla")
                            .calle("c/La Huerta,31")
                            .codigoPostal("12919")
                            .rol("USER")
                            .build()
            );
            Usuario jero = usuarioRepo.save(
                    Usuario.builder()
                            .username("jero")
                            .apellido("perez")
                            .email("jero@email.com")
                            .telefono("666666666")
                            .password(passwordEncoder.encode("1234"))
                            .pais("España")
                            .ciudad("Almeria")
                            .calle("c/La Pantomima,1")
                            .codigoPostal("01001")
                            .rol("USER")
                            .build()
            );

            solicitudRepo.save(Solicitud.builder()
                    .usuario(jero)
                    .fechaSolicitud(LocalDateTime.now())
                    .asunto("Sugerencia")
                    .mensaje("Podrian regalarme un rolex")
                    .build()

            );
            solicitudRepo.save(Solicitud.builder()
                    .usuario(jero)
                    .fechaSolicitud(LocalDateTime.now())
                    .asunto("Problema")
                    .mensaje("Nunca me llegó el Rolex :(")
                    .build()

            );
//
//            Usuario u1 = new Usuario();
        	if (categoriaRepo.count() == 0) {

                Categoria c1 = new Categoria();
                c1.setTipo("rings");

                Categoria c2 = new Categoria();
                c2.setTipo("earrings");

                Categoria c3 = new Categoria();
                c3.setTipo("necklaces");

                Categoria c4 = new Categoria();
                c4.setTipo("bracelets");

                categoriaRepo.save(c1);
                categoriaRepo.save(c2);
                categoriaRepo.save(c3);
                categoriaRepo.save(c4);

                System.out.println("Categorías insertadas con éxito.");
            }
        	
        	// Ahora insertamos productos solo si no hay productos
            if (productoRepo.count() == 0) {
                // Traemos las categorías (puede ser por tipo, o si tienes IDs)
            	Categoria rings = categoriaRepo.findByTipo("rings").orElseThrow(() -> new RuntimeException("Categoría 'anillos' no encontrada"));
            	Categoria earrings = categoriaRepo.findByTipo("earrings").orElseThrow(() -> new RuntimeException("Categoría 'pendientes' no encontrada"));
            	Categoria necklaces = categoriaRepo.findByTipo("necklaces").orElseThrow(() -> new RuntimeException("Categoría 'collares' no encontrada"));
                Categoria bracelets = categoriaRepo.findByTipo("bracelets").orElseThrow(() -> new RuntimeException("Categoría 'brazaletes' no encontrada"));

                Producto p1 = new Producto();
                p1.setNombre("Gold Ring");
                p1.setDescripcion("Pure silver ring with elegant design.");
                p1.setPrecio(50.0);
                p1.setStock(10);
                p1.setCategoria(rings);
                p1.setEstilo("Gold");
                p1.setImgUrl("cat2.png");

                Producto p2 = new Producto();
                p2.setNombre("Gold Earrings");
                p2.setDescripcion("Gold earrings with blue pearls");
                p2.setPrecio(80.0);
                p2.setStock(15);
                p2.setCategoria(earrings);
                p2.setEstilo("Blue");
                p2.setImgUrl("cat1.png");

                Producto p3 = new Producto();
                p3.setNombre("Pearl Necklace");
                p3.setDescripcion("Elegant necklace with natural green pearls.");
                p3.setPrecio(120.0);
                p3.setStock(5);
                p3.setCategoria(necklaces);
                p3.setEstilo("Silver");
                p3.setImgUrl("cat4.png");

                Producto p4 = new Producto();
                p4.setNombre("Blue Bracelet");
                p4.setDescripcion("Bracelet with blue pearls");
                p4.setPrecio(420.0);
                p4.setStock(12);
                p4.setCategoria(bracelets);
                p4.setEstilo("Blue");
                p4.setImgUrl("cat3.png");

                Producto p5 = new Producto();
                p5.setNombre("Diamond Earrings");
                p5.setDescripcion("4k diamond earrings");
                p5.setPrecio(1000.0);
                p5.setStock(2);
                p5.setCategoria(earrings);
                p5.setEstilo("Diamond");
                p5.setImgUrl("pendientes.png");

                Producto p6 = new Producto();
                p6.setNombre("Silver Bracelet with Blue Pearls");
                p6.setDescripcion("Silver bracelet with bluish design");
                p6.setPrecio(500.0);
                p6.setStock(100);
                p6.setCategoria(bracelets);
                p6.setEstilo("Silver");
                p6.setImgUrl("pulsera.png");

                productoRepo.save(p1);
                productoRepo.save(p2);
                productoRepo.save(p3);
                productoRepo.save(p4);
                productoRepo.save(p5);
                productoRepo.save(p6);

                System.out.println("Productos insertados con éxito.");
            }
            
//            if (pedidoRepo.count() == 0) {
//                // Traemos usuarios existentes (los primeros 2)
//                List<Usuario> usuarios = usuarioRepo.findAll();
//                if (usuarios.size() < 2) {
//                    throw new RuntimeException("No hay suficientes usuarios para crear pedidos");
//                }

                // Traemos productos existentes (los primeros 2)
//                List<Producto> productos = productoRepo.findAll();
//                if (productos.size() < 2) {
//                    throw new RuntimeException("No hay suficientes productos para crear pedidos");
//                }
//
//                Pedido pedido1 = new Pedido();
//                pedido1.setUsuario(usuarios.get(1));
//                pedido1.setProducto(productos.get(1));
//                pedido1.setPrecioTotal(productos.get(1).getPrecio());
//                pedido1.setEstado("entregado");
//                pedido1.setFechaPedido(new Date());
//
//                Pedido pedido2 = new Pedido();
//                pedido2.setUsuario(usuarios.get(2));
//                pedido2.setProducto(productos.get(2));
//                pedido2.setPrecioTotal(productos.get(2).getPrecio());
//                pedido2.setEstado("no entregado");
//                pedido2.setFechaPedido(new Date());
//
//                pedidoRepo.save(pedido1);
//                pedidoRepo.save(pedido2);
//
//                System.out.println("Pedidos insertados con éxito.");
//            }
        
//        if (envioRepo.count() == 0 && pagoRepo.count() == 0) {
//            List<Pedido> pedidos = pedidoRepo.findAll();
//            if (pedidos.size() < 2) {
//                throw new RuntimeException("No hay suficientes pedidos para crear envíos y pagos");
//            }

//            Pedido pedido1 = pedidos.get(0);
//            Pedido pedido2 = pedidos.get(1);

            // === Envío 1 ===
//            Envio envio1 = new Envio();
//            envio1.setPedido(pedido1);
//            envio1.setFechaEnvio(new Date());
//            envio1.setEstado("entregado");
//            envio1.setCalle("Calle Luna 123");
//            envio1.setCiudad("Madrid");
//            envio1.setCodigoPostal("28001");
//            envio1.setPais("España");

            // === Envío 2 ===
//            Envio envio2 = new Envio();
//            envio2.setPedido(pedido2);
//            envio2.setFechaEnvio(new Date());
//            envio2.setEstado("no entregado");
//            envio2.setCalle("Calle Sol 456");
//            envio2.setCiudad("Barcelona");
//            envio2.setCodigoPostal("08001");
//            envio2.setPais("España");

//            envioRepo.save(envio1);
//            envioRepo.save(envio2);

            // === Pago 1 ===
//            Pago pago1 = new Pago();
//            pago1.setPedido(pedido1);
//            pago1.setFechaPago(new Date());

            // === Pago 2 ===
//            Pago pago2 = new Pago();
//            pago2.setPedido(pedido2);
//            pago2.setFechaPago(new Date());

//            pagoRepo.save(pago1);
//            pagoRepo.save(pago2);

//            System.out.println("Envíos y pagos insertados con éxito.");
//        }
       
     };
   };
}