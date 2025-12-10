package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.formChangePassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteFormState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PasswordFormViewModel (private val item: DependienteDTO?,
                             onSuccess: (DependienteFormState) -> Unit): ViewModel() {

    private val _uiState = MutableStateFlow(
        DependienteFormState(
            nombre = item?.name ?: "",
            email = item?.email?:"",
            enabled = item?.enabled?:false,
            imagePath = item?.imagePath?:"",
            isadmin = item?.isAdmin?:false,
            password = "",
            confirmPassword = ""

        )
    )
    val uiState: StateFlow<DependienteFormState> = _uiState.asStateFlow()

    //para saber si el formulario es válido
    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if(item==null)
                state.passwordError == null &&
                state.confirmPasswordError == null &&

                state.password.isNotBlank() &&
                state.confirmPassword.isNotBlank()
    else{
            state.passwordError == null &&
                    state.confirmPasswordError == null &&

                    state.password.isNotBlank() &&
                    state.confirmPassword.isNotBlank()

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onPasswordChange(v: String) {
        _uiState.value = _uiState.value.copy(password = v, passwordError = validatePassword(v))
    }
    fun onConfirmPasswordChange(v: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = v,
            confirmPasswordError = validateConfirmPassword(uiState.value.password, v)
        )
    }

    fun clear() {
        _uiState.value = DependienteFormState()
    }
    private fun validatePassword(pw: String): String? {
        if (pw.isBlank()) return "La contraseña es obligatoria"
        if (pw.length < 8) return "La contraseña debe tener al menos 8 caracteres"
        // opcional: validar mayúsculas/números
        val hasDigit = pw.any { it.isDigit() }
        val hasUpper = pw.any { it.isUpperCase() }
        if (!hasDigit || !hasUpper) return "La contraseña debe incluir mayúscula y número"
        return null
    }

    private fun validateConfirmPassword(pw: String, confirm: String): String? {
        if (confirm.isBlank()) return "Confirma la contraseña"
        if (pw != confirm) return "Las contraseñas no coinciden"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val pwErr = if(item==null) validatePassword(s.password) else null
        val confirmErr = if(item==null)validateConfirmPassword(s.password, s.confirmPassword) else null
        val newState = s.copy(
            passwordError = pwErr,
            confirmPasswordError = confirmErr,

            submitted = true
        )
        _uiState.value = newState
        return listOf(pwErr, confirmErr).all { it == null }
    }

    //se le pasan lambdas para ejecutar código en caso de éxito o error
    fun submit(
        onSuccess: (DependienteFormState) -> Unit,
        onFailure: ((DependienteFormState) -> Unit)? = null
    ) {
        //se ejecuta en una corrutina, evitando que se bloque la interfaz gráficas
        viewModelScope.launch {
            val ok = validateAll()
            if (ok) {
                onSuccess(_uiState.value)
            } else {
                onFailure?.invoke(_uiState.value)
            }
        }
    }

}