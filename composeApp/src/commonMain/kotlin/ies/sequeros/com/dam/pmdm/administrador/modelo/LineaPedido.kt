package ies.sequeros.com.dam.pmdm.administrador.modelo

import kotlinx.serialization.Serializable

@Serializable
data class LineaPedido (
    var id:String,
    val amount:Int,
    val total: Float,
    val productPrice: Float,
    val delivered: Boolean,
    val id_pedido: String,
    val id_producto: String
)