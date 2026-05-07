package com.smartlogix.bff.dto;

import lombok.Data;

@Data
public class DetallePedidoDTO {
    private Long productoId;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
}