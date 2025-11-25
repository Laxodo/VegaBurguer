package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar

import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio




class ListarProductoUseCase(private val repositorio: IProductoRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke( ): List<ProductoDTO> {
        //this.validateUser(user)
        //si tiene imagen
        val items= repositorio.getAll().map { it.toDTO( ) }
        return items
    }
}

// it.toDTO(if(it.imagePath.isEmpty()) "" else almacenDatos.getAppDataDir()+"/productos/")