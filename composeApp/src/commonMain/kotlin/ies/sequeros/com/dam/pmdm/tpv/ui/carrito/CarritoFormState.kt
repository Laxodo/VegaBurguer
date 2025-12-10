package ies.sequeros.com.dam.pmdm.tpv.ui.carrito

data class CarritoFormState (
    val amount: Int,
    val productName: String,
    val total: Float,
    val productPrice: Float,
    val productID: String
)