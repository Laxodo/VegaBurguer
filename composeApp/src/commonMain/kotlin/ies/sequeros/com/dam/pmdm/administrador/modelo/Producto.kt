package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto (
    val id: String,
    val name: String,
    val description: String,
    val imagePath: String,
    val enabled: Boolean,
    val precio: Float
)

