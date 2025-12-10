package ies.sequeros.com.dam.pmdm.tpv.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.House
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowWidthSizeClass
import ies.sequeros.com.dam.pmdm.AppViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.categorias.CategoriaTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.carrito.CarritoTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.carrito.CarritoTPVViewModel
import ies.sequeros.com.dam.pmdm.tpv.ui.productos.ProductoTPV
import ies.sequeros.com.dam.pmdm.tpv.ui.productos.ProductoTPVViewModel
import javax.swing.Icon

@Suppress("ViewModelConstructorInComposable")
@Composable
fun MainTPV(
    appViewModel: AppViewModel,
    mainTPVViewModel: MainTPVViewModel,
    categoriaTPVViewModel: CategoriaTPVViewModel,
    productoViewModel: ProductoTPVViewModel,
    carritoTPVViewModel: CarritoTPVViewModel,
    onExit: () -> Unit
) {

    val currentProducts = carritoTPVViewModel.totalProducts.collectAsState()
    val totalPrice = carritoTPVViewModel.totalPrice.collectAsState()

    val navController = rememberNavController()
    val bottomOptions by mainTPVViewModel.filteredBottomItems.collectAsState()
    val topOptions by mainTPVViewModel.filteredTopItems.collectAsState()

    val wai by appViewModel.windowsAdaptativeInfo.collectAsState();

    mainTPVViewModel.setBottomOptions(
        listOf(
            ItemOption(
                Icons.Default.RemoveCircle, {
                    carritoTPVViewModel.removeProducto(productoViewModel.selected.value)
                },
                "Remove"
            ),
            ItemOption(
                Icons.Default.AddCircle, {
                    carritoTPVViewModel.addProducto(productoViewModel.selected.value)
                },
                "Add"
            )
        )
    )

    val navegador: @Composable () -> Unit = {
        NavHost(
            navController,
            startDestination = TPVRoutes.Categorias
        ) {
            composable(TPVRoutes.Categorias) {
                CategoriaTPV(categoriaTPVViewModel, {
                    categoriaTPVViewModel.setSelectedCategoria(it)
                    navController.navigate(TPVRoutes.Productos) {
                        launchSingleTop = true
                    }
                })
            }
            composable(TPVRoutes.Productos) {
                ProductoTPV(categoriaTPVViewModel, productoViewModel, {
                    productoViewModel.setSelectedProducto(it)
                })
            }
            composable(TPVRoutes.Carrito) {
                CarritoTPV(carritoTPVViewModel, {
                    mainTPVViewModel.add(it)
                    onExit()
                })
            }
        }
    }

    if (wai?.windowSizeClass?.windowWidthSizeClass == WindowWidthSizeClass.COMPACT) {
        Scaffold(
            bottomBar = {

                NavigationBar {
                    mainTPVViewModel.filteredBottomItems.collectAsState().value.forEach { item ->
                        // if(!item.admin || (item.admin && appViewModel.hasPermission()))
                        NavigationBarItem(
                            selected = true,
                            onClick = { item.action() },
                            icon = { Icon(item.icon, contentDescription = item.name) },

                        )
                    }
                }
            },
            topBar = {
                NavigationBar {

                    NavigationBarItem(

                        selected = true,
                        onClick = {
                            if (navController.currentDestination?.route != TPVRoutes.Categorias){
                                navController.popBackStack()
                            }else{ onExit() }
                        },
                        icon = { Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null) },
                    )
                    NavigationBarItem(

                        selected = true,
                        onClick = {
                            if (navController.currentDestination?.route != TPVRoutes.Categorias){
                                navController.popBackStack()
                            }else{ onExit() }
                        },
                        icon = {Icon(imageVector = Icons.Default.Home, contentDescription = null)},
                    )
                    Text(totalPrice.value.toString() + "€")
                    NavigationBarItem(

                        selected = true,
                        onClick = {
                            navController.navigate(TPVRoutes.Carrito) {
                                launchSingleTop = true
                            }
                        },
                        icon = { Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null) },
                    )
                    Text(currentProducts.value.toString())
                }
            }
        ) { innerPadding ->
            Box(Modifier.padding(innerPadding)) {
                navegador()
            }
        }
    } else {

        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    Modifier.then(
                        if (wai?.windowSizeClass?.windowWidthSizeClass == WindowWidthSizeClass.COMPACT)
                            Modifier.width(128.dp)
                        else Modifier.width(128.dp)
                    )
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight()  // ocupa todo el alto del drawer
                            .padding(vertical = 16.dp),
                        verticalArrangement = Arrangement.Center,  // centra verticalmente
                        horizontalAlignment = Alignment.CenterHorizontally  // opcional: centra horizontalmente
                    ) {

                        Spacer(Modifier.height(16.dp))
                        topOptions.forEach { item ->
                            //si se tienen permiso
                            // if(!item.admin || (item.admin && appViewModel.hasPermission()))
                            NavigationDrawerItem(
                                icon = {
                                    Box(
                                        modifier = Modifier.fillMaxWidth(),
                                        contentAlignment = Alignment.Center,

                                        ) {
                                        Icon(
                                            item.icon,
                                            tint = MaterialTheme.colorScheme.primary,
                                            contentDescription = item.name
                                        )
                                    }
                                },
                                label = { appViewModel.windowsAdaptativeInfo.collectAsState().value?.windowSizeClass.toString() }, // sin texto
                                selected = false,
                                onClick = { item.action() },
                                modifier = Modifier
                                    .padding(vertical = 4.dp) // espaciado entre items

                            )
                        }
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        // Add a fixed height constraint to prevent "Size out of range" error
                        .height(600.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    navegador()

                }
            }
        )
    }
}