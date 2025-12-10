package ies.sequeros.com.dam.pmdm.administrador.ui.productos.form

data class ProductoFormState(
    val nombre: String = "",
    val descripcion: String = "",
    val imagePath: String ="default",
    val enabled: Boolean = false,
    val price: String = "",
    val id_categoria: String = "",
    // errores (null = sin error)
    val nombreError: String? = null,
    val descripcionError: String? = null,
    val priceError: String? = null,
    val imagePathError: String? =null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)