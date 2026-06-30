# 📦 Stock Fabrica - Arquitectura del proyecto.

# Arquitectura

1. **Filosofía del proyecto**

2. **Organización del proyecto**

   2.1 Arquitectura modular

   2.2 Estructura de paquetes

   2.3 Responsabilidad de cada capa



3. **Dominio**

   3.1 Entidades

   3.2 Constraints

   3.3 Excepciones de dominio

   3.4 RequestValidator



4. **Aplicación**

   4.1 Casos de uso

   4.2 Servicios reutilizables

   4.3 Servicios de composición



5. **Infraestructura**

   5.1 Repository

   5.2 Persistencia



6. **API**

   6.1 Controllers

   6.2 Request

   6.3 Response

   6.4 Bean Validation



7. **Shared**

   7.1 Qué pertenece a shared

   7.2 Qué NO pertenece a shared



8. **Gestión de excepciones**

   8.1 Excepciones de dominio

   8.2 ExceptionHandler

   8.3 Responses de error



9. **Flujo completo de una Request**



10. **Convenciones del proyecto**

    10.1 Nomenclatura

    10.2 Organización de módulos

    10.3 Reglas generales


---

# 🧠 1. Filosofía del proyecto
La arquitectura de este proyecto nace de una idea muy sencilla:

El código debe expresar el negocio antes que la tecnología.

Cada decisión de diseño busca que el proyecto sea fácil de entender, mantener y evolucionar con el paso del tiempo. Para ello, la arquitectura se apoya en una serie de principios que sirven como guía durante todo el desarrollo.

Principios fundamentales
Responsabilidad única

Cada clase debe tener un propósito claro y bien definido. Cuando una clase asume varias responsabilidades, el código se vuelve más difícil de mantener y reutilizar.

El dominio dirige la arquitectura

Las reglas del negocio son el centro de la aplicación. Las tecnologías utilizadas (Spring Boot, JPA, PostgreSQL, etc.) son herramientas al servicio del dominio, nunca al contrario.

Expresar antes que generalizar

No se crean abstracciones por anticipación. Primero aparece una necesidad real, después un patrón repetido y, únicamente cuando aporta valor, se introduce una abstracción.

La reutilización es una consecuencia

Las clases no se diseñan para ser reutilizadas. Se diseñan con una responsabilidad concreta. Cuando esa responsabilidad está bien definida, la reutilización aparece de forma natural.

Convenciones antes que excepciones

El proyecto utiliza convenciones comunes de organización, nomenclatura y desarrollo para que todos los módulos mantengan la misma estructura y resulten fáciles de comprender.

El código debe comunicar

Los nombres de las clases, métodos y paquetes deben permitir entender su responsabilidad sin necesidad de analizar su implementación. Leer el código debe ser lo más parecido posible a leer el lenguaje del negocio.

Objetivo de la arquitectura

El objetivo no es construir una arquitectura compleja, sino una arquitectura consistente.

Cada nuevo módulo debe desarrollarse siguiendo los mismos principios y convenciones. De este modo, el esfuerzo se centra en modelar el negocio y no en reinventar la estructura del proyecto en cada nueva funcionalidad.
