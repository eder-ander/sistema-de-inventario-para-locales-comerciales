package intisoft2025.practica.exception;

import intisoft2025.practica.dto.RespuestaApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice: Esta es una de las anotaciones más potentes para un
 *                        backend.
 *                        Funciona como un "Interceptor Global" o un
 *                        "Guardaespaldas" de tus controladores.
 *                        ¿Qué hace exactamente?
 *                        1. Combina @ControllerAdvice y @ResponseBody.
 *                        2. Le dice a Spring: "Escucha todo lo que pase en
 *                        CUALQUIER controlador".
 *                        3. Si algún controlador lanza una excepción, en lugar
 *                        de que el programa falle
 *                        o devuelva un error feo, Spring buscará aquí un método
 *                        que sepa manejarla.
 *                        Es nivel Semi-Senior porque desacopla el manejo de
 *                        errores de la lógica de negocio.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja excepciones de tipo ResourceNotFoundException (404).
     * 
     * @param ex La excepcion capturada.
     * @return Una respuesta estandarizada con ApiResponse.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RespuestaApi<?>> handleResourceNotFound(ResourceNotFoundException ex) {
        RespuestaApi<Void> response = new RespuestaApi<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Maneja excepciones de tipo BadRequestException (400).
     * 
     * @param ex La excepcion capturada.
     * @return Una respuesta estandarizada con ApiResponse.
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RespuestaApi<?>> handleBadRequest(BadRequestException ex) {
        RespuestaApi<Void> response = new RespuestaApi<>(false, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Maneja cualquier otra excepcion no controlada (500).
     * Esto evita que la API devuelva un "Stack Trace" interno al cliente.
     * 
     * @param ex La excepcion capturada.
     * @return Una respuesta estandarizada con un mensaje generico de error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<RespuestaApi<?>> handleGlobalException(Exception ex) {
        RespuestaApi<Void> response = new RespuestaApi<>(false, "Ocurrio un error interno en el servidor", null);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
