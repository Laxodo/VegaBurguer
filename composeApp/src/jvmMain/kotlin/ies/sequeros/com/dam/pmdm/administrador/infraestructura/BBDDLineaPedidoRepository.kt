package ies.sequeros.com.dam.pmdm.administrador.infraestructura

import ies.sequeros.com.dam.pmdm.administrador.infraestructura.lineapedidos.BBDDRepositorioLineaPedidosJava
import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido
import ies.sequeros.com.dam.pmdm.administrador.modelo.ILineaPedidoRepositorio

class BBDDLineaPedidoRepository (
    private val bbddRepositorioLineaPedidoJava: BBDDRepositorioLineaPedidoJava
) : ILineaPedidoRepositorio {
    override suspend fun add(item: LineaPedido) {
        bbddRepositorioLineaPedidoJava.add(item)
    }

    override suspend fun remove(item: LineaPedido): Boolean {
        bbddRepositorioDepedientesJava.remove(item)
        return true
    }

    override suspend fun remove(id: String): Boolean {

        bbddRepositorioDepedientesJava.remove(id)
        return true
    }

    override suspend fun update(item: LineaPedido): Boolean {
        bbddRepositorioDepedientesJava.update(item)
        return true
    }

    override suspend fun getAll(): List<LineaPedido> {
        return bbddRepositorioDepedientesJava.all
    }

    override suspend fun findByName(name: String): LineaPedido? {
        return bbddRepositorioDepedientesJava.findByName( name)
    }

    override suspend fun getById(id: String): LineaPedido? {
        return bbddRepositorioDepedientesJava.getById(id)
    }
}