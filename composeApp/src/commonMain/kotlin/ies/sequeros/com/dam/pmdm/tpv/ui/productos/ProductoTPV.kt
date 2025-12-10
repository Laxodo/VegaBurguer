package ies.sequeros.com.dam.pmdm.tpv.ui.productos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPVViewModel


@Composable
fun ProductoTPV(
    categoriaTPVViewModel: CategoriaTPVViewModel,
    productoViewModel: ProductoTPVViewModel,
    onSelectItem:(ProductoDTO?)->Unit
){
    val items by productoViewModel.items.collectAsState()
    var searchText by remember { mutableStateOf("")}
    val searchId by categoriaTPVViewModel.selected.collectAsState()
    val filteredItems = items.filter {
        if (searchText.isNotBlank()) {
            it.name.contains(searchText, ignoreCase = true)
        }else{
            true
        }
    }
        .filter {
            if(searchId != null){
                searchId?.name == it.categoria
            }else{
                true
            }
        } .filter { it.enabled }

    // Contenedor principal
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 🔹 Barra superior fija con buscador y botón añadir
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                shape = RoundedCornerShape(16.dp),
                placeholder = { Text("Buscar...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription ="Search" ) },
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                minSize = 512.dp
            )
        ){
            items(filteredItems.size) { item ->
                ProductoCardTPV(filteredItems.get(item), {
                    onSelectItem(it)
                })

            }
        }
    }



}