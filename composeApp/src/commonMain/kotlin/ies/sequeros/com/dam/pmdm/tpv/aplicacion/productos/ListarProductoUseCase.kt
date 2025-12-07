package ies.sequeros.com.dam.pmdm.tpv.aplicacion.productos

import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

class ListarProductoUseCase(private val repositorio: IProductoRepositorio, private val repositorioCategoria: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos) {
    suspend fun invoke(): List<ProductoDTO> {
        val items = repositorio.getAll().map { it.toDTO(
            if (it.imgPath.isEmpty()) "" else almacenDatos.getAppDataDir()+"/productos/",
            repositorioCategoria.getById(it.id_categoria)?.name?: "")
        }
        return items
    }
}