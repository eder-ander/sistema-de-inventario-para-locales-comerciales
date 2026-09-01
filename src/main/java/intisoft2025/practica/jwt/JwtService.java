package intisoft2025.practica.jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService{

    private final SecretKey secretKey;
    private final long expirationMs;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.expiration-ms}") long expirationMs){
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expirationMs = expirationMs;
    }


    /**
     * TRADUCCIÓN: "Generar Token" (generateToken)
     *
     * Crea un JWT firmado digitalmente con el username como "subject" (sub)
     * y le asigna fecha de emisión (issuedAt) y fecha de expiración (expiration).
     */
    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        Instant expiry = now.plusMillis(expirationMs);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiry))
                .signWith(secretKey)
                .compact();
    }

    /**
     * TRADUCCIÓN: "Extraer Nombre de Usuario" (extractUsername)
     *
     * Lee el payload (claims) del token y obtiene el campo 'sub' (subject),
     * que corresponde al username del usuario autenticado.
     */
    public String extractUsername(String token) {
        return parseClaims(token).getSubject();
    }

    /**
     * TRADUCCIÓN: "¿Es el Token Válido?" (isTokenValid)
     *
     * Comprueba dos cosas:
     * 1. Que el username dentro del token coincida con el UserDetails proporcionado.
     * 2. Que el token no haya caducado (no esté expirado).
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * TRADUCCIÓN: "¿Está el Token Expirado?" (isTokenExpired)
     *
     * Compara la fecha de expiración del token con la fecha/hora actual del sistema.
     * Retorna true si la fecha de expiración ya pasó.
     */
    private boolean isTokenExpired(String token) {
        Instant ahora = Instant.now();
        return parseClaims(token).getExpiration().before(Date.from(ahora));
    }

    /**
     * TRADUCCIÓN: "Analizar / Parsear Reclamaciones (Claims)" (parseClaims)
     *
     * valida la firma del token usando nuestra clave secreta (secretKey)
     * y extrae el cuerpo/payload (claims) del token si la firma es auténtica.
     */
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
