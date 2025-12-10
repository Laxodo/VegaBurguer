package ies.sequeros.com.dam.pmdm.tpv.aplicacion.productos

data class ProductoDTO (
    val id: String,
    val name: String,
    val description: String,
    val categoria: String,
    val imagePath: String,
    val enabled: Boolean,
    val price: Float
)