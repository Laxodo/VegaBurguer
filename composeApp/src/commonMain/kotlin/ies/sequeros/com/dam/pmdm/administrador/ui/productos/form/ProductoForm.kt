package ies.sequeros.com.dam.pmdm.administrador.ui.productos.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.CategoriaViewModel

import ies.sequeros.com.dam.pmdm.administrador.ui.productos.ProductosViewModel
import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import ies.sequeros.com.dam.pmdm.commons.ui.SelectorImagenComposable

import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre


@Composable
fun ProductoForm(
    //appViewModel: AppViewModel,
    productoViewModel: ProductosViewModel,
    categoriaViewModel: CategoriaViewModel,
    onClose: () -> Unit,
    onConfirm: (datos: ProductoFormState) -> Unit = {},
    productoFormularioViewModel: ProductoFormViewModel = viewModel {
        ProductoFormViewModel(
            productoViewModel.selected.value ,onConfirm
        )
    }
) {
    val state by productoFormularioViewModel.uiState.collectAsState()
    val formValid by productoFormularioViewModel.isFormValid.collectAsState()
    val selected = productoViewModel.selected.collectAsState()
    val categorias = categoriaViewModel.items.collectAsState()//mutableListOf<CategoriaDTO>()
    val selectedCategory = remember { mutableStateOf(null) }
    val imagePath =
        remember { mutableStateOf(if (state.imagePath != null && state.imagePath.isNotEmpty()) state.imagePath else "") }

    //val names = CartoonString.getNames() - "default"

    //  Scroll state
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .defaultMinSize(minHeight = 200.dp),
        tonalElevation = 4.dp,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .verticalScroll(scrollState), // 👈 Aquí el scroll vertical
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //  Título
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
                Text(
                    text = if (selected == null)
                        "Crear nuevo producto"
                    else
                        "Editar producto",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Espacio antes del botón

            //Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            //  Campos
            OutlinedTextField(
                value = state.nombre,
                onValueChange = { productoFormularioViewModel.onNombreChange(it) },
                label = { Text("Nombre del producto") },
                leadingIcon = { Icon(Icons.Default.PersonOutline, contentDescription = null) },
                isError = state.nombreError != null,
                modifier = Modifier.fillMaxWidth()
            )
            state.nombreError?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            OutlinedTextField(
                value = state.descripcion,
                onValueChange = { productoFormularioViewModel.onDescripcionChange(it) },
                label = { Text("Descripcion del producto") },
                leadingIcon = { Icon(Icons.Default.PersonOutline, contentDescription = null) },
                isError = state.descripcionError != null,
                modifier = Modifier.fillMaxWidth()
            )
            state.descripcionError?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            OutlinedTextField(
                value = state.price.toString(),
                onValueChange = { productoFormularioViewModel.onPriceChange(it) },
                label = { Text("Precio del producto") },
                leadingIcon = { Icon(Icons.Default.PersonOutline, contentDescription = null) },
                isError = state.priceError != null,
                modifier = Modifier.fillMaxWidth()
            )
            state.priceError?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }

            CategoriasComboBox(
                categorias = categorias.value,
                current = selectedCategory.value,
                onSelect = { productoFormularioViewModel.onCategoryChange(it.id) }
            )

            // Checkboxes
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = state.enabled,
                        onCheckedChange = { productoFormularioViewModel.onEnabledChange(it) }
                    )
                    Text("Activo", style = MaterialTheme.typography.bodyMedium)
                }
            }

            //  Selector de avatar
            Text("Selecciona una imagen:", style = MaterialTheme.typography.titleSmall)

            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            val scope = rememberCoroutineScope()
            SelectorImagenComposable({ it: String ->
                productoFormularioViewModel.onImagePathChange(it)//  productoViewModel.almacenDatos.copy(it, "prueba","/productos_imgs/")
                imagePath.value = it
            })

            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            ImagenDesdePath(imagePath, Res.drawable.hombre, Modifier.fillMaxSize())
            state.imagePathError?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            //  Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(onClick = { productoFormularioViewModel.clear() }) {
                    Icon(Icons.Default.Autorenew, contentDescription = null)
                    //Spacer(Modifier.width(6.dp))
                    //Text("Limpiar")
                }

                Button(
                    onClick = {
                        productoFormularioViewModel.submit(
                            onSuccess = {
                                onConfirm(productoFormularioViewModel.uiState.value)
                            },
                            onFailure = {}
                        )
                    },
                    enabled = formValid
                ) {
                    Icon(Icons.Default.Save, contentDescription = null)
                   // Spacer(Modifier.width(6.dp))
                    //Text("" + formValid.toString())
                }

                FilledTonalButton(onClick = { onClose() }) {
                    Icon(Icons.Default.Close, contentDescription = null)
                    //Spacer(Modifier.width(6.dp))
                    //Text("Cancelar")
                }
            }
        }
    }
}






