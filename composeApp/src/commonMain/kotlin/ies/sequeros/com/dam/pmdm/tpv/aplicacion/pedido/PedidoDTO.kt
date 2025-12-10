package ies.sequeros.com.dam.pmdm.tpv.aplicacion.pedido

data class PedidoDTO(
    val id: String,
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val date: String)