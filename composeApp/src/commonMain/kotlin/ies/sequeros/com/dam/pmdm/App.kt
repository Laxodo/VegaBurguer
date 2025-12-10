package ies.sequeros.com.dam.pmdm

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ies.sequeros.com.dam.pmdm.administrador.AdministradorViewModel
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.ListarCategoriaUseCase
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio

import ies.sequeros.com.dam.pmdm.administrador.ui.MainAdministrador
import ies.sequeros.com.dam.pmdm.administrador.ui.MainAdministradorViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.dependientes.DependientesViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.categorias.CategoriaViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.productos.ProductosViewModel
import ies.sequeros.com.dam.pmdm.administrador.ui.pedidos.PedidosViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.MainTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.MainTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.login.TPVLoginForm
import ies.sequeros.com.dam.pmdm.tpv.ui.productos.ProductoTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.carrito.CarritoTPVViewModel

@Suppress("ViewModelConstructorInComposable")
@Composable

fun App(
    dependienteRepositorio : IDependienteRepositorio,
    categoriaRepositorio: ICategoriaRepositorio,
    productoRepositorio: IProductoRepositorio,
    pedidoRepositorio: IPedidoRepositorio,
    almacenImagenes:AlmacenDatos) {

    //view model
    val appViewModel= viewModel {  AppViewModel() }
    val mainViewModel= remember { MainAdministradorViewModel() }
    val administradorViewModel= viewModel { AdministradorViewModel() }
    val listarCategoriaUseCase = ListarCategoriaUseCase(categoriaRepositorio, almacenImagenes)
    val dependientesViewModel = viewModel{ DependientesViewModel(
        dependienteRepositorio, almacenImagenes
    )}
    val categoriaViewModel = viewModel { CategoriaViewModel(
        categoriaRepositorio, almacenImagenes
    ) }
    val productoViewModel = viewModel { ProductosViewModel(
        productoRepositorio, categoriaRepositorio,almacenImagenes
    ) }
    val pedidoViewModel = viewModel { PedidosViewModel(
        pedidoRepositorio, almacenImagenes
    ) }
    val categoriaTPVViewModel = viewModel { CategoriaTPVViewModel(categoriaRepositorio, almacenImagenes) }
    val productoTPVViewModel = viewModel { ProductoTPVViewModel(productoRepositorio, categoriaRepositorio, almacenImagenes) }
    val pedidoTPVViewModel = viewModel { CarritoTPVViewModel() }
    val mainTPVViewModel = viewModel { MainTPVViewModel() }

    appViewModel.setWindowsAdatativeInfo( currentWindowAdaptiveInfo())
    val navController= rememberNavController()
//para cambiar el modo
    AppTheme(appViewModel.darkMode.collectAsState()) {

        NavHost(
            navController,
            startDestination = AppRoutes.Main
        ) {
            composable(AppRoutes.Main) {
                Principal({
                    navController.navigate(AppRoutes.Administrador)
                },{},{navController.navigate(AppRoutes.TPVLogin)})
            }
            composable (AppRoutes.Administrador){
                MainAdministrador(appViewModel,mainViewModel,listarCategoriaUseCase, administradorViewModel,
                    dependientesViewModel,
                    categoriaViewModel,
                    pedidoViewModel,
                    productoViewModel,{
                    navController.popBackStack()
                })
            }
            composable(AppRoutes.TPVLogin) {
                TPVLoginForm({
                    navController.navigate(AppRoutes.TPV) {
                        launchSingleTop = true
                    }
                }, {
                    navController.popBackStack()
                })
            }
            composable(AppRoutes.TPV){
                MainTPV(appViewModel,
                    mainTPVViewModel,
                    categoriaTPVViewModel,
                    productoTPVViewModel, pedidoTPVViewModel,{
                        navController.popBackStack()
                    })
            }

        }
    }

}