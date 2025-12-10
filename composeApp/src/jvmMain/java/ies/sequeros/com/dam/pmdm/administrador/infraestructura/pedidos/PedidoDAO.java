package ies.sequeros.com.dam.pmdm.administrador.infraestructura.pedidos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ies.sequeros.com.dam.pmdm.administrador.modelo.Pedido;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.DataBaseConnection;
import ies.sequeros.com.dam.pmdm.commons.infraestructura.IDao;

public class PedidoDAO implements IDao<Pedido> {
    private DataBaseConnection conn;
    private final String table_name = "Pedido";
    private final String selectall = "select * from " + table_name;
    private final String selectbyid = "select * from " + table_name + " where id = ?";
    private final String findbyname = "select * from " + table_name + " where name = ?";
    private final String deletebyid = "select * from " + table_name + " where id = ?";
    private final String insert = "INSERT INTO " + table_name + " (id, clientName, productNumbers, pendingProduct, totalPrice, date, id_dependiente)" + " VALUES(?, ?, ?, ?, ?, ?, ?)";
    private final String update = "UPDATE "+ table_name + " SET clientName = ?, productNumbers = ?, pendingProduct = ?, totalPrice = ?, date = ?, id_dependiente = ?" + " WHERE id = ?";

    public PedidoDAO() {
    }

    public DataBaseConnection getConn() {
        return this.conn;
    }

    public void setConn(final DataBaseConnection conn) {
        this.conn = conn;
    }

    @Override
    public Pedido getById(final String id) {
        Pedido pd = null;
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(selectbyid);
            pst.setString(1, id);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pd = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info("Ejecutando SQL: " + selectbyid + " | Parametros: [id = " + id + "]");
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }

    public Pedido findByName(final String name) {
        Pedido pd = null;
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(findbyname);
            pst.setString(1, name);
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                pd = registerToObject(rs);
            }
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info("Ejecutando SQL: " + findbyname + " | Parametros: [name " + name + "]");
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }

    @Override
    public List<Pedido> getAll() {
        final ArrayList<Pedido> pdl = new ArrayList<>();
        Pedido tempo;
        PreparedStatement pst = null;
        try {
            try {
                pst = conn.getConnection().prepareStatement(selectall);
            } catch (final SQLException ex) {
                Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            final ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                tempo = registerToObject(rs);
                pdl.add(tempo);
            }
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info("Ejecutsndo SQL: " + selectall + " | Paramentros: ");
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pdl;
    }

    @Override
    public void update(final Pedido item) {
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(update);
            pst.setString(1, item.getClientName());
            pst.setInt(2, item.getProductNumbers());
            pst.setInt(3, item.getPendingProducts());
            pst.setFloat(4, item.getTotalPrice());
            pst.setString(5, item.getDat3());
            pst.setString(6, item.getId_dependiente());
            pst.setString(6, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + update +
                            " | Params: [1]=" + item.getClientName() +
                            ", [2]=" + item.getProductNumbers() +
                            ", [3]=" + item.getPendingProducts() +
                            ", [4]=" + item.getTotalPrice() +
                            ", [5]=" + item.getDat3() +
                            ", [6]=" + item.getId_dependiente() +
                            ", [7]=" + item.getId() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(final Pedido item) {
        try {
            final PreparedStatement pst = conn.getConnection().prepareStatement(deletebyid);
            pst.setString(1, item.getId());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info("Ejecutando SQL: " + deletebyid + " | Parametros: [id = " + item.getId() + "]");
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(final Pedido item) {
        final PreparedStatement pst;
        try {
            pst = conn.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, item.getId());
            pst.setString(2, item.getClientName());
            pst.setInt(3, item.getProductNumbers());
            pst.setInt(4, item.getPendingProducts());
            pst.setFloat(5, item.getTotalPrice());
            pst.setString(6, item.getDat3());
            pst.setString(7, item.getId_dependiente());
            pst.executeUpdate();
            pst.close();
            Logger logger = Logger.getLogger(PedidoDAO.class.getName());
            logger.info(() ->
                    "Ejecutando SQL: " + insert +
                            " | Params: [1]=" + item.getId() +
                            ", [2]="+ item.getClientName() +
                            ", [3]=" + item.getProductNumbers() +
                            ", [4]=" + item.getPendingProducts() +
                            ", [5]=" + item.getTotalPrice() +
                            ", [6]=" + item.getDat3() +
                            ", [7]=" + item.getId_dependiente() +
                            "]"
            );
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Pedido registerToObject(final ResultSet r) {
        Pedido pd = null;
        try {
            pd = new Pedido(
                    r.getString("ID"),
                    r.getString("CLIENTNAME"),
                    r.getInt("PRODUCTNUMBERS"),
                    r.getInt("PENDINGPRODUCTS"),
                    r.getFloat("TOTALPRICE"),
                    r.getString("DAT3"),
                    r.getString("ID_DEPENDIENTE"));
            return pd;
        } catch (final SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pd;
    }
}
