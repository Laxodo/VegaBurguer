package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Producto (
    var id: String,
    var name: String,
    var isActive: Boolean,
    var precio: Float
)

