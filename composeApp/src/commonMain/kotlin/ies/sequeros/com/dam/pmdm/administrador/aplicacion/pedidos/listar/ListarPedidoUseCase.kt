package ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar


import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio




class ListarPedidoUseCase(private val repositorio: IPedidoRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<PedidoDTO> {
        //this.validateUser(user)
        //si tiene imagen
        val items= repositorio.getAll().map { it.toDTO() }
        return items
    }
}