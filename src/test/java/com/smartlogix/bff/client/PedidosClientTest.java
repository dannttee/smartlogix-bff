package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.PedidoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidosClientTest {

    @Mock
    private RestTemplate restTemplate;

    private PedidosClient pedidosClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pedidosClient = new PedidosClient(restTemplate, "http://localhost:8082");
    }

    @Test
    void getAllPedidos_retornaLista() {
        PedidoDTO p = new PedidoDTO();
        p.setId(1L);
        p.setEstado("PENDIENTE");
        when(restTemplate.getForObject(
                "http://localhost:8082/api/pedidos",
                PedidoDTO[].class)
        ).thenReturn(new PedidoDTO[]{p});

        List<PedidoDTO> result = pedidosClient.getAllPedidos();

        assertEquals(1, result.size());
        assertEquals("PENDIENTE", result.get(0).getEstado());
    }

    @Test
    void getAllPedidos_retornaListaVaciaCuandoNull() {
        when(restTemplate.getForObject(
                "http://localhost:8082/api/pedidos",
                PedidoDTO[].class)
        ).thenReturn(null);

        List<PedidoDTO> result = pedidosClient.getAllPedidos();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getPedidoById_retornaPedido() {
        PedidoDTO p = new PedidoDTO();
        p.setId(2L);
        p.setEstado("ENVIADO");
        when(restTemplate.getForObject(
                "http://localhost:8082/api/pedidos/2",
                PedidoDTO.class)
        ).thenReturn(p);

        PedidoDTO result = pedidosClient.getPedidoById(2L);

        assertNotNull(result);
        assertEquals("ENVIADO", result.getEstado());
    }

    @Test
    void fallbackGetAllPedidos_retornaListaVacia() {
        List<PedidoDTO> result = pedidosClient
                .fallbackGetAllPedidos(new RuntimeException("ms caído"));

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void fallbackGetPedidoById_retornaNull() {
        PedidoDTO result = pedidosClient
                .fallbackGetPedidoById(1L, new RuntimeException("ms caído"));

        assertNull(result);
    }

    @Test
    void fallbackCrearPedido_retornaNull() {
        PedidoDTO dto = new PedidoDTO();
        PedidoDTO result = pedidosClient
                .fallbackCrearPedido(dto, new RuntimeException());

        assertNull(result);
    }
}