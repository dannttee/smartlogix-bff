package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.ProductoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InventarioClientTest {

    @Mock
    private RestTemplate restTemplate;

    private InventarioClient inventarioClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inventarioClient = new InventarioClient(restTemplate, "http://localhost:8081");
    }

    @Test
    void getAllProductos_retornaLista() {
        ProductoDTO p = new ProductoDTO();
        p.setId(1L);
        p.setNombre("Laptop");
        when(restTemplate.getForObject(
                "http://localhost:8081/api/productos",
                ProductoDTO[].class)
        ).thenReturn(new ProductoDTO[]{p});

        List<ProductoDTO> result = inventarioClient.getAllProductos();

        assertEquals(1, result.size());
        assertEquals("Laptop", result.get(0).getNombre());
    }

    @Test
    void getAllProductos_retornaListaVaciaCuandoNull() {
        when(restTemplate.getForObject(
                "http://localhost:8081/api/productos",
                ProductoDTO[].class)
        ).thenReturn(null);

        List<ProductoDTO> result = inventarioClient.getAllProductos();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getProductoById_retornaProducto() {
        ProductoDTO p = new ProductoDTO();
        p.setId(1L);
        p.setNombre("Monitor");
        when(restTemplate.getForObject(
                "http://localhost:8081/api/productos/1",
                ProductoDTO.class)
        ).thenReturn(p);

        ProductoDTO result = inventarioClient.getProductoById(1L);

        assertNotNull(result);
        assertEquals("Monitor", result.getNombre());
    }

    @Test
    void fallbackGetAllProductos_retornaListaVacia() {
        List<ProductoDTO> result = inventarioClient
                .fallbackGetAllProductos(new RuntimeException("ms caído"));

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void fallbackGetProductoById_retornaNull() {
        ProductoDTO result = inventarioClient
                .fallbackGetProductoById(1L, new RuntimeException("ms caído"));

        assertNull(result);
    }

    @Test
    void fallbackCrearProducto_retornaNull() {
        ProductoDTO dto = new ProductoDTO();
        ProductoDTO result = inventarioClient
                .fallbackCrearProducto(dto, new RuntimeException());

        assertNull(result);
    }
}