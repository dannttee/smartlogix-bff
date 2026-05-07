package com.smartlogix.bff.factory;

import com.smartlogix.bff.dto.PedidoDTO;
import com.smartlogix.bff.dto.ProductoDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class DtoFactory {

    private DtoFactory() {
    }

    public static ProductoDTO crearProducto(
            String nombre,
            String descripcion,
            Double precio,
            Integer stock
    ) {
        ProductoDTO dto = new ProductoDTO();
        dto.setNombre(nombre);
        dto.setDescripcion(descripcion);
        dto.setPrecio(precio);
        dto.setStock(stock);
        return dto;
    }

    public static PedidoDTO crearPedido(String estado) {
        PedidoDTO dto = new PedidoDTO();
        dto.setEstado(estado);
        dto.setFecha(LocalDateTime.now());
        dto.setDetalles(new ArrayList<>());
        return dto;
    }
}