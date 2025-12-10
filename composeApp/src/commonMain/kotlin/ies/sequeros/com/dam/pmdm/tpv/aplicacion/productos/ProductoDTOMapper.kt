package ies.sequeros.com.dam.pmdm.tpv.aplicacion.productos

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