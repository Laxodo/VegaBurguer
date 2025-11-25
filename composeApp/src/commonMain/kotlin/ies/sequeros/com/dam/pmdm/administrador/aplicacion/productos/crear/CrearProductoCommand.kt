package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear

data class CrearProductoCommand (
    val id: String,
    val name: String,
    val description: String,
    val imagePath: String,
    val enabled: Boolean,
    val price: Float
)