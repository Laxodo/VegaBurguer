package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

data class PedidoDTO (
    val id: String,
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val date: Int)