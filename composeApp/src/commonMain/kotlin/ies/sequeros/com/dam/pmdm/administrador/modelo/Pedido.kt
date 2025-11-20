package ies.sequeros.com.dam.pmdm.administrador.modelo

data class Pedido (
    var id: String,
    var nombreCliente: String,
    var numeroProductos: Int,
    var productosPendientes: Int,
    var precioTotal: Float,
    var fecha: Int
)