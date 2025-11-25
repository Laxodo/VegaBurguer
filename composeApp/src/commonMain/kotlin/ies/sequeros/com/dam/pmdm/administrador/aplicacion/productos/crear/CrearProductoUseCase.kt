package ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.crear

import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.ProductoDTO
import ies.sequeros.com.dam.pmdm.administrador.aplicacion.productos.listar.toDTO
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto
import ies.sequeros.com.dam.pmdm.administrador.modelo.IProductoRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import ies.sequeros.com.dam.pmdm.generateUUID

class CrearProductoUseCase(private val repositorio: IProductoRepositorio, private val almacenDatos: AlmacenDatos)  {

    suspend  fun invoke(createCategoryCommand: CrearProductoCommand): ProductoDTO {
        //this.validateUser(user)
        if (repositorio.findByName(createCategoryCommand.name)!=null) {
            throw IllegalArgumentException("El producto ya está registrado.")
        }
        val id=generateUUID()
        //se almacena el fichero
        val imageName=almacenDatos.copy(createCategoryCommand.imagePath,id,"/productos/")
        val item = Producto(
            id = id,
            name = createCategoryCommand.name,
            description = createCategoryCommand.description,
            imagePath = imageName,
            enabled = createCategoryCommand.enabled,
            price = createCategoryCommand.price
        )
        val element=repositorio.findByName(item.name)
        if(element!=null)
            throw IllegalArgumentException("El producto ya está registrado.")
        repositorio.add(item)
        return item.toDTO( almacenDatos.getAppDataDir()+"/productos/");
    }
}