package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class Pedido (
    var id: String,
    val clientName: String,
    val productNumbers: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val dat3: String,
    val id_dependiente: String
)