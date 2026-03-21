package intisoft2025.practica.service.ProductoServiceimp;

import intisoft2025.practica.model.Producto;
import intisoft2025.practica.repository.ProductoRepository;
import intisoft2025.practica.service.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;

    /**
     * Inyeccion de dependencias a ProductoRepository
     * @param productoRepository
     */
    public ProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    /**
     * Metodo para guardar un producto
     * @param producto
     * @return
     */
    @Override
    public Producto guardarProducto(Producto producto){
        return productoRepository.save(producto);
    }

    /**
     * Metodo para actualizar un producto
     * @param id
     * @param producto
     * @return
     */
    @Override
    public Producto actualizarProducto(Long id, Producto producto){
        Producto productoBuscar = buscarProducto(id);

        productoBuscar.setPrecio(producto.getPrecio());
        productoBuscar.setNombre(producto.getNombre());
        productoBuscar.setCantidad(producto.getCantidad());

        return productoRepository.save(productoBuscar);
    }

    /**
     * Metodo para buscar un producto
     * @param id
     * @return
     */
    @Override
    public Producto buscarProducto(Long id){
        return productoRepository.findById(id)
                .orElse(null);
    }

    /**
     * Lista de productos
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos(){
        return productoRepository.findAll();
    }

}
