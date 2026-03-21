package intisoft2025.practica.service;

import intisoft2025.practica.model.Venta;
import intisoft2025.practica.dto.VentaRequestDTO;

public interface IVentaService {

    /**
     *Metodo publico abstracto para crear una venta
     * @param solicitud
     * @return
     */
    Venta crearVenta(VentaRequestDTO solicitud);
}
