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

```text
Stock = SUM(ENTRADAS) - SUM(SALIDAS)