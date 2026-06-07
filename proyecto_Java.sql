-- . Creación de la base de datos
CREATE DATABASE IF NOT EXISTS tecnostore_db;
USE tecnostore_db;

-- 1. Tabla: clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

-- 2. Tabla: celulares
CREATE TABLE IF NOT EXISTS celulares (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    sistema_operativo VARCHAR(30),
    gama VARCHAR(15) NOT NULL, -- Guardaremos aqui el String del Enum (ALTA, MEDIA, BAJA) pa que no se le olvide
    precio DECIMAL(12, 2) NOT NULL,
    stock INT NOT NULL
);

-- 3. Tabla: ventas
CREATE TABLE IF NOT EXISTS ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id) ON DELETE RESTRICT
);

-- 4. Tabla: detalle_ventas (ItemVenta)
CREATE TABLE IF NOT EXISTS detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(12, 2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES ventas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_celular) REFERENCES celulares(id) ON DELETE RESTRICT
);




