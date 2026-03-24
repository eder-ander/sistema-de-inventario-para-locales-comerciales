package intisoft2025.practica.controller;

import intisoft2025.practica.dto.RespuestaApi;
import intisoft2025.practica.dto.VentaRequestDTO;
import intisoft2025.practica.model.Venta;
import intisoft2025.practica.service.IVentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ventas")
public class VentaController {


    /**
     * @PostMapping
     *     public ResponseEntity<RespuestaApi<Producto>> save(@RequestBody Producto producto) {
     *         if (producto.getNombre() == null || producto.getNombre().isEmpty() || producto.getCantidad() == null
     *                 || producto.getCantidad() < 0) {
     *             throw new BadRequestException("El nombre es obligatorio y la cantidad debe ser mayor o igual a 0");
     *         }
     *
     *         Producto productoSave = productoService.guardarProducto(producto);
     *
     *         RespuestaApi<Producto> response = new RespuestaApi<>(true, "Producto creado con exito", productoSave);
     *         return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 retorna ahi
     *     }
     */

    private final IVentaService iVentaService;
    public VentaController(IVentaService ventaService){
        this.iVentaService = ventaService;
    }


    @PostMapping
    public ResponseEntity<RespuestaApi<Venta>> crearVenta(@RequestBody VentaRequestDTO productos){
        return null;
    }
}
