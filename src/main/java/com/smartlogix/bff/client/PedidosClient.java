package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.PedidoDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class PedidosClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PedidosClient(
            RestTemplate restTemplate,
            @Value("${pedidos.ms.url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @CircuitBreaker(name = "pedidos", fallbackMethod = "fallbackGetAllPedidos")
    public List<PedidoDTO> getAllPedidos() {
        PedidoDTO[] pedidos = restTemplate.getForObject(
                baseUrl + "/api/pedidos",
                PedidoDTO[].class
        );
        return pedidos != null ? Arrays.asList(pedidos) : Collections.emptyList();
    }

    public List<PedidoDTO> fallbackGetAllPedidos(Exception ex) {
        return Collections.emptyList();
    }

    @CircuitBreaker(name = "pedidos", fallbackMethod = "fallbackGetPedidoById")
    public PedidoDTO getPedidoById(Long id) {
        return restTemplate.getForObject(
                baseUrl + "/api/pedidos/" + id,
                PedidoDTO.class
        );
    }

    public PedidoDTO fallbackGetPedidoById(Long id, Exception ex) {
        return null;
    }

    @CircuitBreaker(name = "pedidos", fallbackMethod = "fallbackCrearPedido")
    public PedidoDTO crearPedido(PedidoDTO pedido) {
        return restTemplate.postForObject(
                baseUrl + "/api/pedidos",
                pedido,
                PedidoDTO.class
        );
    }

    public PedidoDTO fallbackCrearPedido(PedidoDTO pedido, Exception ex) {
        return null;
    }

    @CircuitBreaker(name = "pedidos", fallbackMethod = "fallbackActualizarPedido")
    public void actualizarPedido(Long id, PedidoDTO pedido) {
        restTemplate.put(baseUrl + "/api/pedidos/" + id, pedido);
    }

    public void fallbackActualizarPedido(Long id, PedidoDTO pedido, Exception ex) {
    }

    @CircuitBreaker(name = "pedidos", fallbackMethod = "fallbackEliminarPedido")
    public void eliminarPedido(Long id) {
        restTemplate.delete(baseUrl + "/api/pedidos/" + id);
    }

    public void fallbackEliminarPedido(Long id, Exception ex) {
    }
}