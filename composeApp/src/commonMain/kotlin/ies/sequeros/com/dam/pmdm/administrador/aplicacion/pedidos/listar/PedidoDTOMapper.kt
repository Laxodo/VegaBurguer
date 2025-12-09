package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido

fun Pedido.toDTO(dependiente: String) = PedidoDTO(
    id = id,
    clientName = clientName,
    productNumber = productNumbers,
    pendingProducts = pendingProducts,
    totalPrice = totalPrice,
    date = dat3,
    dependiente = dependiente
)
fun PedidoDTO.toPedido(id_dependiente: String)= Pedido(
    id = id,
    clientName = clientName,
    productNumbers = productNumber,
    pendingProducts = pendingProducts,
    totalPrice = totalPrice,
    dat3 = date,
    id_dependiente = id_dependiente
)