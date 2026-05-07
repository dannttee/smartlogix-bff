package com.smartlogix.bff.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PedidoDTO {
    private Long id;
    private String estado;
    private LocalDateTime fecha;
    private List<DetallePedidoDTO> detalles;
}