# Sistema de Gestión TecnoStore

Proyecto desarrollado para la automatización de procesos de **TecnoStore**, una tienda minorista de dispositivos móviles. El sistema reemplaza el control manual de inventarios y ventas por una solución robusta basada en Java, orientada a objetos y con persistencia en base de datos MySQL.

## 🚀 Propósito del Sistema
El objetivo es optimizar la administración de la tienda mediante un software de consola capaz de gestionar el ciclo de vida de los productos, la base de clientes y la facturación, garantizando la integridad de los datos y el cumplimiento de las reglas de negocio.

## ✅ Requerimientos Cumplidos

### 1. Gestión Integral
* **Celulares:** CRUD completo (Crear, Leer, Actualizar, Borrar) con validaciones de negocio (precios y stock positivos).
* **Clientes:** Registro de clientes con validación de formato de correo electrónico (Regex) y unicidad de identificación.
* **Ventas:** Proceso de facturación con cálculo automático de IVA (19%) y actualización en tiempo real del inventario.

### 2. Análisis y Reportes (Stream API)
* **Monitoreo:** Identificación automática de celulares con stock crítico (< 5 unidades).
* **Inteligencia de Negocio:** Generación de Top 3 de celulares más vendidos.
* **Historial:** Análisis de ventas totales por mes utilizando Stream API.

### 3. Persistencia y Datos
* **Conectividad:** Integración completa con **MySQL** mediante JDBC.
* **Archivos:** Exportación de reportes detallados en formato `.txt` mediante utilidades de manejo de archivos.
* **Seguridad:** Implementación de bloques `try-with-resources` para el manejo seguro de conexiones y flujos.

### 4. Arquitectura y Diseño
* **Patrones de Diseño:** Implementación del **Patrón Strategy** para el cálculo de descuentos dinámicos.
* **Principios:** Aplicación estricta de principios **SOLID**, fomentando el bajo acoplamiento mediante capas (Modelo, Lógica, Persistencia/DAO, Utilidades).

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 17+
* **Base de Datos:** MySQL
* **Persistencia:** JDBC (Java Database Connectivity)
* **Paradigma:** Programación Orientada a Objetos (POO)

## 📂 Estructura del Proyecto
```text
src/
├── model/          # Entidades (Celular, Cliente, Venta, etc.)
├── controller/     # Reglas de negocio (Gestores)
├── persistencia/   # Acceso a datos (DAOs y ConexiónDB)
├── patron/         # Patrones de diseño (Strategy de descuentos)
├── util/           # Clases de apoyo (Validadores, ArchivoUtils)
└── view/           # Menú principal y visualización
