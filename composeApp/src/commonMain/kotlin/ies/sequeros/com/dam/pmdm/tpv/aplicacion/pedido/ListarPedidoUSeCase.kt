package ies.sequeros.com.dam.pmdm.tpv.aplicacion.pedido

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.PedidoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.pedidos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.IPedidoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

class ListarPedidoUSeCase (private val repositorio: IPedidoRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<PedidoDTO> {
        val items= repositorio.getAll().map { it.toDTO() }
        return items
    }
}