package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido

data class CrearPedidoCommand(
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val dat3: String,
    val id_dependiente: String,
    val listar: List<LineaPedido>
)