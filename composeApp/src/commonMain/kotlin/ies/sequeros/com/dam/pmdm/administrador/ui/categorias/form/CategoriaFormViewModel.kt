package ies.sequeros.com.dam.pmdm.administrador.ui.categorias.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriaFormViewModel(private val item: CategoriaDTO?, onSuccess: (CategoriaFormState) -> Unit): ViewModel() {

    private val _uiState = MutableStateFlow(CategoriaFormState(
        name = item?.name ?: "",
        description = item?.description ?: "",
        imgPath = item?.imgPath ?: "",
        enabled = item?.enabled ?: false
        ))
    val uiState: StateFlow<CategoriaFormState> = _uiState.asStateFlow()
    val isFormValid: StateFlow<Boolean> = uiState.map { state ->
        if(item==null)
            state.nameError == null &&
            state.descriptionError == null &&
            state.imgPathError ==null &&
            !state.name.isBlank() &&
            !state.description.isBlank() &&
            state.imgPath.isNotBlank()
        else{
            state.nameError == null &&
            state.descriptionError == null &&
            state.imgPathError ==null &&
            !state.name.isBlank() &&
            !state.description.isBlank() &&
            state.imgPath.isNotBlank()

        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )

    fun onNameChange(v: String) {
        _uiState.value = _uiState.value.copy(name = v, nameError = validateName(v))
    }

    fun onDescriptionChange(v: String) {
        _uiState.value = _uiState.value.copy(description = v, descriptionError = validateDescription(v))
    }

    fun onImgPathChange(v: String) {
        _uiState.value = _uiState.value.copy(imgPath = v, imgPathError = validateImgPath(v))
    }

    fun onEnabledChange(v: Boolean) {
        _uiState.value = _uiState.value.copy(enabled = v)
    }

    fun clear() {
        _uiState.value = CategoriaFormState()
    }

    private fun validateName(name: String): String? {
        if (name.isBlank()) return "El nombre es obligatorio"
        if (name.length < 2) return "El nombre es muy corto"
        return null
    }

    private fun validateDescription(description: String): String? {
        if (description.isBlank()) return "La descripción es oblifatoria"
        return null
    }

    private fun validateImgPath(path: String): String? {
        if (path.isBlank()) return "La imagn es obligatoria"
        return null
    }

    fun validateAll(): Boolean {
        val s = _uiState.value
        val nameErr = validateName(s.name)
        val descErr = validateDescription(s.description)
        val imgErr = validateImgPath(s.imgPath)
        val newState = s.copy(
            nameError = nameErr,
            descriptionError = descErr,
            imgPathError = imgErr,
            submitted = true
        )
        _uiState.value = newState
        return listOf(nameErr, descErr, imgErr).all { it == null }
    }

    fun submit(
        onSuccess: (CategoriaFormState) -> Unit,
        onFailure: ((CategoriaFormState) -> Unit)? = null
    ) {
        viewModelScope.launch {
            val ok = validateAll()
            if (ok) {
                onSuccess.invoke(_uiState.value)
            } else {
                onFailure?.invoke(_uiState.value)
            }
        }
    }
}