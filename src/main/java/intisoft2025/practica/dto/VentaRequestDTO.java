package intisoft2025.practica.dto;

import java.util.List;

public class VentaRequestDTO {
    private List<DetalleRequestDTO> productos;

    /**
     * Constructor predeterminado
     */
    public VentaRequestDTO() {
    }

    public List<DetalleRequestDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<DetalleRequestDTO> productos) {
        this.productos = productos;
    }
}
