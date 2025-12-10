package ies.sequeros.com.dam.pmdm.tpv.ui

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.ItemOption
import kotlinx.coroutines.flow.MutableStateFlow

data class ItemOption(val icon: ImageVector, val action:()->Unit, val name:String)
class MainTPVViewModel: ViewModel() {
    private val _topOptions= MutableStateFlow<List<ItemOption>>(emptyList())
    private val _bottomOptions= MutableStateFlow<List<ItemOption>>(emptyList())

    fun setTopOptions(options:List<ItemOption>){
        _topOptions.value = options.toList()
    }
    fun setBottomOptions(options:List<ItemOption>){
        _bottomOptions.value = options.toList()
    }
    val filteredTopItems = _topOptions
    val filteredBottomItems = _bottomOptions
}