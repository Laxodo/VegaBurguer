package ies.sequeros.com.dam.pmdm.tpv.ui.productos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ListarProductoUseCase
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio

import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductoTPVViewModel(
    //private val administradorViewModel: MainAdministradorViewModel,
    private val productoRepositorio: IProductoRepositorio,
    private val categoriaRepositorio: ICategoriaRepositorio,
    val almacenDatos: AlmacenDatos
) : ViewModel() {
    private val listarProductoUseCase: ListarProductoUseCase

    private val _items = MutableStateFlow<MutableList<ProductoDTO>>(mutableListOf())
    val items: StateFlow<List<ProductoDTO>> = _items.asStateFlow()
    private val _selected = MutableStateFlow<ProductoDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarProductoUseCase = ListarProductoUseCase(productoRepositorio, categoriaRepositorio, almacenDatos)
        viewModelScope.launch {
            var items = listarProductoUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items)
        }
    }

    fun setSelectedProducto(item: ProductoDTO?) {
        _selected.value = item
    }
}