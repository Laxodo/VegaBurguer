package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear

data class CrearPedidoCommand(
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val dat3: String,
    val id_dependiente: String
)