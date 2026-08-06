package intisoft2025.practica.service.implement;

import intisoft2025.practica.exception.BadRequestException;
import intisoft2025.practica.model.DetalleVenta;
import intisoft2025.practica.model.Empresa;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.model.Venta;
import intisoft2025.practica.dto.venta.VentaRequestDTO;
import intisoft2025.practica.dto.venta.DetalleRequestDTO;
import intisoft2025.practica.repository.EmpresaRepository;
import intisoft2025.practica.repository.VentaRepository;
import intisoft2025.practica.repository.ProductoRepository;
import intisoft2025.practica.service.IVentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;
    private final EmpresaRepository empresaRepository;

    /**
     * Inyeccion de dependecias para producto_repository y venta_repository
     * @param productoRepository
     * @param ventaRepository
     */
    public VentaService(ProductoRepository productoRepository, VentaRepository ventaRepository, EmpresaRepository empresaRepository){
        this.empresaRepository = empresaRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta crearVenta(Long id_empresa, VentaRequestDTO productosDto) {

        if(productosDto == null || productosDto.getProductos() == null || productosDto.getProductos().isEmpty()){
            throw new BadRequestException("Debe de aver productos existentes.");
        }
        for (DetalleRequestDTO id: productosDto.getProductos()){
            if (id.getProductoId() == null || id.getProductoId() <= 0){
                throw new BadRequestException("Debe de aver un id valido");
            }
            if(id.getCantidad() <= 0){
                throw new BadRequestException("Debe de ingresar una cantidad valida >= 1");
            }
        }

        // 1. Creamos la venta base ("el recibo")
        Venta nuevaVenta = new Venta();

        // 2. Iteramos sobre los productos del DTO
        if (productosDto.getProductos() != null) {
            for (DetalleRequestDTO item : productosDto.getProductos()) {

                // Buscamos el producto
                Producto producto = productoRepository.findById(item.getProductoId())
                        .orElseThrow(() -> new RuntimeException("Producto con ID " + item.getProductoId() + " no encontrado"));

                //verificamos si este producto pertenence a esta empresa
                if(!producto.getEmpresa().getId().equals(id_empresa)){
                    throw  new RuntimeException("Este producto no pertenece a esta empresa.");
                }

                // Validamos stock
                if (producto.getCantidad() < item.getCantidad()) {
                    throw new RuntimeException("Stock insuficiente para: " + producto.getNombre());
                }

                // Descontamos stock y lo actualizamos
                producto.setCantidad(producto.getCantidad() - item.getCantidad());
                productoRepository.save(producto);

                // Construimos el DetalleVenta
                DetalleVenta detalle = new DetalleVenta();
                detalle.setProducto(producto);
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecio(producto.getPrecio());

                // Usamos el helper de la entidad para el enlace bidireccional
                nuevaVenta.añadirDetalleVenta(detalle);
            }
        }

        // 3. Al guardar Venta, CascadeType.ALL guardará también todos sus detalleVentas
        return ventaRepository.save(nuevaVenta);
    }
}
