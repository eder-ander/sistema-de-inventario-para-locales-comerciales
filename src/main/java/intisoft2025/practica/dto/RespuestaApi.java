package intisoft2025.practica.dto;

/**
 * ApiResponse es una clase generica utilizada para estandarizar la respuesta de
 * la API.
 * Esto ayuda a que el cliente (Frontend) siempre reciba una estructura
 * predecible.
 * 
 * @param <T> El tipo de dato que se incluira en la respuesta (ej. Producto,
 *            List<Producto>, String).
 */
public class RespuestaApi<T> {
    private boolean operacion;
    private String mensaje;
    private T dato;

    public RespuestaApi(boolean operacion, String mensaje, T dato){
        this.operacion = operacion;
        this.mensaje = mensaje;
        this.dato = dato;
    }

    public boolean isOperacion() {
        return operacion;
    }

    public void setOperacion(boolean operacion) {
        this.operacion = operacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }
}
