# Arquitecturas Web - Microservicio -> `tarifa_service`

## Descripcion

Microservicio encargado de gestionar las tarifas del sistema.
Permite obtener tarifas por tipo, actualizarlas y calcular el costo final de un viaje combinando kilómetros y minutos de pausa.

## Arquitectura y responsabilidades

Este microservicio trabaja en conjunto con:

* `viaje_service` -> Solicita el calculo del costo de un viaje.
* `pausa_service` -> Influye en el tipo de tarifa aplicada (basica o extra).

Responsabilidades de `tarifa_service`:
* Registrar y administrar tarifas del sistema.
* Determinar la tarifa segun reglas de negocio.
* Calcular el costo de un viaje.
* Proveer un endpoint para ser consumido por otros microservicios.

## Entidad principal 

**Tarifa**
| Campo   | Tipo   | Descripción         |
| ------- | ------ | ------------------- |
| id      | Long   | Identificador único |
| tipo    | Enum   | BASICA / EXTRA      |
| montoKm | Double | Monto por kilómetro |

**TipoTarifa**
* BASICA
* EXTRA

## Endoints disponibles

* POST `/api/tarifas/calcular` -> Calcular costo.
* Recibe: `CostoRequestDTO.java`.

```json
{
  "kilometros": 12.5,
  "minutosPausa": 3.5
}
```
  * Retorna: `CostoResponseDTO.java`.
    
```json
{
  "costo": 123.0,
  "tipoTarifa": "BASICA"
}
```

* GET `/api/tarifas/{tipo}`
  * Obtener tarifa por tipo.
* PUT `/api/tarifas/{tipo}`.
	* Actualiza la tarifa.

## Logica de negocio

1. Se determina el tipo de tarifa:
   * **BASICA** -> cuando la pausa es menor a 15 minutos.
   * **EXTRA** -> cuando el total de pausas en mayor o igual a 15 minutos.
2. Se busca la tarifa correspondiente en la base de datos.
3. Se calcula:
  ```
  costo = kilometros * tarifa.montoKm
  ```
4. Se retorna el total junto con el tipo de tarifa aplicada.
  
## Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Web
* Spring Data JPA
* MySQL

Pendientes de agregar: 
* Swagger
* Autenticacion JWT

## Ejecucion 

Requisistos previos:
* Java 21+
* Maven
* MySQL en ejecucion

Ajustar credenciales en `application.properties`

```
spring.datasource.url=jdbc:mysql://localhost:3306/viaje_db
spring.datasource.username=root
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

## Diagramas del microservicio
Endpoint y dependencias internas

``` mermaid
flowchart LR
%% Estilos globales
%% ----------------------------------------
classDef service fill:#e8f1ff,stroke:#3a6ea5,stroke-width:2px,color:#0b2545,rx:10,ry:10;
classDef db fill:#fff4d6,stroke:#b68b00,stroke-width:2px,color:#4e3b00,rx:10,ry:10;
classDef op fill:#e2ffe9,stroke:#41a35a,stroke-width:2px,color:#1a4e26,rx:10,ry:10;
    subgraph Tarifa_Service ["tarifa_service"]

        C1[POST /tarifas/calcular]:::service --> C2[(Tarifa)]:::db
        C2 -->|Retorna CostoResponseDTO| C3[Costo Final]:::op

    end
```

Diagrama C4

```
%% Estilos globales
graph TD
classDef ms fill:#e3f2fd,stroke:#64b5f6,stroke-width:2px,color:#0d47a1,rx:10px,ry:10px;
classDef db fill:#fff3e0,stroke:#ffb74d,stroke-width:2px,color:#e65100,rx:10px,ry:10px;
classDef comp fill:#e8f5e9,stroke:#81c784,stroke-width:2px,color:#1b5e20,rx:10px,ry:10px;
classDef persona fill:#fce4ec,stroke:#f06292,stroke-width:2px,color:#880e4f,rx:10px,ry:10px;
classDef relation stroke-dasharray: 5 5;

subgraph tarifa_service
    controller([TarifaController]):::comp
    service([TarifaService]):::comp
    repo[(TarifaRepository)]:::db
    entity[[Tarifa]]:::comp
end

controller --> service
service --> repo
service --> entity
```

## Colaboradores

> * Langenheim, Geronimo V.
> * Lopez, Micaela N.
> * San Roman, Emanuel.
> * San Roman, Melanie.
