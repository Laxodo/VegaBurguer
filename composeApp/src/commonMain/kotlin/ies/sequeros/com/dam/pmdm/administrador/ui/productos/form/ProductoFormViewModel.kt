package ies.sequeros.com.dam.pmdm.administrador.ui.productos.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductoFormViewModel (private val item: ProductoDTO?,
                             onSuccess: (ProductoFormState) -> Unit): ViewModel() {

    private val _uiState = MutableStateFlow(ProductoFormState(
        nombre = item?.name ?: "",
        descripcion = item?.description ?: "",
        imagePath = item?.imagePath?:"",
        enabled = item?.enabled?:false,
        price = item?.price?:0.0f

    ))
    val uiState: StateFlow<ProductoFormState> = _uiState.asStateFlow()

    //para saber si el formulario es válido
    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if(item==null)
        state.nombreError == null &&
                state.imagePathError ==null &&

                !state.nombre.isBlank() &&
                state.imagePath.isNotBlank()
    else{
            state.nombreError == null &&
                    state.imagePathError ==null &&
                    !state.nombre.isBlank() &&
                    state.imagePath.isNotBlank()

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNombreChange(v: String) {
        _uiState.value = _uiState.value.copy(nombre = v, nombreError = validateNombre(v))
    }
    fun onImagePathChange(v: String) {
        _uiState.value = _uiState.value.copy(imagePath =  v, imagePathError =  validateImagePath(v))
    }

    fun onEnabledChange(v: Boolean) {
        _uiState.value = _uiState.value.copy(
            enabled =  v

        )
    }

    fun clear() {
        _uiState.value = ProductoFormState()
    }

    private fun validateNombre(nombre: String): String? {
        if (nombre.isBlank()) return "El nombre es obligatorio"
        if (nombre.length < 2) return "El nombre es muy corto"
        return null
    }
    private fun validateImagePath(path: String): String? {
        if (path.isBlank()) return "La imagen es obligatoria"
        return null
    }

    private fun validateConfirmPassword(pw: String, confirm: String): String? {
        if (confirm.isBlank()) return "Confirma la contraseña"
        if (pw != confirm) return "Las contraseñas no coinciden"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nombreErr = validateNombre(s.nombre)
        val imageErr=validateImagePath(s.imagePath)
        val newState = s.copy(
            nombreError = nombreErr,
            imagePathError = imageErr,

            submitted = true
        )
        _uiState.value = newState
        return listOf(nombreErr, imageErr).all { it == null }
    }

    //se le pasan lambdas para ejecutar código en caso de éxito o error
    fun submit(
        onSuccess: (ProductoFormState) -> Unit,
        onFailure: ((ProductoFormState) -> Unit)? = null
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