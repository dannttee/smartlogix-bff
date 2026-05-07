# SmartLogix BFF

Backend For Frontend de SmartLogix. Actúa como capa de integración entre el frontend y los microservicios de inventario y pedidos.

## Tecnologías
- Java 17
- Spring Boot 3.2.5
- Maven
- SpringDoc OpenAPI (Swagger)

## Requisitos
- Java 17+
- Maven o usar el wrapper incluido (`mvnw`)
- Microservicios corriendo:
  - Inventario MS en `localhost:8081`
  - Pedidos MS en `localhost:8082`

## Instalación y ejecución

```bash
.\mvnw spring-boot:run
```

## Documentación API
Una vez corriendo, accede al Swagger en:

## Endpoints disponibles
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/bff/productos | Listar productos |
| GET | /api/bff/productos/{id} | Obtener producto |
| POST | /api/bff/productos | Crear producto |
| PUT | /api/bff/productos/{id} | Actualizar producto |
| DELETE | /api/bff/productos/{id} | Eliminar producto |
| GET | /api/bff/pedidos | Listar pedidos |
| GET | /api/bff/pedidos/{id} | Obtener pedido |
| POST | /api/bff/pedidos | Crear pedido |
| PUT | /api/bff/pedidos/{id} | Actualizar pedido |
| DELETE | /api/bff/pedidos/{id} | Eliminar pedido |
