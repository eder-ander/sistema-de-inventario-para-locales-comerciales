package intisoft2025.practica.service.ProductoServiceimp;

import intisoft2025.practica.model.Producto;
import intisoft2025.practica.repository.ProductoRepository;
import intisoft2025.practica.service.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }


    @Override
    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id){
        productoRepository.deleteById(id);
    }

    @Override
    public Producto actualizarProducto(Long id, Producto producto){
        Producto productoBuscar = buscarProducto(id);

        productoBuscar.setPrecio(producto.getPrecio());
        productoBuscar.setNombre(producto.getNombre());
        productoBuscar.setCantidad(producto.getCantidad());

        return productoRepository.save(productoBuscar);
    }

    @Override
    public Producto buscarProducto(Long id){
        return productoRepository.findById(id)
                .orElse(null);
    }

    @Override
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

}
