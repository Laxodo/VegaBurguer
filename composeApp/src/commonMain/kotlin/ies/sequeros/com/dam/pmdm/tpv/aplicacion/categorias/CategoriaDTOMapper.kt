package ies.sequeros.com.dam.pmdm.tpv.aplicacion.categorias

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria

fun Categoria.toDTO(path: String = "") = CategoriaDTO(
    id = id,
    name = name,
    description = description,
    imgPath = path + imgPath,
    enabled = enabled
)

fun CategoriaDTO.toCategoria() = Categoria(
    id = id,
    name = name,
    description = description,
    imgPath = imgPath,
    enabled = enabled
)