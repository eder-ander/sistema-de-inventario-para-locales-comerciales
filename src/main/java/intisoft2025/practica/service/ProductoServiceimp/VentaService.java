package intisoft2025.practica.service.ProductoServiceimp;

import intisoft2025.practica.model.DetalleVenta;
import intisoft2025.practica.model.Producto;
import intisoft2025.practica.model.Venta;
import intisoft2025.practica.dto.VentaRequestDTO;
import intisoft2025.practica.dto.DetalleRequestDTO;
import intisoft2025.practica.repository.VentaRepository;
import intisoft2025.practica.repository.ProductoRepository;
import intisoft2025.practica.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VentaService implements IVentaService {

    private ProductoRepository productoRepository;
    private VentaRepository ventaRepository;
    public VentaService(ProductoRepository productoRepository, VentaRepository ventaRepository){
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public Venta crearVenta(VentaRequestDTO productosDto) {

        // 1. Creamos la venta base ("el recibo")
        Venta nuevaVenta = new Venta();

        // 2. Iteramos sobre los productos del DTO
        if (productosDto.getProductos() != null) {
            for (DetalleRequestDTO item : productosDto.getProductos()) {

                // Buscamos el producto
                Producto producto = productoRepository.findById(item.getProductoId())
                        .orElseThrow(() -> new RuntimeException(
                                "Producto con ID " + item.getProductoId() + " no encontrado"));

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
