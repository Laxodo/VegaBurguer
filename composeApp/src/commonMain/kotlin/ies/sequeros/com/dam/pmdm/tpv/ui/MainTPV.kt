package ies.sequeros.com.dam.pmdm.tpv.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.administrador.ui.AdminRoutes
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.login.TPVLoginForm
import ies.sequeros.com.dam.pmdm.tpv.ui.productos.ProductoTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.productos.ProductoTPVViewModel

@Suppress("ViewModelConstructorInComposable")
@Composable
fun MainTPV(
    categoriaTPVViewModel: CategoriaTPVViewModel,
    productoViewModel: ProductoTPVViewModel,
    onExit: () -> Unit
) {

    val navController = rememberNavController()

    NavHost(
            navController,
            startDestination = TPVRoutes.Login
        ) {
            composable(TPVRoutes.Login) {
                TPVLoginForm({
                    navController.navigate(AdminRoutes.Categorias) {
                        launchSingleTop = true
                    }
                },{
                    navController.popBackStack()
                })
            }
            composable(TPVRoutes.Categorias) {
                CategoriaTPV(categoriaTPVViewModel, {
                    categoriaTPVViewModel.setSelectedCategoria(it)
                    navController.navigate(AdminRoutes.Productos) {
                        launchSingleTop = true
                    }
                })
            }
            composable(TPVRoutes.Productos) {
                ProductoTPV(categoriaTPVViewModel, productoViewModel, {})
            }
            composable(TPVRoutes.Carrito) {

            }
        }
}