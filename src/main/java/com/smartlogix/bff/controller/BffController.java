package com.smartlogix.bff.controller;

import com.smartlogix.bff.client.InventarioClient;
import com.smartlogix.bff.client.PedidosClient;
import com.smartlogix.bff.dto.PedidoDTO;
import com.smartlogix.bff.dto.ProductoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "*")
public class BffController {

    private final InventarioClient inventarioClient;
    private final PedidosClient pedidosClient;

    public BffController(InventarioClient inventarioClient,
                         PedidosClient pedidosClient) {
        this.inventarioClient = inventarioClient;
        this.pedidosClient = pedidosClient;
    }

    // ── Productos ──────────────────────────────────
    @GetMapping("/productos")
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        return ResponseEntity.ok(inventarioClient.getAllProductos());
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Long id) {
        return ResponseEntity.ok(inventarioClient.getProductoById(id));
    }

    @PostMapping("/productos")
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO producto) {
        return ResponseEntity.ok(inventarioClient.crearProducto(producto));
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Void> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO producto) {
        inventarioClient.actualizarProducto(id, producto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        inventarioClient.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // ── Pedidos ────────────────────────────────────
    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoDTO>> getAllPedidos() {
        return ResponseEntity.ok(pedidosClient.getAllPedidos());
    }

    @GetMapping("/pedidos/{id}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable Long id) {
        return ResponseEntity.ok(pedidosClient.getPedidoById(id));
    }

    @PostMapping("/pedidos")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody PedidoDTO pedido) {
        return ResponseEntity.ok(pedidosClient.crearPedido(pedido));
    }

    @PutMapping("/pedidos/{id}")
    public ResponseEntity<Void> actualizarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedido) {
        pedidosClient.actualizarPedido(id, pedido);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/pedidos/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidosClient.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}