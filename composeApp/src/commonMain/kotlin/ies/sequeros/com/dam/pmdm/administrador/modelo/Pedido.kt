package ies.sequeros.com.dam.pmdm.administrador.modelo

data class Pedido (
    var id: String,
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val date: Int
)