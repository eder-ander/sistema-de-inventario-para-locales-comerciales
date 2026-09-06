package intisoft2025.practica.controller;

import intisoft2025.practica.dto.producto.RequestProductoDto;
import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.security.CustomUserDetails;
import intisoft2025.practica.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> save(@RequestBody RequestProductoDto producto,
                                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Producto productoSave = productoService.guardarProducto(producto, customUserDetails.getIdEmpresa());
        RequestProductoDto dto = new RequestProductoDto(productoSave);

        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto creado con éxito", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id_producto}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> actualizarProducto(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                     @PathVariable Long id_producto,
                                                                     @RequestBody RequestProductoDto producto) {

        Producto nuevoProducto = productoService.actualizarProducto(customUserDetails.getIdEmpresa(), id_producto, producto);
        RequestProductoDto dto = new RequestProductoDto(nuevoProducto);

        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto actualizado con éxito", dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id_producto}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLEADO')")
    public ResponseEntity<RespuestaApi<RequestProductoDto>> buscarProducto(@PathVariable Long id_empresa, @PathVariable Long id_producto){
        RequestProductoDto buscarProducto = productoService.buscarProducto(id_empresa, id_producto);
        RespuestaApi<RequestProductoDto> response = new RespuestaApi<>(true, "Producto encontrado", buscarProducto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN, EMPLEADO')")
    public ResponseEntity<RespuestaApi<List<RequestProductoDto>>> lista(@PathVariable Long id_empresa) {
        List<RequestProductoDto> productos = productoService.listarProductos(id_empresa);
        RespuestaApi<List<RequestProductoDto>> api = new RespuestaApi<>(true, "Lista de productos", productos);
        return ResponseEntity.ok(api);
    }
}
