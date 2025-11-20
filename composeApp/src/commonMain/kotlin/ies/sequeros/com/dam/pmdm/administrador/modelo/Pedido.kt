package ies.sequeros.com.dam.pmdm.administrador.modelo

data class Pedido (
    var id: String,
    var clientName: String,
    var productNumber: Int,
    var pendingProducts: Int,
    var totalPrice: Float,
    var date: Int
)