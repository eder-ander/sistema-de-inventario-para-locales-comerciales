package intisoft2025.practica.dto.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaApi<T> {

    private boolean exito;
    private String mensaje;
    private T datos;

    public RespuestaApi(boolean exito, String mensaje, T datos) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public RespuestaApi() {}
}
