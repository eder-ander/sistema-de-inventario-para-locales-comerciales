package intisoft2025.practica.service.ProductoServiceimp;

import intisoft2025.practica.model.Venta;
import intisoft2025.practica.service.IVentaService;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Override
    public Venta crearVenta(){
        Venta venta = new Venta();
        return venta;
    }

}
