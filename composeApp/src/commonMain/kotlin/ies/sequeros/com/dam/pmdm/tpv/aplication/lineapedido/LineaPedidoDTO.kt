package ies.sequeros.com.dam.pmdm.tpv.aplication.lineapedido

data class LineaPedidoDTO(
    val id: String,
    val amount: Int,
    val productName: String,
    val total: Float,
    val ProductPrice: Float,
    val delivered: Boolean,
    val pedido: String,
    val producto: String
)