package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar


import ies.sequeros.com.dam.pmdm.administrador.modelo.IDependienteRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio




class ListarPedidoUseCase(private val repositorio: IPedidoRepositorio, private val repositorioDependiente: IDependienteRepositorio, private val repositorioLineaPedido: ILineaPedidoRepositorio, val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<PedidoDTO>? {
        /*//this.validateUser(user)
        //si tiene imagen
        //val items= repositorio.getAll().map { it.toDTO("", repositorioLineaPedido.getAll().filter { it.id_pedido == pedido.id }) }
        val items = repositorio.getAll().map { pedido ->
            val lineas = repositorioLineaPedido.getAll().filter { it.id_pedido == pedido.id }
            pedido.toDTO("", lineas)
        return items*/
        val pedidos = repositorio.getAll()
        val lineas = repositorioLineaPedido.getAll()

        return pedidos.map { pedido ->
            val lineasPedido = lineas.filter { linea -> linea.id_pedido == pedido.id }
        pedido.toDTO("", lineasPedido)
        }
    }
}