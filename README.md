# Microservicio de Pagos — Tienda PC

## Descripción

Microservicio desarrollado con Java y Spring Boot para administrar los registros de pagos de una tienda en línea de componentes de PC.

Permite crear pagos, consultar registros, buscar pagos por pedido, estado o método de pago, actualizar su estado y eliminarlos.

Este proyecto utiliza pagos simulados con fines académicos. No realiza transacciones bancarias reales.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.8
- Spring Data JPA
- MySQL
- Maven
- Git y GitHub

## Configuración

- **Puerto:** 8086
- **Base de datos:** `tienda_pagos_db`
- **URL base:** `http://localhost:8086/api/pagos`

## Funcionalidades

- Registrar pagos con estado inicial `PENDIENTE`.
- Consultar todos los pagos.
- Buscar pagos por ID.
- Consultar pagos asociados a un pedido.
- Filtrar pagos por estado.
- Filtrar pagos por método de pago.
- Buscar pagos por pedido y estado.
- Actualizar el estado de un pago.
- Eliminar registros de pagos.

## Endpoints REST

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/pagos` | Crear pago |
| GET | `/api/pagos` | Listar pagos |
| GET | `/api/pagos/{id}` | Buscar por ID |
| GET | `/api/pagos/pedido/{pedidoId}` | Buscar por pedido |
| GET | `/api/pagos/estado/{estado}` | Buscar por estado |
| GET | `/api/pagos/metodo/{metodoPago}` | Buscar por método de pago |
| GET | `/api/pagos/pedido/{pedidoId}/estado/{estado}` | Filtrar por pedido y estado |
| PUT | `/api/pagos/{id}/estado` | Actualizar estado |
| DELETE | `/api/pagos/{id}` | Eliminar pago |

## Ejemplo para crear un pago

**POST** `/api/pagos`

```json
{
  "pedidoId": 1,
  "monto": 250000,
  "metodoPago": "TRANSFERENCIA"
}
```

El sistema asigna automáticamente el estado inicial `PENDIENTE` y la fecha del registro.

## Ejemplo para actualizar el estado

**PUT** `/api/pagos/1/estado`

```json
{
  "estado": "APROBADO"
}
```

Otros estados posibles para la simulación son `RECHAZADO` y `PENDIENTE`.

## Base de datos

Crear la base de datos en MySQL:

```sql
CREATE DATABASE IF NOT EXISTS tienda_pagos_db;
```

Spring Data JPA e Hibernate administran la tabla `pagos`.

## Ejecución

Configurar la variable de entorno `DB_PASSWORD` con la contraseña local de MySQL.

En PowerShell:

```powershell
$env:DB_PASSWORD = "TU_CONTRASEÑA_MYSQL"
.\mvnw.cmd spring-boot:run
```

## Compilación

```powershell
.\mvnw.cmd clean package
```

## Estructura del proyecto

- `model`: entidad Pago.
- `repository`: acceso a la base de datos.
- `service`: lógica de negocio.
- `controller`: endpoints REST.
- `config`: configuraciones adicionales.

## Alcance actual

El microservicio almacena el identificador del pedido, monto, método de pago, estado y fecha.

No está integrado con una pasarela de pagos real ni verifica automáticamente la existencia del pedido en `ms-pedidos`. Las actualizaciones de estado son simuladas.

## Proyecto académico

Este microservicio forma parte de una arquitectura de ocho microservicios para una tienda de componentes de PC.