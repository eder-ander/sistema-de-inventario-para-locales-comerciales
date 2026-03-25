package intisoft2025.practica.controller;

import intisoft2025.practica.dto.DetalleRequestDTO;
import intisoft2025.practica.dto.RespuestaApi;
import intisoft2025.practica.dto.VentaRequestDTO;
import intisoft2025.practica.exception.BadRequestException;
import intisoft2025.practica.model.Venta;
import intisoft2025.practica.service.IVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    private final IVentaService iVentaService;

    public VentaController(IVentaService ventaService) {
        this.iVentaService = ventaService;
    }

    /**
     * Procesa una nueva venta.
     * Realiza validaciones de entrada antes de delegar al servicio.
     * 
     * @param solicitud DTO con la lista de productos y cantidades.
     * @return ResponseEntity con la venta creada.
     */
    @PostMapping
    public ResponseEntity<RespuestaApi<Venta>> crearVenta(@RequestBody VentaRequestDTO solicitud) {
        if(solicitud == null || solicitud.getProductos() == null || solicitud.getProductos().isEmpty()){
            throw new BadRequestException("Los productos deben de ser existentes");
        }

        for (DetalleRequestDTO id: solicitud.getProductos()){
            if (id.getProductoId() == null || id.getProductoId() <= 0){
                throw new BadRequestException("Debe de aver un id valido");
            }
            if(id.getCantidad() <= 0){
                throw new BadRequestException("Debe de ingresar una cantidad valida >= 1");
            }
        }

        Venta venta = iVentaService.crearVenta(solicitud);
        RespuestaApi<Venta> response = new RespuestaApi<>(true, "Venta creada con exito", venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
