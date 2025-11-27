package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar

data class CambiarImagenCategoriaCommand (
    val id: String,
    val enabled: Boolean,
)