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
        date = item?.date?: ""

    ))
    val uiState: StateFlow<PedidoFormState> = _uiState.asStateFlow()

    //para saber si el formulario es válido
    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if(item==null)
        state.nombreError == null &&
        state.productNumberError == null &&
        state.pendingProductsError == null &&
        state.totalPriceError == null &&
        state.dateError == null &&
        !state.clientName.isBlank()
    else{
            state.nombreError == null &&
            !state.clientName.isBlank()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(clientName = v, nombreError = validateNombre(v))
    }

    fun onProductNumberChange(v: String) {
        _uiState.value = _uiState.value.copy(productNumber = v.toInt(), productNumberError = validateProductNumber(v))
    }

    fun onPendingProductsChange(v: String) {
        _uiState.value = _uiState.value.copy(pendingProducts = v.toInt(), pendingProductsError = validatePendingProducts(v))
    }

    fun onPriceChange(v: String) {
        _uiState.value = _uiState.value.copy(totalPrice = v.toFloat(), totalPriceError = validateTotalPrice(v))
    }

    fun onDateChange(v: String) {
        _uiState.value = _uiState.value.copy(date = v, dateError = validateDate(v))
    }

    fun clear() {
        _uiState.value = PedidoFormState()
    }

    private fun validateNombre(nombre: String): String? {
        if (nombre.isBlank()) return "El nombre es obligatorio"
        if (nombre.length < 2) return "El nombre es muy corto"
        return null
    }

    private fun validateProductNumber(productNumber: String): String? {
        if (productNumber.isBlank()) return "El numero de Productos es obligatorio"
        return null
    }

    private fun validatePendingProducts(pendingProducts: String): String? {
        if (pendingProducts.isBlank()) return "El numero de Productos pendientes es obligatorio"
        return null
    }

    private fun validateTotalPrice(totalPrice: String): String? {
        if (totalPrice.isBlank()) return "El precio total es obligatorio"
        return null
    }

    private fun validateDate(date: String): String? {
        if (date.isEmpty()) return "La fecha es obligatoria"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.clientName)
        val productNumberErr = validateProductNumber(s.productNumber.toString())
        val pendingProductErr = validatePendingProducts(s.pendingProducts.toString())
        val totalPriceErr = validateTotalPrice(s.totalPriceError.toString())
        val dateErr = validateDate(s.dateError.toString())
        val newState = s.copy(
            nombreError = nombreErr,
            productNumberError = productNumberErr,
            pendingProductsError = pendingProductErr,
            totalPriceError = totalPriceErr,
            dateError = dateErr,
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