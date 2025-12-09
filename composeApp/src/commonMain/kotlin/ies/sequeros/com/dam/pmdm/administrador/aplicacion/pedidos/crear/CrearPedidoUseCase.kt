package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.generateUUID

class CrearPedidoUseCase(private val repositorio: IPedidoRepositorio, private val repositorioDependiente: IDependienteRepositorio) {
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
        val element = repositorio.findByName(item.clientName)
        if(element!=null)
            throw IllegalArgumentException("El producto ya está registrado.")
        repositorio.add(item)
        return item.toDTO(repositorioDependiente.getById(item.id_dependiente)?.name ?: "")
    }
}