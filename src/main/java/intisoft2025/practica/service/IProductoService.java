package intisoft2025.practica.service;

import intisoft2025.practica.model.Producto;

import java.util.List;

public interface IProductoService {
    Producto guardarProducto(Producto producto);



    Producto actualizarProducto(Long id, Producto producto);

    List<Producto> listarProductos();

    Producto buscarProducto(Long id);
}
