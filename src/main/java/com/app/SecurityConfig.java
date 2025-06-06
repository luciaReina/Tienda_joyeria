package com.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.service.UsuarioService;

@Configuration
@EnableMethodSecurity
public class SecurityConfig  {

	@Autowired
    private  UsuarioService usuarioService;

    
    public SecurityConfig(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(AbstractHttpConfigurer::disable)
        .cors(cors -> cors.disable())
        .authorizeHttpRequests(auth -> auth
        		.requestMatchers(HttpMethod.POST, "/carrito/agregar").permitAll()
        		.requestMatchers(HttpMethod.POST, "/api/solicitudes").permitAll()
                .requestMatchers(HttpMethod.POST,"/auth/registro" ).permitAll()
        		.requestMatchers("/css/**", "/html/**", "/assets/**", "/img/**" , "/js/**", "/registro").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/html/login.html")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/html/index.html", true)
                .failureUrl("/html/login.html?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/html/index.html")
                .permitAll()
            )
            .exceptionHandling(exceptions -> exceptions
                .accessDeniedPage("/html/login.html?accessDenied=true")
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Bean para AuthenticationManager necesario para autenticar con usuarioService
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}