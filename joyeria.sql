CREATE TABLE usuarios (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    clave VARCHAR(50) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_usuario)
);

CREATE TABLE solicitudes (
    id_solicitudes INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    fecha_solicitud TIMESTAMP NOT NULL,
    mensaje VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_solicitudes),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE categorias (
    id_categoria INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_categoria)
);

CREATE TABLE productos (
    id_producto INT NOT NULL AUTO_INCREMENT,
    id_categoria INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    PRIMARY KEY (id_producto),
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE pedidos (
    id_pedido INT NOT NULL AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    fecha_pedido TIMESTAMP NOT NULL,
    precio_total INT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_pedido),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);

CREATE TABLE detalles_pedido (
    id_pedido INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    PRIMARY KEY (id_pedido, id_producto),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE pagos (
    id_pago INT NOT NULL AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    fecha_pago TIMESTAMP NOT NULL,
    estado_pago VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_pago),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);

CREATE TABLE envios (
    id_envio INT NOT NULL AUTO_INCREMENT,
    id_pedido INT NOT NULL,
    fecha_envio TIMESTAMP NOT NULL,
    PRIMARY KEY (id_envio),
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido)
);
