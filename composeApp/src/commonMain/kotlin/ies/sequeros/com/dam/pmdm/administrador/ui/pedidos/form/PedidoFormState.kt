package ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.form

data class PedidoFormState(
    val clientName: String = "",
    val productNumber: Int = 0,
    val pendingProducts: Int = 0,
    val totalPrice: Float = 0.0f,
    val date: String = "",
    // errores (null = sin error)
    val nombreError: String? = null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)