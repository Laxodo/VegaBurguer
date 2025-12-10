package ies.sequeros.com.dam.pmdm.administrador.infraestructura.productos;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Categoria;
import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class ProductoDAO implements IDao<Producto> {
    private DataBaseConnection conn;
    private final String table_name = "Producto";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id = ?";
    private final String findbyname = "select * from " + table_name + " where name = ?";
    private final String deletebyid = "delete from " + table_name + " where id = ?";
    private final String insert = "INSERT INTO " + table_name + " (id, name, description, price, imgPath, enabled, id_categoria) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " SET name = ?, description = ?, price = ?, imgPath = ?, enabled = ?, id_categoria = ?" + " WHERE id = ?";
    public ProductoDAO() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Producto getById(final String id) {
        Producto pd = null;
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pd = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id = " + id + "]");
            return pd;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }

    public Producto findByName(final String name) {
        Producto pd = null;
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                pd = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name = " + name + "]");
            return pd;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }

    @Override
    public List<Producto> getAll() {
        final ArrayList<Producto> pdl = new ArrayList<>();
        Producto tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                pdl.add(tempo);
            }
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info("Ejecutando SQL: " + selectall + " | Parametros: ");
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdl;
    }

    @Override
    public void update(final Producto item) {
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(update);
            pst.setString(1, item.getName());
            pst.setString(2, item.getDescription());
            pst.setFloat(3, item.getPrice());
            pst.setString(4, item.getImgPath());
            pst.setBoolean(5, item.getEnabled());
            pst.setString(6, item.getId_categoria());
            pst.setString(7, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getName() +
                            ", [2]="+ item.getDescription() +
                            ", [3]=" + item.getPrice() +
                            ", [4]=" + item.getImgPath() +
                            ", [5]=" + item.getEnabled() +
                            ", [6]=" + item.getId_categoria() +
                            ", [7]=" + item.getId() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(final Producto item) {
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info("EJECUTANDO SQL: " + deletebyid + " | Parametros: [id = " + item.getId() + "]");
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(final Producto item) {
        final PreparedStatement pst;
        try {
            pst = conn.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getId());
            pst.setString(2, item.getName());
            pst.setString(3, item.getDescription());
            pst.setFloat(4, item.getPrice());
            pst.setString(5, item.getImgPath());
            pst.setBoolean(6, item.getEnabled());
            pst.setString(7, item.getId_categoria());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(ProductoDAO.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]="+ item.getName() +
                            ", [3]=" + item.getDescription() +
                            ", [4]=" + item.getPrice() +
                            ", [5]=" + item.getImgPath() +
                            ", [6]=" + item.getEnabled() +
                            ", [7]=" + item.getId_categoria() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Producto registerToObject(final ResultSet r) {
        Producto pd =null;
        try {
            pd = new Producto(
                    r.getString("ID"),
                    r.getString("NAME"),
                    r.getString("DESCRIPTION"),
                    r.getFloat("PRICE"),
                    r.getString("IMGPATH"),
                    r.getBoolean("ENABLED"),
                    r.getString("ID_CATEGORIA"));
            return pd;
        } catch (final SQLException ex) {
            Logger.getLogger(ProductoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }
}
