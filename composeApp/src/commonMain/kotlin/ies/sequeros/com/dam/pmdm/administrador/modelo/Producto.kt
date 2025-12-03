package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto (
    var id: String,
    val name: String,
    val description: String,
    val price: Float,
    val imgPath: String,
    val enabled: Boolean,
    val id_categoria: String
)

