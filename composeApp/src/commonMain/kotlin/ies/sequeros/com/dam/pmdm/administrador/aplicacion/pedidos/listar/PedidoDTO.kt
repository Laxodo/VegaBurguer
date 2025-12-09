package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.lineapedido.listar.LineaPedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido

data class PedidoDTO (
    val id: String,
    val clientName: String,
    val productNumber: Int,
    val pendingProducts: Int,
    val totalPrice: Float,
    val date: String,
    val dependiente: String,
    val listar: List<LineaPedido> = emptyList()
)