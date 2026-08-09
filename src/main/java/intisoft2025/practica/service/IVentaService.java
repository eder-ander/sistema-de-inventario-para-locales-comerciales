package intisoft2025.practica.service;

import intisoft2025.practica.model.Venta;
import intisoft2025.practica.dto.venta.VentaRequestDTO;

public interface IVentaService {

    /**
     *Metodo publico abstracto para crear una venta
     * @param solicitud
     * @return
     */
    Venta crearVenta(Long id_empresa, String dniEmpleado, VentaRequestDTO solicitud);
}
