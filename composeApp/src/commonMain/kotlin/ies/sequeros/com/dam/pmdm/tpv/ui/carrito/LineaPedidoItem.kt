package ies.sequeros.com.dam.pmdm.tpv.ui.carrito

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun LineaPedidoItem(
    item: CarritoFormState,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(item.productName, modifier = Modifier.weight(1f))
        Text(item.total.toString() + "€", modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = onRemove) {
            Text("-")
        }
        Text(item.amount.toString(), modifier = Modifier.padding(horizontal = 8.dp))
        Button(onClick = onAdd) {
            Text("+")
        }
    }
}