package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.lineapedido.listar.LineaPedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido
import ies.sequeros.com.dam.pmdm.generateUUID

class CrearPedidoUseCase(private val repositorio: IPedidoRepositorio, private val repositorioLineaPedido: ILineaPedidoRepositorio, private val repositorioProducto: IProductoRepositorio,val repositorioDependiente: IDependienteRepositorio) {
    suspend fun invoke(crearPedidoCommand: CrearPedidoCommand): PedidoDTO {
        if(repositorio.findByName(crearPedidoCommand.clientName)!= null) {
            throw IllegalArgumentException("El pedido ya esta creado.")
        }
        val id = generateUUID()
        val item = Pedido(
            id = id,
            clientName = crearPedidoCommand.clientName,
            productNumbers = crearPedidoCommand.productNumber,
            pendingProducts = crearPedidoCommand.pendingProducts,
            totalPrice = crearPedidoCommand.totalPrice,
            dat3 = crearPedidoCommand.dat3,
            id_dependiente = crearPedidoCommand.id_dependiente
        )

        val lineasPedido = crearPedidoCommand.listar
        val productos = repositorioProducto.getAll()
        val nombresProductos = productos.associateBy({ it.id }, { it.name })

        val lineasPedidoDTO: List<LineaPedidoDTO> = lineasPedido.map { linea ->
            LineaPedidoDTO(
                id = linea.id,
                amount = linea.amount,
                total = linea.total,
                ProductPrice = linea.productPrice,
                delivered = linea.delivered,
                pedido = linea.id_pedido,
                producto = linea.id_producto,
                nombreProducto = nombresProductos[linea.id_producto] ?: "Desconocido"
            )
        }
        repositorio.add(item)
        lineasPedido.forEach { item ->
            item.id = generateUUID()
            item.id_pedido = id
            repositorioLineaPedido.add(item)
        }

// Finalmente, llamar a toDTO con la lista correcta
        val nombreDependiente = repositorioDependiente.getById(item.id_dependiente)?.name ?: ""

        return item.toDTO(nombreDependiente, lineasPedidoDTO)

    }
}