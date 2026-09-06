package intisoft2025.practica.controller;

import intisoft2025.practica.dto.common.RespuestaApi;
import intisoft2025.practica.dto.venta.VentaRequestDTO;
import intisoft2025.practica.dto.venta.VentaResponseDTO;
import intisoft2025.practica.model.Venta;
import intisoft2025.practica.security.CustomUserDetails;
import intisoft2025.practica.service.IVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final IVentaService iVentaService;

    public VentaController(IVentaService ventaService) {
        this.iVentaService = ventaService;
    }

    /**
     * Procesa una nueva venta.
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN, EMPLEADO')")
    public ResponseEntity<RespuestaApi<VentaResponseDTO>> crearVenta(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                                     @RequestBody VentaRequestDTO solicitud) {
        Venta venta = iVentaService.crearVenta(customUserDetails.getIdEmpresa(), customUserDetails.getDni(), solicitud);
        VentaResponseDTO dto = new VentaResponseDTO(venta);
        
        RespuestaApi<VentaResponseDTO> response = new RespuestaApi<>(true, "Venta creada con éxito", dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
