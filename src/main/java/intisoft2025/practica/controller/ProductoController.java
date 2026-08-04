package intisoft2025.practica.controller;

import intisoft2025.practica.dto.RequestProductoDto;
import intisoft2025.practica.dto.RespuestaApi;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ProductoController maneja las peticiones HTTP para el recurso Producto.
 * Ahora utiliza una estructura profesional de respuestas (ApiResponse) y
 * manejo centralizado de excepciones.
 */
@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<Producto>> save(@RequestBody RequestProductoDto producto, @PathVariable Long id_empresa) {
        Producto productoSave = productoService.guardarProducto(producto, id_empresa);
        RespuestaApi<Producto> response = new RespuestaApi<>(true, "Producto creado con exito", productoSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 retorna ahi
    }

    @PutMapping("/{id_empresa}/{id_producto}")
    public ResponseEntity<RespuestaApi<Producto>> actualizarProducto(@PathVariable Long id_empresa,
                                                                     @PathVariable Long id_producto,
                                                                     @RequestBody RequestProductoDto producto) {
        RequestProductoDto buscarProducto = productoService.buscarProducto(id_empresa,id_producto);
        Producto nuevoProducto = productoService.actualizarProducto(id_empresa, id_producto, producto);
        RespuestaApi<Producto> response = new RespuestaApi<>(true, "Producto actualizado con exito", nuevoProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<List<RequestProductoDto>>> lista(@PathVariable Long id_empresa) {
        List<RequestProductoDto> productos = productoService.listarProductos(id_empresa);
        RespuestaApi<List<RequestProductoDto>> api = new RespuestaApi<>(true, "lista de productos", productos);
        return ResponseEntity.ok(api);
    }
}
