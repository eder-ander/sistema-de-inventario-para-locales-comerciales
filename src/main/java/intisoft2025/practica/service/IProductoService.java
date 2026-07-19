package intisoft2025.practica.service;

import intisoft2025.practica.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto guardarProducto(Producto producto, Long id_empresa);

    Producto actualizarProducto(Long id_empresa, Long id, Producto producto);

    List<Producto> listarProductos(Long id_empresa);

    Producto buscarProducto(Long id_empresa, Long id);
}
