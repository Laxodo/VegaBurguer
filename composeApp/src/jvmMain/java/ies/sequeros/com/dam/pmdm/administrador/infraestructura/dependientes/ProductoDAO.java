package ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes;


import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class ProductoDAO implements IDao<Producto> {
    private DataBaseConnection conn;
    private final String table_name = "PRODUCTO";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String selectbiname = "select * from " + table_name + " where name=?";
    private final String deletebyid = "delete from " + table_name + " where id=?";
    private final String insert = "INSERT INTO " + table_name + " (id + name + isActive + precio) " + "VALUES (?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " ";
}
