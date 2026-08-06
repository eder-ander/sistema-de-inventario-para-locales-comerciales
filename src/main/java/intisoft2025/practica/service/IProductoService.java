package intisoft2025.practica.service;

import intisoft2025.practica.dto.producto.RequestProductoDto;
import intisoft2025.practica.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto guardarProducto(RequestProductoDto producto, Long id_empresa);

    Producto actualizarProducto(Long id_empresa, Long id, RequestProductoDto producto);

    List<RequestProductoDto> listarProductos(Long id_empresa);

    RequestProductoDto buscarProducto(Long id_empresa, Long id);
}
