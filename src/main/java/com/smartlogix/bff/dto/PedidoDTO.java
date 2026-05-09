package com.smartlogix.bff.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PedidoDTO {
    private Long id;
    private String clienteNombre;
    private Long productoId;
    private String productoNombre;
    private Integer cantidad;
    private Double precioUnitario;
    private Double total;
    private String estado;
    private LocalDateTime fechaPedido;
}