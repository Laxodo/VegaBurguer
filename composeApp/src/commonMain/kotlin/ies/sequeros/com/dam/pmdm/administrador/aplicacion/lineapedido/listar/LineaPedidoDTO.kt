package ies.sequeros.com.dam.pmdm.administrador.aplicacion.lineapedido.listar

data class LineaPedidoDTO(
    val id: String,
    val amount: Int,
    val total: Float,
    val ProductPrice: Float,
    val delivered: Boolean,
    val pedido: String,
    val producto: String
)