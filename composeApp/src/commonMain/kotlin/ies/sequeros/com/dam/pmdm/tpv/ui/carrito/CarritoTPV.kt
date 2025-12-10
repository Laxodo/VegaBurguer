package ies.sequeros.com.dam.pmdm.tpv.ui.carrito

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun CarritoTPV(
    carritoTPVViewModel: CarritoTPVViewModel,
    onComprar: ()-> Unit
) {
    val items by carritoTPVViewModel.items.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(
                minSize = 512.dp
            )
        ) {
            items(items) { item ->
                Spacer(Modifier.width(16.dp))
                LineaPedidoItem(item, {
                    carritoTPVViewModel.addProducto(item)
                }, {
                    carritoTPVViewModel.removeProducto(item)
                })
            }
        }
        Button( onClick = onComprar ){
            Text("Comprar")
        }
    }
}