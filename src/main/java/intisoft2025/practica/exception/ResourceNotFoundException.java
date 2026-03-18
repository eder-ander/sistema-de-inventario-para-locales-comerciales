package intisoft2025.practica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @ResponseStatus: Al igual que en BadRequestException, aqui le indicamos a
 *                  Spring
 *                  que el codigo de respuesta debe ser un 404 (Not Found).
 * 
 *                  - value = HttpStatus.NOT_FOUND: Indica que un recurso
 *                  buscado no existe.
 * 
 *                  Es vital para que el cliente (como un navegador o App movil)
 *                  sepa que busco un ID
 *                  que no esta en nuestra base de datos.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
