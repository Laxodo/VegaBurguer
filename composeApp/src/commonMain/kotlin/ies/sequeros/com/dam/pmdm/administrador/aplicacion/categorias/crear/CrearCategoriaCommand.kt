package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.crear

data class CrearCategoriaCommand (
    val name: String,
    val description: String,
    val imgPath: String,
    val enabled: Boolean
)