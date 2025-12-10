package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.actualizar

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlin.String

class ActualizarProductoUseCase(private val repositorio: IProductoRepositorio,
                                private val almacenDatos: AlmacenDatos) {

    suspend fun invoke(command: ActualizarProductoCommand, ): ProductoDTO {

        val item: Producto?=repositorio.getById(command.id)

        //val nombreArchivo = command.imagePath.substringAfterLast('/')
        var nuevaImagePath:String?=null
        if (item==null) {
            throw IllegalArgumentException("El producto no esta registrado.")
        }
        //se pasa a dto para tener el path
        var itemDTO: ProductoDTO=item.toDTO(almacenDatos.getAppDataDir()+"/productos/", command.id_categoria)

        //si las rutas son diferentes se borra y se copia
        if(itemDTO.imagePath!=command.imagePath) {
            almacenDatos.remove(itemDTO.imagePath)
            nuevaImagePath=almacenDatos.copy(command.imagePath,command.id,"/productos/")
        }else{
            nuevaImagePath=item.imgPath
        }

        var newProduct= item.copy(
            id = command.id,
            name = command.name,
            description = command.description,
            imgPath = nuevaImagePath,
            enabled = command.enabled,
            price = command.price,
            id_categoria = command.id_categoria
        )
        repositorio.update(newProduct)
        //se devuelve con el path correcto
        return newProduct.toDTO(almacenDatos.getAppDataDir()+"/productos/", command.id_categoria)
    }
}