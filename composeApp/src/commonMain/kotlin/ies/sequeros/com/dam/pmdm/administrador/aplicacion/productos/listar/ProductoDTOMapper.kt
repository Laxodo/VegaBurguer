package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

fun Producto.toDTO(path: String, categoria: String) = ProductoDTO(
    id = id,
    name = name,
    description = description,
    categoria = categoria,
    imagePath = path+imgPath,
    enabled = enabled,
    price = price
)
fun ProductoDTO.toProducto(id_categoria: String)= Producto(
    id = id,
    name = name,
    description = description,
    imgPath = imagePath,
    enabled = enabled,
    price = price,
    id_categoria = id_categoria
)
/*
val id: String,
    val name: String,
    val description: String,
    val categoria: String,
    val imagePath: String,
    val enabled: Boolean,
    val price: Float
* */