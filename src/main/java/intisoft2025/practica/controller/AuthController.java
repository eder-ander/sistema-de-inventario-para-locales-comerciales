package intisoft2025.practica.controller;

import intisoft2025.practica.dto.empleado.LoginRequest;
import intisoft2025.practica.dto.empleado.LoginResponse;
import intisoft2025.practica.jwt.JwtService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TRADUCCIÓN: "Controlador de Autenticación" (AuthController)
 *
 * Expone los endpoints públicos para autenticarse y obtener tokens JWT.
 */
@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        // 1. Delegamos la autenticación al AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // 2. Obtenemos el UserDetails autenticado
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 3. Generamos el token JWT
        String token = jwtService.generateToken(userDetails);

        // 4. Obtenemos el primer rol para informarlo en la respuesta (opcional)
        String rol = userDetails.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        // 5. Retornamos la respuesta con el token
        return ResponseEntity.ok(new LoginResponse(userDetails.getUsername(), token, rol));
    }
}