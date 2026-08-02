package intisoft2025.practica.service.ProductoServiceimp;

import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.repository.EmpresaRepository;
import intisoft2025.practica.repository.ProductoRepository;
import intisoft2025.practica.service.IProductoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService implements IProductoService {
    private final ProductoRepository productoRepository;
    private final EmpresaRepository empresaRepository;

    /**
     * Inyección de dependencias a ProductoRepository y EmpresaRepository
     */
    public ProductoService(ProductoRepository productoRepository, EmpresaRepository empresaRepository){
        this.productoRepository = productoRepository;
        this.empresaRepository = empresaRepository;
    }

    /**
     * Metodo privado para buscar una empresa
     * @param id_empresa
     * @return
     */
    private Empresa buscarEmpresa(Long id_empresa) {
        return empresaRepository.findById(id_empresa)
                .orElseThrow(() -> new RuntimeException("No se encontro empresa"));
    }

    /**
     * Metodo para guardar un producto
     */
    @Override
    public Producto guardarProducto(Producto producto, Long id_empresa){
        Empresa buscar = buscarEmpresa(id_empresa);
        producto.setEmpresa(buscar);
        return productoRepository.save(producto);
    }

    /**
     * Metodo para actualizar un producto
     */
    @Override
    public Producto actualizarProducto(Long id_empresa, Long id, Producto producto){
        Producto productoBuscar = buscarProducto(id_empresa, id);

        productoBuscar.setPrecio(producto.getPrecio());
        productoBuscar.setNombre(producto.getNombre());
        productoBuscar.setCantidad(producto.getCantidad());

        return productoRepository.save(productoBuscar);
    }

    /**
     * Metodo para buscar un producto
     */
    @Override
    public Producto buscarProducto(Long id_empresa, Long id){
        Producto productoBuscar = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro producto"));

        // validar si ese producto pertenece a la empresa consultada
        if(!productoBuscar.getEmpresa().getId().equals(id_empresa)){
            throw new RuntimeException("Acceso denegado: El producto no pertenece a tu empresa");
        }
        return productoBuscar;
    }

    /**
     * Lista de productos
     */
    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos(Long id_empresa){
        return productoRepository.findByEmpresaId(id_empresa);
    }
}
