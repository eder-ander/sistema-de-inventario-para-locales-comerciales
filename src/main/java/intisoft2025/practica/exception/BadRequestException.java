package intisoft2025.practica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus: Esta anotacion es fundamental en Spring Boot.
 *                  Su funcion es decirle a Spring exactamente que codigo de
 *                  estado HTTP debe devolver
 *                  cuando esta excepcion es lanzada y no es capturada
 *                  manualmente.
 * 
 *                  - value = HttpStatus.BAD_REQUEST: Define que el codigo sera
 *                  el 400 (Bad Request).
 * 
 *                  Sin esta anotacion, Spring por defecto devolveria un 500
 *                  (Internal Server Error),
 *                  lo cual seria incorrecto porque el error es culpa de los
 *                  datos del cliente, no del servidor.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
