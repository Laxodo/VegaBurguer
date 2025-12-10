package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

data class ProductoDTO (
    val id: String,
    val name: String,
    val description: String,
    val categoria: String,
    val imagePath: String,
    val enabled: Boolean,
    val price: Float
)