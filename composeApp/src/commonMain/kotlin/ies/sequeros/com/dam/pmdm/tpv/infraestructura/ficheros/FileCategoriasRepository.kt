package ies.sequeros.com.dam.pmdm.tpv.infraestructura.ficheros

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria
import ies.sequeros.com.dam.pmdm.administrador.modelo.ICategoriaRepositorio
import ies.sequeros.com.dam.pmdm.commons.infraestructura.AlmacenDatos
import kotlinx.serialization.json.Json
import java.io.File

/*class FileCategoriasRepository(
    private val almacenDatos: AlmacenDatos,
    private val fileName: String = "categorias.json"
): ICategoriaRepositorio {
    private val subdirectory = "/data/"
    init {

    }

    private suspend fun getDirectoryPath(): String {
        val dir = almacenDatos.getAppDataDir()
        val directory = File(dir, subdirectory)
        return directory.absolutePath
    }

    private suspend fun save(items: List<Categoria>) {
        if(!File(this.getDirectoryPath()).exists())
            File(this.getDirectoryPath()).mkdirs()
        this.almacenDatos.writeFile(this.getDirectoryPath()+"/"+this.fileName, Json.encodeToString(items))
    }

    override suspend fun add(item: Categoria) {
        val items = this.getAll().toMutableList()
        if (items.firstOrNull { it.name == item.name } == null) {
            items.add(item)
        } else {
            throw IllegalArgumentException("ALTA:La categoria con id: " + item.id + " ya existe")
        }
        this.save(items)
    }

    override suspend fun remove(item: Categoria): Boolean {
        return this.remove(item.id!!)
    }

    override suspend fun remove(id: String): Boolean {
        val items = this.getAll().toMutableList()
        val item = items.firstOrNull { it.id == id }
        if (item != null) {
            items.remove((item))
            this.save(items)
            return false
        } else {
            throw IllegalArgumentException("BORRADO: La categoria con id: " + id + " NO existe")
        }
        return true
    }


}*/