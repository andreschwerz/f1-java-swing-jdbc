package dao;

import model.Prova;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André Schwerz
 */
public class ProvaDAO extends DbConnection {

    private Connection conn;
    private final String sqlInsert = "INSERT INTO Prova(circuito_nome, gp, data) VALUES (?,?,?)";
    private final String sqlUpdate = "UPDATE Prova SET circuito_nome= ?, gp= ?, data= ? WHERE gp = ? and data=?";
    private final String sqlRemove = "DELETE FROM Prova WHERE gp = ? and data=?";
    private final String sqlList = "SELECT circuito_nome, gp, data FROM Prova ORDER BY data desc";
    private final String sqlFind = "SELECT circuito_nome, gp, data FROM Prova WHERE gp = ? and data=?";

    public void insert(Prova prova) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, prova.getCircuito().getNome());
            ps.setString(2, prova.getGp());
            ps.setDate(3, prova.getData());
            ps.execute();
        } finally {
            ps.close();
        }
    }

    public void update(Prova prova, String gp, java.sql.Date data) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, prova.getCircuito().getNome());
            ps.setString(2, prova.getGp());
            ps.setDate(3, prova.getData());
            ps.setString(4, gp);
            ps.setDate(5, data);
            ps.execute();
        } finally {
            ps.close();
        }
    }

    public void remove(String gp, java.sql.Date data) throws SQLException {
        PreparedStatement ps = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, gp);
            ps.setDate(2, data);
            ps.execute();
        } finally {
            ps.close();
        }
    }

    public List<Prova> list() throws SQLException, ClassNotFoundException, ParseException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            List<Prova> list = new ArrayList<>();
            Prova prova;
            CircuitoDAO circuitoDao = new CircuitoDAO();
            while (rs.next()) {
                prova = new Prova();
                prova.setCircuito(circuitoDao.find(rs.getString("circuito_nome")));
                prova.setGp(rs.getString("gp"));
                prova.setData(rs.getDate("data"));
                list.add(prova);
            }
            return list;
        } finally {
            rs.close();
            ps.close();
        }
    }

    public Prova find(String gp, java.sql.Date data) throws SQLException, ClassNotFoundException, ParseException, IOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, gp);
            ps.setDate(1, data);
            rs = ps.executeQuery();
            Prova prova = new Prova();
            CircuitoDAO circuitoDao = new CircuitoDAO();

            if (rs.next()) {
                prova = new Prova();
                prova.setCircuito(circuitoDao.find(rs.getString("circuito_nome")));
                prova.setGp(rs.getString("gp"));
                prova.setData(rs.getDate("data"));
            }
            return prova;
        } finally {
            rs.close();
            ps.close();
        }
    }
}