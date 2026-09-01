package intisoft2025.practica.config;

import intisoft2025.practica.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    /**
     * TRADUCCIÓN: Constructor de SecurityConfig (Inyección de dependencias convencional)
     *
     * Inyectamos nuestro filtro personalizado JwtAuthenticationFilter.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    /**
     * TRADUCCIÓN: "Codificador de Contraseñas" (passwordEncoder)
     *
     * Define el algoritmo de hashing (BCrypt) para verificar y almacenar contraseñas.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * TRADUCCIÓN: "Administrador de Autenticación" (authenticationManager)
     *
     * Expone el gestor de autenticación de Spring Security para poder inyectarlo
     * en AuthController y validar credenciales (usuario y contraseña).
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * TRADUCCIÓN: "Cadena de Filtros de Seguridad" (securityFilterChain)
     *
     * Configura la seguridad HTTP:
     * 1. Deshabilita CSRF (no necesario en APIs REST stateless con JWT).
     * 2. Define permisos por ruta (públicas para login y endpoints públicos).
     * 3. Configura la política de sesiones como STATELESS (sin sesión en servidor).
     * 4. Registra JwtAuthenticationFilter ANTES de UsernamePasswordAuthenticationFilter.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Deshabilitamos CSRF
                .csrf(csrf -> csrf.disable())

                // 2. Reglas de autorización por URL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/publico/**", "/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 3. Política de sesión SIN ESTADO (Stateless)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 4. Agregamos nuestro filtro JWT antes del filtro estándar de login por formulario
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
