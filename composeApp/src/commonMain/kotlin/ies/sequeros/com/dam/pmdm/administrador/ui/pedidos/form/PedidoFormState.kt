package ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.form

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido

data class PedidoFormState(
    val clientName: String = "",
    val productNumber: Int = 0,
    val pendingProducts: Int = 0,
    val totalPrice: Float = 0.0f,
    val date: String = "",
    val id_dependiente: String = "",
    val listar: List<LineaPedido> = emptyList(),
    // errores (null = sin error)
    val nombreError: String? = null,
    val productNumberError: String? = null,
    val pendingProductsError: String? = null,
    val totalPriceError: String? = null,
    val dateError: String? = null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)