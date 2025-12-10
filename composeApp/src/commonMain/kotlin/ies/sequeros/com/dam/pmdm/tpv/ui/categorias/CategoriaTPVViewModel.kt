package ies.sequeros.com.dam.pmdm.tpv.ui.categorias

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.ListarCategoriaUseCase

import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoriaTPVViewModel(
    //private val administradorViewModel: MainAdministradorViewModel,
    private val categoriaRepositorio: ICategoriaRepositorio,
     val almacenDatos: AlmacenDatos
) : ViewModel() {
    private val listarCategoriaUseCase: ListarCategoriaUseCase

    private val _items = MutableStateFlow<MutableList<CategoriaDTO>>(mutableListOf())
    val items: StateFlow<List<CategoriaDTO>> = _items.asStateFlow()
    private val _selected = MutableStateFlow<CategoriaDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarCategoriaUseCase = ListarCategoriaUseCase(categoriaRepositorio, almacenDatos)
        viewModelScope.launch {
            var items = listarCategoriaUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items)
        }
    }

    fun setSelectedCategoria(item: CategoriaDTO?) {
        _selected.value = item
    }
}