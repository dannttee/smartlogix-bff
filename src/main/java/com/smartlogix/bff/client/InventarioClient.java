package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.ProductoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class InventarioClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public InventarioClient(
            RestTemplate restTemplate,
            @Value("${inventario.ms.url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackGetAllProductos")
    public List<ProductoDTO> getAllProductos() {
        ProductoDTO[] productos = restTemplate.getForObject(
                baseUrl + "/api/productos",
                ProductoDTO[].class
        );
        return productos != null ? Arrays.asList(productos) : Collections.emptyList();
    }

    public List<ProductoDTO> fallbackGetAllProductos(Exception ex) {
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackGetProductoById")
    public ProductoDTO getProductoById(Long id) {
        return restTemplate.getForObject(
                baseUrl + "/api/productos/" + id,
                ProductoDTO.class
        );
    }

    public ProductoDTO fallbackGetProductoById(Long id, Exception ex) {
        return null;
    }

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackCrearProducto")
    public ProductoDTO crearProducto(ProductoDTO producto) {
        return restTemplate.postForObject(
                baseUrl + "/api/productos",
                producto,
                ProductoDTO.class
        );
    }

    public ProductoDTO fallbackCrearProducto(ProductoDTO producto, Exception ex) {
        return null;
    }

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackActualizarProducto")
    public void actualizarProducto(Long id, ProductoDTO producto) {
        restTemplate.put(baseUrl + "/api/productos/" + id, producto);
    }

    public void fallbackActualizarProducto(Long id, ProductoDTO producto, Exception ex) {
    }

    @CircuitBreaker(name = "inventario", fallbackMethod = "fallbackEliminarProducto")
    public void eliminarProducto(Long id) {
        restTemplate.delete(baseUrl + "/api/productos/" + id);
    }

    public void fallbackEliminarProducto(Long id, Exception ex) {
    }
}