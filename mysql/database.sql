DROP DATABASE IF EXISTS mundo;
CREATE DATABASE mundo;
USE mundo;

DROP TABLE IF EXISTS Dependiente;
CREATE TABLE Dependiente(
	id CHAR(64) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
    imgPath VARCHAR(100) NOT NULL,
	enabled BOOLEAN NOT NULL,
    isAdmin BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS Categoria;
CREATE TABLE Categoria(
	id CHAR(64) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    imgPath VARCHAR(100) NOT NULL,
    isActive BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS Producto;
CREATE TABLE Producto(
	id CHAR(64) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    imgPath VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    id_categoria CHAR(64) NOT NULL,
    FOREIGN KEY (id_categoria) REFERENCES Categoria(id)
);

DROP TABLE IF EXISTS Pedido;
CREATE TABLE Pedido(
	id CHAR(64) NOT NULL PRIMARY KEY,
    clientName VARCHAR(50) NOT NULL,
    productNumbers INT NOT NULL,
    pendingProducts INT NOT NULL,
    totalPrice DECIMAL(10, 2) NOT NULL,
    dat3 DATE NOT NULL,
    id_dependiente CHAR(64) NOT NULL,
    FOREIGN KEY (id_dependiente) REFERENCES Dependiente(id)
);

DROP TABLE IF EXISTS LineaPedido;
CREATE TABLE LineaPedido(
	id CHAR(64) NOT NULL PRIMARY KEY,
    amount INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    productPrice DECIMAL(10, 2) NOT NULL,
    delivered BOOLEAN NOT NULL,
    id_pedido CHAR(64) NOT NULL,
    id_producto CHAR(64) NOT NULL,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id)
);