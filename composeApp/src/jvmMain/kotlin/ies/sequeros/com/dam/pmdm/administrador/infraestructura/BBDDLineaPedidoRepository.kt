package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.lineapedido.BBDDRepositorioLineaPedidosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio

class BBDDLineaPedidoRepository (
    private val bbddRepositorioLineaPedidoJava: BBDDRepositorioLineaPedidosJava
) : ILineaPedidoRepositorio {
    override suspend fun add(item: LineaPedido) {
        bbddRepositorioLineaPedidoJava.add(item)
    }

    override suspend fun remove(item: LineaPedido): Boolean {
        bbddRepositorioLineaPedidoJava.remove(item)
        return true
    }

    override suspend fun remove(id: String): Boolean {

        bbddRepositorioLineaPedidoJava.remove(id)
        return true
    }

    override suspend fun update(item: LineaPedido): Boolean {
        bbddRepositorioLineaPedidoJava.update(item)
        return true
    }

    override suspend fun getAll(): List<LineaPedido> {
        return bbddRepositorioLineaPedidoJava.all
    }

    /*override suspend fun findByName(name: String): LineaPedido? {
        return bbddRepositorioLineaPedidoJava.findByName( name)
    }*/

    override suspend fun getById(id: String): LineaPedido? {
        return bbddRepositorioLineaPedidoJava.getById(id)
    }
}