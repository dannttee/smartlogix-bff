package com.smartlogix.bff.factory;

import com.smartlogix.bff.dto.PedidoDTO;
import com.smartlogix.bff.dto.ProductoDTO;

import java.time.LocalDateTime;

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

    public static PedidoDTO crearPedido(
            String clienteNombre,
            Long productoId,
            String productoNombre,
            Integer cantidad,
            Double precioUnitario,
            String estado
    ) {
        PedidoDTO dto = new PedidoDTO();
        dto.setClienteNombre(clienteNombre);
        dto.setProductoId(productoId);
        dto.setProductoNombre(productoNombre);
        dto.setCantidad(cantidad);
        dto.setPrecioUnitario(precioUnitario);
        dto.setTotal(cantidad * precioUnitario);
        dto.setEstado(estado);
        dto.setFechaPedido(LocalDateTime.now());
        return dto;
    }
}