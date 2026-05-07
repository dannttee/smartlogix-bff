package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.ProductoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class InventarioClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public InventarioClient(RestTemplate restTemplate,
                            @Value("${inventario.ms.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<ProductoDTO> getAllProductos() {
        ProductoDTO[] productos = restTemplate.getForObject(
            baseUrl + "/api/productos", ProductoDTO[].class);
        return Arrays.asList(productos);
    }

    public ProductoDTO getProductoById(Long id) {
        return restTemplate.getForObject(
            baseUrl + "/api/productos/" + id, ProductoDTO.class);
    }

    public ProductoDTO crearProducto(ProductoDTO producto) {
        return restTemplate.postForObject(
            baseUrl + "/api/productos", producto, ProductoDTO.class);
    }

    public void actualizarProducto(Long id, ProductoDTO producto) {
        restTemplate.put(baseUrl + "/api/productos/" + id, producto);
    }

    public void eliminarProducto(Long id) {
        restTemplate.delete(baseUrl + "/api/productos/" + id);
    }
}