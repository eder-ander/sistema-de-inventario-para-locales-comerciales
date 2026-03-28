package intisoft2025.practica.controller;

import intisoft2025.practica.dto.RequestProductoDto;
import intisoft2025.practica.dto.RespuestaApi;
import intisoft2025.practica.exception.BadRequestException;
import intisoft2025.practica.exception.ResourceNotFoundException;
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

    /**
     * Crea un nuevo producto.
     * Si los datos son invalidos, lanza BadRequestException que es capturada
     * por el GlobalExceptionHandler devolviendo un 400.
     * 
     * @param producto Los datos del producto a guardar.
     * @return ResponseEntity con ApiResponse conteniendo el producto creado.
     */
    @PostMapping
    public ResponseEntity<RespuestaApi<Producto>> save(@RequestBody Producto producto) {
        if (producto.getNombre() == null || producto.getNombre().isEmpty() || producto.getCantidad() == null
                || producto.getCantidad() < 0) {
            throw new BadRequestException("El nombre es obligatorio y la cantidad debe ser mayor o igual a 0");
        }

        Producto productoSave = productoService.guardarProducto(producto);

        RespuestaApi<Producto> response = new RespuestaApi<>(true, "Producto creado con exito", productoSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(response); // 201 retorna ahi
    }



    /**
     * Actualiza un producto existente.
     * 
     * @param id       Identificador del producto a actualizar.
     * @param producto Nuevos datos del producto.
     * @return ResponseEntity con ApiResponse conteniendo el producto actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<RespuestaApi<Producto>> actualizarProducto(@PathVariable Long id,
            @RequestBody Producto producto) {
        Producto buscarProducto = productoService.buscarProducto(id);
        if (buscarProducto == null) {
            throw new ResourceNotFoundException("No se encontro el producto con ID: " + id);
        }
        Producto nuevoProducto = productoService.actualizarProducto(id, producto);
        RespuestaApi<Producto> response = new RespuestaApi<>(true, "Producto actualizado con exito", nuevoProducto);
        return ResponseEntity.ok(response);
    }

    /**
     * Lista todos los productos disponibles.
     * 
     * @return ResponseEntity con ApiResponse conteniendo la lista de productos.
     */
    @GetMapping
    public ResponseEntity<RespuestaApi<List<RequestProductoDto>>> lista() {
        List<Producto> productos = productoService.listarProductos();

        List<RequestProductoDto> dto = productos.stream().map( x -> {
            RequestProductoDto requestProductoDto = new RequestProductoDto();

            requestProductoDto.setId(x.getId());
            requestProductoDto.setNombre(x.getNombre());
            requestProductoDto.setPrecio(x.getPrecio());
            requestProductoDto.setCantidad(x.getCantidad());
            return requestProductoDto;
            }).toList();

        RespuestaApi<List<RequestProductoDto>> api = new RespuestaApi<>(true, "lista de productos", dto);
        return ResponseEntity.ok(api);
    }
}
