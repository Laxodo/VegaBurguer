package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar


import ies.sequeros.com.dam.pmdm.administrador.aplicacion.lineapedido.listar.LineaPedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio


class ListarPedidoUseCase(private val repositorio: IPedidoRepositorio,private val repositorioProductos: IProductoRepositorio, private val repositorioDependiente: IDependienteRepositorio, private val repositorioLineaPedido: ILineaPedidoRepositorio, val almacenDatos: AlmacenDatos) {
    suspend fun invoke(): List<PedidoDTO> {
        val pedidos = repositorio.getAll() // lista de Pedido
        val lineas = repositorioLineaPedido.getAll() // lista de LineaPedido
        val productos = repositorioProductos.getAll() // lista de Producto

        // Mapa rápido de id_producto -> nombre
        val nombresProductos = productos.associateBy({ it.id }, { it.name })

        // Agrupar líneas por id_pedido
        val lineasPorPedido = lineas.groupBy { it.id_pedido }

        return pedidos.map { pedido ->
            val lineasPedidoDTO = lineasPorPedido[pedido.id]?.map { linea ->
                // Obtener el nombre del producto
                val nombreProducto = nombresProductos[linea.id_producto] ?: "Desconocido"

                LineaPedidoDTO(
                    id = linea.id,
                    amount = linea.amount,
                    total = linea.total,
                    ProductPrice = linea.productPrice,
                    delivered = linea.delivered,
                    pedido = linea.id_pedido,
                    producto = linea.id_producto,
                    nombreProducto = nombreProducto // nuevo campo en tu DTO
                )
            } ?: emptyList()

            pedido.toDTO("", lineasPedidoDTO)
        }
    }

}