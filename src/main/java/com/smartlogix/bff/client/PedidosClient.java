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
}