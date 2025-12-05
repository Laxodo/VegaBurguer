package ies.sequeros.com.dam.pmdm.administrador.aplicacion.dependientes.listar

import ies.sequeros.com.dam.pmdm.administrador.modelo.Dependiente

fun Dependiente.toDTO(path:String="") = DependienteDTO(
    id = id,
    name = name,
    email = email,
    imagePath=path+imgPath,
    enabled ,
    isAdmin = isAdmin
)
fun DependienteDTO.toDependiente()= Dependiente(
    id = id,
    name = name,
    email = email,
    password = "",
    imgPath =imagePath,

    enabled ,
    isAdmin = isAdmin
)