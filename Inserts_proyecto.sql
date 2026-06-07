USE tecnostore_db;



-- --------------------------------------------------------
-- 1. Insertar Clientes
-- --------------------------------------------------------
INSERT INTO clientes (nombre, identificacion, correo, telefono) VALUES
('Ana Martínez', '1098765432', 'ana.martinez@email.com', '3001234567'),
('Carlos Gómez', '1012345678', 'carlos.gomez@email.com', '3109876543'),
('Luisa Fernanda Ruiz', '1023456789', 'luisa.ruiz@email.com', '3205551234'),
('Javier Ramírez', '1100220033', 'javier.ramirez@email.com', '3156667788'),
('Sofía Castro', '1122334455', 'sofia.castro@email.com', '3112223344');

-- --------------------------------------------------------
-- 2. Insertar Celulares
-- --------------------------------------------------------
INSERT INTO celulares (marca, modelo, sistema_operativo, gama, precio, stock) VALUES
('Apple', 'iPhone 15 Pro Max', 'iOS', 'ALTA', 1200.00, 15),
('Samsung', 'Galaxy S24 Ultra', 'Android', 'ALTA', 1300.00, 10),
('Xiaomi', 'Redmi Note 13 Pro', 'Android', 'MEDIA', 350.00, 30),
('Motorola', 'Moto G84', 'Android', 'MEDIA', 280.00, 25),
('Samsung', 'Galaxy A14', 'Android', 'BAJA', 150.00, 40),
('Apple', 'iPhone 13', 'iOS', 'ALTA', 750.00, 20),
('Xiaomi', 'Poco C65', 'Android', 'BAJA', 120.00, 50);

-- --------------------------------------------------------
-- 3. Insertar Ventas
-- (Los totales están precalculados en base a los detalles de abajo)
-- --------------------------------------------------------
INSERT INTO ventas (id_cliente, fecha, total) VALUES
(1, '2023-10-15 10:30:00', 1200.00), -- Venta 1: Ana compra 1 iPhone 15 Pro Max
(2, '2023-10-16 14:45:00', 700.00),  -- Venta 2: Carlos compra 2 Redmi Note 13 Pro
(3, '2023-10-17 09:15:00', 1450.00), -- Venta 3: Luisa compra 1 S24 Ultra y 1 Galaxy A14
(4, '2023-10-18 16:20:00', 280.00),  -- Venta 4: Javier compra 1 Moto G84
(5, '2023-10-19 11:10:00', 2250.00); -- Venta 5: Sofía compra 3 iPhone 13

-- --------------------------------------------------------
-- 4. Insertar Detalle de Ventas (ItemVenta)
-- --------------------------------------------------------
-- Venta 1 (ID 1) - Total: 1200.00
INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(1, 1, 1, 1200.00); -- 1 x iPhone 15 Pro Max (1200.00)

-- Venta 2 (ID 2) - Total: 700.00
INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(2, 3, 2, 700.00); -- 2 x Redmi Note 13 Pro (350.00 c/u)

-- Venta 3 (ID 3) - Total: 1450.00
INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(3, 2, 1, 1300.00), -- 1 x Galaxy S24 Ultra (1300.00)
(3, 5, 1, 150.00);  -- 1 x Galaxy A14 (150.00)

-- Venta 4 (ID 4) - Total: 280.00
INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(4, 4, 1, 280.00); -- 1 x Moto G84 (280.00)

-- Venta 5 (ID 5) - Total: 2250.00
INSERT INTO detalle_ventas (id_venta, id_celular, cantidad, subtotal) VALUES
(5, 6, 3, 2250.00); -- 3 x iPhone 13 (750.00 c/u)






