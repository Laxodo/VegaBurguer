package ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.formChangePassword

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
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.DependientesViewModel

import ies.sequeros.com.dam.pmdm.commons.ui.ImagenDesdePath
import ies.sequeros.com.dam.pmdm.commons.ui.SelectorImagenComposable

import vegaburguer.composeapp.generated.resources.Res
import vegaburguer.composeapp.generated.resources.hombre


@Composable
fun PasswordForm(
    //appViewModel: AppViewModel,
    dependienteViewModel: DependientesViewModel,
    onClose: () -> Unit,
    onConfirm: (datos: PasswordFormState) -> Unit = {},
    passwordFormularioViewModel: PasswordFormViewModel = viewModel {
        PasswordFormViewModel(
            dependienteViewModel.selected.value, onConfirm
        )
    }
) {
    val state by passwordFormularioViewModel.uiState.collectAsState()
    val formValid by passwordFormularioViewModel.isFormValid.collectAsState()
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    val selected = dependienteViewModel.selected.collectAsState()

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
                        "Crear nuevo usuario"
                    else
                        "Editar usuario",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(8.dp)) // Espacio antes del botón

            //Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

            //  Campos
            //solo se muestra si es nuevo
                //  Contraseñas
                OutlinedTextField(
                    value = state.password,
                    onValueChange = { passwordFormularioViewModel.onPasswordChange(it) },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Ver contraseña"
                            )
                        }
                    },
                    isError = state.passwordError != null
                )
                state.passwordError?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = { passwordFormularioViewModel.onConfirmPasswordChange(it) },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            Icons.Default.CheckCircleOutline,
                            contentDescription = null
                        )
                    },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                            Icon(
                                if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Ver contraseña"
                            )
                        }
                    },
                    isError = state.confirmPasswordError != null
                )
                state.confirmPasswordError?.let {
                    Text(
                        it,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            // Checkboxes
            //  Botones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilledTonalButton(onClick = { passwordFormularioViewModel.clear() }) {
                    Icon(Icons.Default.Autorenew, contentDescription = null)
                    //Spacer(Modifier.width(6.dp))
                    //Text("Limpiar")
                }

                Button(
                    onClick = {
                        passwordFormularioViewModel.submit(
                            onSuccess = {
                                onConfirm(passwordFormularioViewModel.uiState.value)
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






