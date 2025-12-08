package ies.sequeros.com.dam.pmdm.tpv.ui.login

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.form.DependienteFormState
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TPVLoginFormViewModel(
    onSuccess: (DependienteFormState) -> Unit
): ViewModel() {

    private val _uiState = MutableStateFlow(TPVLoginFormState(
        nombre = ""
    ))
    val uiState: StateFlow<TPVLoginFormState> = _uiState.asStateFlow()

    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        state.nombreErr == null && !state.nombre.isBlank()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(nombre = v, nombreErr = validateNombre(v))
    }

    private fun validateNombre(nombre: String): String? {
        if (nombre.isBlank()) return "El nombre es obligatorio"
        if (nombre.length < 2) return "El nombre es muy corto"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.nombre)
        val newState = s.copy(
            nombreErr = nombreErr,
            submitted = true
        )
        _uiState.value = newState
        return listOf(nombreErr).all { it == null }
    }

    fun submit(
        onSuccess: ( TPVLoginFormState) -> Unit,
        onFailure: ((TPVLoginFormState) -> Unit)? = null
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