package ies.sequeros.com.dam.pmdm.administrador.infraestructura.dependientes;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Producto;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

/*public class ProductoDAO implements IDao<Producto> {
    private DataBaseConnection conn;
    private final String table_name = "PRODUCTO";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String selectbyname = "select * from " + table_name + " where name=?";
    private final String deletebyid = "delete from " + table_name + " where id=?";
    private final String insert = "INSERT INTO " + table_name + " (id + name + isActive + precio) " + "VALUES (?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " SET name = ?, isActive = ?, precio = ?" + "WHERE id = ?";
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
            Logger logger = Logger.getLogger(Producto.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id=" + id + "]");
            return pd;
        } catch (final SQLException ex) {

        }
        return pd;
    }

    public Producto findByName(final String name) {
        Producto pd = null;
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
        } catch (final SQLException ex) {

        }
    }

    // final
    private Producto registerToObject(final ResultSet r) {
        Producto pd =null;

        try {
            pd = new Producto(
                    r.getString("ID"),
                    r.getString("NAME"),
                    r.getBoolean("IS_ACTIVE"),
                    r.getFloat("PRICE"));
            return pd;
        } catch (final SQLException ex) {
            Logger.getLogger(DependienteDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        return pd;
    }
}
*/