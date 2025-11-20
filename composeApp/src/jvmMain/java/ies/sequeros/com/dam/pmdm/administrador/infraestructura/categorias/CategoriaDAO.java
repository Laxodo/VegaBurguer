package ies.sequeros.com.dam.pmdm.administrador.infraestructura.categorias;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class CategoriaDAO implements IDao<Categoria> {
    private DataBaseConnection conn;
    private final String table_name = "Categoria";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id = ?";
    private final String selectbyname = "select * from " + table_name + " where name = ?";
    private final String deletebyid = "select * from " + table_name + " where id = '?'";
    private final String insert = "INSERT INTO " + table_name + " (id, name, description, imgpath, enabled) " + "VALUES (?, ?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " SET name = ? , description = ?, imgpath = ?, enabled = ? " + " WHERE id = ?";

    public CategoriaDAO() {
    }

    

}
