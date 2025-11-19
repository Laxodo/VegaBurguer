package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Categoria (
    var id: String,
    var nombre: String,
    var descripcion: String,
    var imgPath: String,
    var isActive: Boolean
)