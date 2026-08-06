package intisoft2025.practica.service.implement;

import intisoft2025.practica.dto.producto.RequestProductoDto;
import intisoft2025.practica.exception.BadRequestException;
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
    public Producto guardarProducto(RequestProductoDto producto, Long id_empresa){
        if (producto.getNombre() == null || producto.getNombre().isEmpty() || producto.getCantidad() == null ||producto.getPrecio() == null ||producto.getPrecio() < 0
                || producto.getCantidad() < 0) {
            throw new BadRequestException("El nombre es obligatorio, la cantidad y precio debe ser mayor o igual a 0, el id de empresa es obligatorio");
        }
        Empresa empresaEncontrada = buscarEmpresa(id_empresa);
        Producto nuevoProducto = new Producto(
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCantidad()
        );
        nuevoProducto.setEmpresa(empresaEncontrada);
        return productoRepository.save(nuevoProducto);
    }

    /**
     * Metodo para actualizar un producto de una empresa
     */
    @Override
    public Producto actualizarProducto(Long id_empresa, Long id_producto, RequestProductoDto producto){
        Producto productoExistente = productoRepository.findById(id_producto)
                .orElseThrow(() -> new RuntimeException("No se encontro producto con id_producto = " + id_producto));

        if(!productoExistente.getEmpresa().getId().equals(id_empresa)){
            throw new RuntimeException("Acceso denegado: El producto no pertenece a tu empresa");
        }

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCantidad(producto.getCantidad());
        return productoRepository.save(productoExistente);
    }

    /**
     * Metodo para buscar un producto
     */
    @Override
    public RequestProductoDto buscarProducto(Long id_empresa, Long id){
        Producto productoBuscar = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontro producto con id = "+id));
        if(!productoBuscar.getEmpresa().getId().equals(id_empresa)){
            throw new RuntimeException("Acceso denegado: El producto no pertenece a tu empresa");
        }
        return new RequestProductoDto(productoBuscar);
    }

    /**
     * Lista de productos de una empresa
     */
    @Override
    @Transactional(readOnly = true)
    public List<RequestProductoDto> listarProductos(Long id_empresa){
        List<RequestProductoDto> dto = productoRepository.findByEmpresaId(id_empresa)
                .stream()
                .map(RequestProductoDto::new)
                .toList();
        return dto;
    }
}
