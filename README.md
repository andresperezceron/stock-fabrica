# 📦 Stock Fabrica - Sistema de Gestión de Almacén

Este proyecto es un sistema backend desarrollado en **Java con Spring Boot**, orientado a la gestión de stock en una fábrica de inyección de plástico destinada principalmente al sector agrícola.

El sistema está diseñado como base evolutiva para un futuro ERP industrial.

---

# 🧠 Arquitectura del sistema

El modelo está basado en **event sourcing simplificado de stock**, donde:

> El stock NO se almacena directamente  
> El stock se calcula a partir de movimientos

---

## 🧱 Entidades principales

### 📦 Producto
Representa los artículos gestionados en el sistema.

- Código único
- Descripción
- Unidad de medida
- Familia
- Estado activo/inactivo

---

### 📦 Lote
Representa agrupaciones de producto con trazabilidad.

- Código de lote
- Origen (producción / proveedor)
- Fecha de creación
- Producto asociado
- Cantidad inicial

---

### 🔄 MovimientoStock
Registra todos los movimientos de inventario.

- Tipo: ENTRADA / SALIDA
- Cantidad
- Fecha
- Lote asociado

---

# ⚙️ Tecnologías utilizadas

- Java 21
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- PostgreSQL
- Maven

---

# 🧮 Lógica de negocio

El stock se calcula dinámicamente:

Stock = SUM(ENTRADAS) - SUM(SALIDAS)

---

# 🚀 Cómo ejecutar el proyecto

1. Requisitos
Java 21+
PostgreSQL
Maven

2. Configuración base de datos:

    Crear base de datos: CREATE DATABASE stock_fabrica;

3. Configuración application.properties:


    spring.datasource.url=jdbc:postgresql://localhost:5432/stock_fabrica
    spring.datasource.username=postgres
    spring.datasource.password=tu_password

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true

4. Ejecutar aplicación:

    mvn spring-boot:run

---

# 📊 Estado actual del proyecto

✔ Modelado de dominio completo

✔ Persistencia con JPA

✔ Relaciones entre entidades

✔ Movimientos de stock funcionales

✔ Cálculo dinámico de stock

---

# 📌 Próximos pasos
API REST con Controllers

Gestión de stock por producto

Validación de stock negativo

UI (Swing / Android / Web)

Separación en arquitectura hexagonal

Migraciones con Flyway
