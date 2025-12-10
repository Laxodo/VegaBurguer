package ies.sequeros.com.dam.pmdm.tpv.ui.carrito

import androidx.lifecycle.ViewModel
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.tpv.aplication.lineapedido.LineaPedidoDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter


class CarritoTPVViewModel: ViewModel() {

    private val _items = MutableStateFlow<List<CarritoFormState>>(mutableListOf())
    val items: StateFlow<List<CarritoFormState>> = _items.asStateFlow()

    private val _totalPrice = MutableStateFlow(0.0f)
    private val _totalProducts = MutableStateFlow(0)
    val totalPrice: StateFlow<Float> = _totalPrice.asStateFlow()
    val totalProducts: StateFlow<Int> = _totalProducts.asStateFlow()

    fun addProducto(item:ProductoDTO?){
        if (item == null){return}
        val index = _items.value.indexOfFirst { it.productID == item.id }
        val list = _items.value.toMutableList()
        if (index != -1){
            val producto = _items.value.get(index)
            list.set(index, producto.copy(
                amount = producto.amount + 1,
                total = producto.productPrice * (producto.amount + 1)))
        }else{
            list.add(
                CarritoFormState(
                    amount = 1,
                    productName = item.name,
                    total = item.price,
                    productPrice = item.price,
                    productID = item.id
                )
            )
        }
        recalculate(list)
    }

    fun addProducto(item:CarritoFormState){
        val list = _items.value.toMutableList()
        if (item != null){
            list.set(list.indexOf(item),  item.copy(
                amount = item.amount + 1,
                total = item.productPrice * (item.amount + 1))
            )
        }
        recalculate(list)
    }

    fun removeProducto(item: ProductoDTO?){
        if (item == null){return}
        val index = _items.value.indexOfFirst { it.productID == item.id }
        val list = _items.value.toMutableList()
        if (index != -1){
            val producto = _items.value.get(index)
            if (producto.amount > 1){
                list.set(index, producto.copy(
                    amount = producto.amount - 1,
                    total = producto.productPrice * (producto.amount - 1)))
            }else{
                list.removeAt(index)
            }
            recalculate(list)
        }
    }

    fun removeProducto(item: CarritoFormState){
        val list = _items.value.toMutableList()
        if (item != null){
            if (item.amount > 1){
                list.set(list.indexOf(item),  item.copy(
                    amount = item.amount - 1,
                    total = item.productPrice * (item.amount - 1))
                )
            }else{
                list.removeAt(list.indexOf(item))
            }
        }
        recalculate(list)
    }

    fun recalculate(list: MutableList<CarritoFormState>){
        _items.value = list
        _totalPrice.value = _items.value.sumOf { it.total.toDouble() }.toFloat()
        _totalProducts.value = _items.value.sumOf { it.amount }
    }

}
/*

fun addProducto(item:ProductoDTO?){
        if (item == null){return}
        val index = _items.value.indexOfFirst { it.productID == item.id }
        if (index != -1){
            val producto = _items.value.get(index)
            _items.value = (_items.value + producto.copy(
                amount = producto.amount + 1,
                total = producto.productPrice * (producto.amount + 1))
            )
        }else{
            _items.value = (_items.value + CarritoFormState(
                amount = 1,
                productName = item.name,
                total = item.price,
                productPrice = item.price,
                productID = item.id
            )) as MutableList
        }
        _totalPrice.value = _items.value.sumOf { it.total.toDouble() }.toFloat()
        //_totalPrice.value += item.price
    }

    fun removeProducto(item: ProductoDTO?){
        if (item == null){return}
        val index = _items.value.indexOfFirst { it.productID == item.id }
        if (index != -1){
            val producto = _items.value.get(index)
            if (producto.amount > 1){
                _items.value = (_items.value.filter { it.productID != item.id })
                _items.value = (_items.value + producto.copy(
                    amount = producto.amount - 1,
                    total = producto.productPrice * (producto.amount - 1))
                )
            }else{
                _items.value = (_items.value.filter { it.productID != item.id })
            }
            _totalPrice.value = _items.value.sumOf { it.total.toDouble() }.toFloat()
            //_totalPrice.value -= item.price
        }
    }
}

* */