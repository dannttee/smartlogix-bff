# SmartLogix BFF

Backend For Frontend de SmartLogix. Actúa como capa de integración entre el frontend y los microservicios de inventario y pedidos, centralizando el acceso a los datos desde una sola API.

## Tecnologías
- Java 17
- Spring Boot 3.2.5
- Maven
- SpringDoc OpenAPI (Swagger)

## Requisitos
- Java 17 o superior
- Maven o Maven Wrapper
- Microservicios en ejecución:
  - Inventario MS en `http://localhost:8081`
  - Pedidos MS en `http://localhost:8082`

## Configuración
Este servicio se ejecuta en el puerto `8080`.

## Instalación y ejecución

```bash
./mvnw spring-boot:run
```

En Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

## Documentación API
Una vez iniciado el proyecto, la documentación Swagger está disponible en:

- `http://localhost:8080/swagger-ui.html`
- o `http://localhost:8080/swagger-ui/index.html`

## Endpoints disponibles

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /api/bff/productos | Listar productos |
| GET | /api/bff/productos/{id} | Obtener producto por ID |
| POST | /api/bff/productos | Crear producto |
| PUT | /api/bff/productos/{id} | Actualizar producto |
| DELETE | /api/bff/productos/{id} | Eliminar producto |
| GET | /api/bff/pedidos | Listar pedidos |
| GET | /api/bff/pedidos/{id} | Obtener pedido por ID |
| POST | /api/bff/pedidos | Crear pedido |
| PUT | /api/bff/pedidos/{id} | Actualizar pedido |
| DELETE | /api/bff/pedidos/{id} | Eliminar pedido |

## Prueba rápida
1. Levantar `inventario-ms`.
2. Levantar `pedidos-ms`.
3. Ejecutar este BFF.
4. Verificar respuesta en `http://localhost:8080`.
5. Probar los endpoints desde Swagger o desde el frontend React.

## Autor
Proyecto desarrollado para la asignatura **Desarrollo Fullstack III**.
