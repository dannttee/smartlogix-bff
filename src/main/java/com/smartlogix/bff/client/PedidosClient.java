package com.smartlogix.bff.client;

import com.smartlogix.bff.dto.PedidoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class PedidosClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PedidosClient(RestTemplate restTemplate,
                         @Value("${pedidos.ms.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public List<PedidoDTO> getAllPedidos() {
        PedidoDTO[] pedidos = restTemplate.getForObject(
            baseUrl + "/api/pedidos", PedidoDTO[].class);
        return Arrays.asList(pedidos);
    }

    public PedidoDTO getPedidoById(Long id) {
        return restTemplate.getForObject(
            baseUrl + "/api/pedidos/" + id, PedidoDTO.class);
    }

    public PedidoDTO crearPedido(PedidoDTO pedido) {
        return restTemplate.postForObject(
            baseUrl + "/api/pedidos", pedido, PedidoDTO.class);
    }

    public void actualizarPedido(Long id, PedidoDTO pedido) {
        restTemplate.put(baseUrl + "/api/pedidos/" + id, pedido);
    }

    public void eliminarPedido(Long id) {
        restTemplate.delete(baseUrl + "/api/pedidos/" + id);
    }
}