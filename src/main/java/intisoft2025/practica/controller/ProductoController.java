package intisoft2025.practica.controller;

import intisoft2025.practica.dto.producto.RequestProductoDto;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> save(@RequestBody RequestProductoDto producto, @PathVariable Long id_empresa) {
        Producto productoSave = productoService.guardarProducto(producto, id_empresa);
        RequestProductoDto dto = new RequestProductoDto(productoSave);

        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto creado con éxito", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id_empresa}/{id_producto}")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> actualizarProducto(@PathVariable Long id_empresa,
                                                                     @PathVariable Long id_producto,
                                                                     @RequestBody RequestProductoDto producto) {

        Producto nuevoProducto = productoService.actualizarProducto(id_empresa, id_producto, producto);
        RequestProductoDto dto = new RequestProductoDto(nuevoProducto);

        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto actualizado con éxito", dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id_empresa}/{id_producto}")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> buscarProducto(@PathVariable Long id_empresa, @PathVariable Long id_producto){
        RequestProductoDto buscarProducto = productoService.buscarProducto(id_empresa, id_producto);
        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto encontrado", buscarProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id_empresa}")
    public ResponseEntity<RespuestaApi<List<RequestProductoDto>>> lista(@PathVariable Long id_empresa) {
        List<RequestProductoDto> productos = productoService.listarProductos(id_empresa);
        RespuestaApi<List<RequestProductoDto>> api = new RespuestaApi<>(true, "Lista de productos", productos);
        return ResponseEntity.ok(api);
    }
}
