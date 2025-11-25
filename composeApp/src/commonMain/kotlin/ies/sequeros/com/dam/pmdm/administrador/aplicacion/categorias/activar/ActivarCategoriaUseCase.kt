package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.activar.ActivarCategoriaCommand
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos

class ActivarCategoriaUseCase(private val repositorio: ICategoriaRepositorio, private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: ActivarCategoriaCommand ): CategoriaDTO {
        val item: Categoria?=repositorio.getById(command.id)
        if (item==null) {
            throw IllegalArgumentException("La categoria no esta registrado.")
        }
        var newCategory= item.copy(
            enabled = command.enabled,
        )
        repositorio.update(newCategory)
        //se devuelve con el path correcto
        return newCategory.toDTO(almacenDatos.getAppDataDir()+"/categorias/")
    }
}