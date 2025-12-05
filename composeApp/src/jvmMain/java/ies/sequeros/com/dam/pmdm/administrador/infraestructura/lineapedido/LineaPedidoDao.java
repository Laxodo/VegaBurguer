package ies.sequeros.com.dam.pmdm.administrador.infraestructura.lineapedido;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ies.sequeros.com.dam.pmdm.administrador.modelo.LineaPedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class LineaPedidoDao implements IDao<LineaPedido> {
    private DataBaseConnection conn;
    private final String table_name = "LineaPedido";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id=?";
    private final String findbyname = "select * from " + table_name + " where name=?";

    private final String deletebyid = "delete from " + table_name + " where id=?";
    private final String insert = "INSERT INTO " + table_name + " (id, anoumt, total, productPrice, delivered, id_pedido, id_producto) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String update = "UPDATE " + table_name + " SET amount = ?, total = ?, productPrice = ?, delivered = ?, id_pedido = ?, id_producto = ? " + "WHERE id = ?";
    public LineaPedidoDao() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public LineaPedido getById(final String id) {
        LineaPedido sp = null;// = new LineaPedido();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sp = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id=" + id + "]");
            return sp;
        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp;
    }

    public LineaPedido findByName(final String name) {
        LineaPedido sp = null;// = new LineaPedido();
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                sp = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name=" + name + "]");

            return sp;
        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sp;
    }
    @Override
    public List<LineaPedido> getAll() {
        final ArrayList<LineaPedido> scl = new ArrayList<>();
        LineaPedido tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                scl.add(tempo);
            }

            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + selectall+ " | Parametros: ");

        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scl;
    }

    @Override
    public void update(final LineaPedido item) {
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(update);
            pst.setInt(1, item.getAmount());
            pst.setFloat(2, item.getTotal());
            pst.setFloat(3, item.getProductPrice());
            pst.setBoolean(4, item.getDelivered());
            pst.setString(5, item.getId_pedido());
            pst.setString(6, item.getId_producto());
            pst.setString(7, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getAmount() +
                            ", [2]=" + item.getTotal() +
                            ", [3]=" + item.getProductPrice() +
                            ", [4]=" + item.getDelivered() +
                            ", [5]=" + item.getId_pedido() +
                            ", [6]=" + item.getId_producto() +
                            ", [7]=" + item.getId() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @Override
    public void delete(final LineaPedido item) {
        try {
            final PreparedStatement pst =
                    conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info("Ejecutando SQL: " + deletebyid + " | Parametros: [id=" + item.getId() + "]");

        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(final LineaPedido item) {
        final PreparedStatement pst;
        try {
           pst = conn.getConnection().prepareStatement(insert,
                    Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getId());
            pst.setInt(2, item.getAmount());
            pst.setFloat(3, item.getTotal());
            pst.setFloat(4, item.getProductPrice());
            pst.setBoolean(5, item.getDelivered());
            pst.setString(6, item.getId_pedido());
            pst.setString(7, item.getId_producto());

            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(LineaPedidoDao.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]="+ item.getAmount() +
                            ", [3]=" + item.getTotal() +
                            ", [4]=" + item.getProductPrice() +
                            ", [5]=" + item.getDelivered() +
                            ", [6]=" + item.getId_pedido() +
                            ", [7]=" + item.getId_producto() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //pasar de registro a objeeto
    private LineaPedido registerToObject(final ResultSet r) {
         LineaPedido sc =null;
        try {
            sc=new LineaPedido(
                    r.getString("ID"),
                    r.getInt("AMOUNT"),
                    r.getFloat("TOTAL"),
                    r.getFloat("PRODUCTPRICE"),
                    r.getBoolean("DELIVERED"),
                    r.getString("ID_PEDIDO"),
                    r.getString("ID_PRODUCTO"));
            return sc;
        } catch (final SQLException ex) {
            Logger.getLogger(LineaPedidoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sc;
    }
}
