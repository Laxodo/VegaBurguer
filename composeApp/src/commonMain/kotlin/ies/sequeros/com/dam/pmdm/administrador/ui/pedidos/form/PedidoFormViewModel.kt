package ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PedidoFormViewModel (private val item: PedidoDTO?,
                           onSuccess: (PedidoFormState) -> Unit): ViewModel() {

    private val _uiState = MutableStateFlow(PedidoFormState(
        clientName = item?.clientName ?: "",
        productNumber = item?.productNumber ?: 0,
        pendingProducts = item?.pendingProducts?: 0,
        totalPrice = item?.totalPrice?: 0.0f,
        date = item?.date?: 0

    ))
    val uiState: StateFlow<PedidoFormState> = _uiState.asStateFlow()

    //para saber si el formulario es válido
    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if(item==null)
        state.nombreError == null && !state.clientName.isBlank()
    else{
            state.nombreError == null && !state.clientName.isBlank()

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(clientName = v, nombreError = validateNombre(v))
    }

    fun clear() {
        _uiState.value = PedidoFormState()
    }

    private fun validateNombre(nombre: String): String? {
        if (nombre.isBlank()) return "El nombre es obligatorio"
        if (nombre.length < 2) return "El nombre es muy corto"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.clientName)
        val newState = s.copy(
            nombreError = nombreErr,

            submitted = true
        )
        _uiState.value = newState
        return listOf(nombreErr).all { it == null }
    }

    //se le pasan lambdas para ejecutar código en caso de éxito o error
    fun submit(
        onSuccess: (PedidoFormState) -> Unit,
        onFailure: ((PedidoFormState) -> Unit)? = null
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