package ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.form

data class ProductoFormState(
    val nombre: String = "",
    val descripcion: String = "",
    val imagePath:String="default",
    val enabled: Boolean = false,
    val price: Float = 0.0f,
    // errores (null = sin error)
    val nombreError: String? = null,
    val imagePathError:String?=null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)