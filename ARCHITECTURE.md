# 📦 Stock Fabrica · Arquitectura del Proyecto


## Índice

1. Filosofía del proyecto
2. Organización del proyecto
3. Dominio
4. Aplicación
5. Infraestructura
6. API
7. Shared
8. Gestión de excepciones
9. Flujo completo de una Request
10. Convenciones del proyecto


---

## 🧠 1. Filosofía del proyecto
La arquitectura de este proyecto nace de una idea muy sencilla:

> **El código debe expresar el negocio antes que la tecnología.**

Cada decisión de diseño busca que el proyecto sea fácil de entender, mantener y evolucionar con el paso del tiempo. Para ello, la arquitectura se apoya en una serie de principios que sirven como guía durante todo el desarrollo.

### Principios fundamentales
#### Responsabilidad única

Cada clase debe tener un propósito claro y bien definido. Cuando una clase asume varias responsabilidades, el código se vuelve más difícil de mantener y reutilizar.

#### El dominio dirige la arquitectura

Las reglas del negocio son el centro de la aplicación. Las tecnologías utilizadas (Spring Boot, JPA, PostgreSQL, etc.) son herramientas al servicio del dominio, nunca al contrario.

### Expresar antes que generalizar

No se crean abstracciones por anticipación. Primero aparece una necesidad real, después un patrón repetido y, únicamente cuando aporta valor, se introduce una abstracción.

### La reutilización es una consecuencia

Las clases no se diseñan para ser reutilizadas. Se diseñan con una responsabilidad concreta. Cuando esa responsabilidad está bien definida, la reutilización aparece de forma natural.

### Convenciones antes que excepciones

El proyecto utiliza convenciones comunes de organización, nomenclatura y desarrollo para que todos los módulos mantengan la misma estructura y resulten fáciles de comprender.

### El código debe comunicar

Los nombres de las clases, métodos y paquetes deben permitir entender su responsabilidad sin necesidad de analizar su implementación. Leer el código debe ser lo más parecido posible a leer el lenguaje del negocio.

## Objetivo de la arquitectura

El objetivo no es construir una arquitectura compleja, sino una arquitectura consistente.

Cada nuevo módulo debe desarrollarse siguiendo los mismos principios y convenciones. De este modo, el esfuerzo se centra en modelar el negocio y no en reinventar la estructura del proyecto en cada nueva funcionalidad.
