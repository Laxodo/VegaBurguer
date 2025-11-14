package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.formChangePassword

data class PasswordFormState(
    val password: String = "",
    val confirmPassword: String = "",
    // errores (null = sin error)
    val passwordError: String? = null,
    val confirmPasswordError: String? = null,

    // para controlar si se intentó enviar (mostrar errores globales)
    val submitted: Boolean = false
)