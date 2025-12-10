package ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form

data class CategoriaFormState(
    val name: String = "",
    val description: String = "",
    val imgPath: String = "default",
    val enabled: Boolean = false,
    // errores: null = no error
    val nameError: String? = null,
    val descriptionError: String? = null,
    val imgPathError: String? = null,
    // intent control send
    val submitted: Boolean = false
)