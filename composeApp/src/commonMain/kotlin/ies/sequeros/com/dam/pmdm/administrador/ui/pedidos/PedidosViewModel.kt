package ies.sequeros.com.dam.pmdm.administrador.ui.pedidos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.ListarPedidoUseCase
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PedidosViewModel(
    //private val administradorViewModel: MainAdministradorViewModel,
    private val pedidoRepositorio: IPedidoRepositorio,
    private val productoRepositorio: IProductoRepositorio,
    private val dependienteRepositorio: IDependienteRepositorio,
    private val lineaPedidoRepositorio: ILineaPedidoRepositorio,
     val almacenDatos: AlmacenDatos
) : ViewModel() {
    //los casos de uso se crean dentro para la recomposición
    //se pueden injectar también, se tratará en próximos temas
    private val listarPedidoUseCase: ListarPedidoUseCase

    private val _items = MutableStateFlow<MutableList<PedidoDTO>>(mutableListOf())
    val items: StateFlow<List<PedidoDTO>> = _items.asStateFlow()
    private val _selected = MutableStateFlow<PedidoDTO?>(null)
    val selected = _selected.asStateFlow()

    init {
        listarPedidoUseCase = ListarPedidoUseCase(pedidoRepositorio,productoRepositorio, dependienteRepositorio, lineaPedidoRepositorio, almacenDatos)
        viewModelScope.launch {
            var items: List<PedidoDTO>? = listarPedidoUseCase.invoke()
            _items.value.clear()
            _items.value.addAll(items ?: emptyList())

        }
    }

    fun setSelectedPedido(item: PedidoDTO?) {
        _selected.value = item
    }

    fun delete(item: PedidoDTO) {
        viewModelScope.launch {
          //  borrarProductoUseCase.invoke(item.id)
            _items.update { current ->
                current.filterNot { it.id == item.id }.toMutableList()
            }
        }

    }
}