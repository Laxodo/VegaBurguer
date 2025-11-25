package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto

fun Producto.toDTO(categoria: String) = ProductoDTO(
    id = id,
    name = name,
    description = description,
    categoria = categoria,
    imagePath = imagePath,
    enabled = enabled,
    price = price
)
fun ProductoDTO.toProducto()= Producto(
    id = id,
    name = name,
    description = description,
    imagePath = imagePath,
    enabled = enabled,
    price = price
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