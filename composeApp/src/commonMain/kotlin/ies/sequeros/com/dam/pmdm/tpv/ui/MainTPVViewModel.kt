package ies.sequeros.com.dam.pmdm.tpv.ui

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear.CrearPedidoUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.DependienteDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar.ListarDependientesUseCase
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear.CrearPedidoCommand
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.form.PedidoFormState
import ies.sequeros.com.dam.pmdm.tpv.ui.ItemOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class ItemOption(val icon: ImageVector, val action:()->Unit, val name:String)
class MainTPVViewModel(
    private val repositorioPedido: IPedidoRepositorio,
    private val repositorioLineaPedido: ILineaPedidoRepositorio,
    private val repositorioProducto: IProductoRepositorio,
    val repositorioDependiente: IDependienteRepositorio
): ViewModel() {

    private val crearPedidoUseCase: CrearPedidoUseCase
    private val _topOptions= MutableStateFlow<List<ItemOption>>(emptyList())
    private val _bottomOptions= MutableStateFlow<List<ItemOption>>(emptyList())

    init {
        crearPedidoUseCase = CrearPedidoUseCase(repositorioPedido, repositorioLineaPedido, repositorioProducto, repositorioDependiente)
    }

    fun setTopOptions(options:List<ItemOption>){
        _topOptions.value = options.toList()
    }
    fun setBottomOptions(options:List<ItemOption>){
        _bottomOptions.value = options.toList()
    }
    val filteredTopItems = _topOptions
    val filteredBottomItems = _bottomOptions

    fun add(formState: PedidoFormState) {
        val command = CrearPedidoCommand(
            formState.clientName,
            formState.productNumber,
            formState.pendingProducts,
            formState.totalPrice,
            formState.date,
            formState.id_dependiente,
            formState.listar
        )
        viewModelScope.launch {
            try {
                val user = crearPedidoUseCase.invoke(command)
            }catch (e:Exception){
                throw  e
            }

        }
    }
}