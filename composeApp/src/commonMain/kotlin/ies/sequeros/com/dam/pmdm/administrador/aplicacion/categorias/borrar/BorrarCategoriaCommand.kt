package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.borrar

data class BorrarCategoriaCommand (
    val id: String,
    val enabled: Boolean,
)