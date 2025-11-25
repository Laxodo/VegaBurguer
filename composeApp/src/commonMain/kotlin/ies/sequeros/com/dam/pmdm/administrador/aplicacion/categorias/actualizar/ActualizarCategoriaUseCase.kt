package ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.actualizar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.CategoriaDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.categorias.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlin.String

class ActualizarCategoriaUseCase(private val repositorio: ICategoriaRepositorio,
                              private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: ActualizarCategoriaCommand, ): CategoriaDTO {

        val item: Categoria?=repositorio.getById(command.id)

        //val nombreArchivo = command.imagePath.substringAfterLast('/')
        var nuevaImagePath:String?=null
        if (item==null) {
            throw IllegalArgumentException("La categoria no esta registrado.")
        }
        //se pasa a dto para tener el path
        var itemDTO: CategoriaDTO=item.toDTO(almacenDatos.getAppDataDir()+"/categorias/")

        //si las rutas son diferentes se borra y se copia
        if(itemDTO.imgPath!=command.imgPath) {
            almacenDatos.remove(itemDTO.imgPath)
            nuevaImagePath=almacenDatos.copy(command.imgPath,command.id,"/categorias/")
        }else{
            nuevaImagePath=item.imgPath
        }

        var newCategory= item.copy(
            id = command.id,
            name = command.name,
            description = command.description,
            imgPath = nuevaImagePath,
            enabled = command.enabled
        )
        repositorio.update(newCategory)
        //se devuelve con el path correcto
        return newCategory.toDTO(almacenDatos.getAppDataDir()+"/categorias/")
    }
}