package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

fun Pedido.toDTO() = PedidoDTO(
    id = id,
    clientName = clientName,
    productNumber = productNumber,
    pendingProducts = pendingProducts,
    totalPrice = totalPrice,
    date = date
)
fun PedidoDTO.toPedido(id_dependiente: String)= Pedido(
    id = id,
    clientName = clientName,
    productNumber = productNumber,
    pendingProducts = pendingProducts,
    totalPrice = totalPrice,
    date = date,
    id_dependiente = id_dependiente
)