package ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos;

import java.sql.SQLException;
import java.util.List;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;

public class BBDDRepositorioProductosJava {
    private final DataBaseConnection db;
    private ProductoDAO dao;

    public BBDDRepositorioProductosJava(DataBaseConnection conn) throws Exception {
        super();
        this.db = conn;
        dao = new ProductoDAO();
        dao.setConn(this.db);
    }

    public void add(Producto item) {
        this.dao.insert(item);
    }

    public boolean remove(Producto item) {
        this.dao.delete(item);
        return true;
    }

    public boolean remove(String id){
        var item = this.dao.getById(id);
        if(item != null){
            this.remove(item);
            return true;
        }
        return false;
    }

    public boolean update(Producto item) {
        this.dao.update(item);
        return true;
    }

    public List<Producto> getAll() {
        return this.dao.getAll();
    }

    public Producto findByName(String name) {
        return null;
    }

    public Producto getById(String id) {
        return this.dao.getById(id);
    }

    public void close() {
        try {
            this.db.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
