package ies.sequeros.com.dam.pmdm.tpv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.tpv.ui.login.TPVLoginForm

@Suppress("ViewModelConstructorInComposable")
@Composable
fun MainTPV(

    onExit: () -> Unit
) {

    val navController = rememberNavController()

    val navegador: @Composable () -> Unit = {
        NavHost(
            navController,
            startDestination = TPVRoutes.Login
        ) {
            composable(TPVRoutes.Login) {
                TPVLoginForm()
            }
            composable(TPVRoutes.Categorias) {

            }
            composable(TPVRoutes.Productos) {

            }
            composable(TPVRoutes.Carrito) {

            }
        }
    }
}