package intisoft2025.practica.jwt;

import intisoft2025.practica.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    /**
     * Constructor convencional para Inyección de Dependencias.
     */
    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * TRADUCCIÓN: "Ejecutar Filtrado Interno" (doFilterInternal)
     *
     * Metodo principal del filtro donde se ejecuta la lógica de inspección del token.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        // 1. Extraemos la cabecera "Authorization" de la petición HTTP
        final String authHeader = request.getHeader("Authorization");

        // 2. Si no viene cabecera o no empieza con "Bearer ", dejamos pasar la petición
        // (Spring Security la rechazará más adelante si el endpoint requiere autenticación)
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extraemos el token quitando el prefijo "Bearer " (los primeros 7 caracteres)
        final String token = authHeader.substring(7);

        // 4. Extraemos el username del token usando nuestro JwtService
        final String username = jwtService.extractUsername(token);

        // 5. Si hay username y el usuario aún NO está autenticado en el contexto actual:
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Cargamos los datos del usuario desde la base de datos (UserDetails)
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Validamos que el token pertenezca al usuario y no esté expirado
            if (jwtService.isTokenValid(token, userDetails)) {

                // Creamos el objeto de autenticación con el usuario, sus credenciales (null) y sus roles
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                // Añadimos detalles adicionales de la petición web (como la IP remota o sesión)
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Guardamos la autenticación en el SecurityContextHolder (Spring Security ahora sabe quién es)
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 6. Continuamos con la cadena de filtros hacia el controlador
        filterChain.doFilter(request, response);
    }
}
