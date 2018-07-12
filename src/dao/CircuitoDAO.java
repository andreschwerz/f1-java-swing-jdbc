package dao;

import model.Circuito;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author André Luis Schwerz
 */
public class CircuitoDAO extends DbConnection{
    private Connection conn;
    private final String sqlInsert  = "INSERT INTO Circuito(pais_sigla, nome, status, cidade, estilo) VALUES (?,?,?,?,?)";
    private final String sqlUpdate  = "UPDATE Circuito SET pais_sigla= ?, nome=?, status = ?, cidade= ?, estilo=?  WHERE nome = ? ";
    private final String sqlRemove  = "DELETE FROM Circuito WHERE nome = ?";
    private final String sqlList    = "SELECT pais_sigla, nome, status, cidade, estilo FROM Circuito ORDER BY Circuito.nome";
    private final String sqlFind    = "SELECT pais_sigla, nome, status, cidade, estilo FROM Circuito WHERE nome = ?";

    public void insert(Circuito circuito) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setString(1, circuito.getPais().getSigla());
            ps.setString(2, circuito.getNome());
            ps.setBoolean(3, circuito.isStatus());
            ps.setString(4, circuito.getCidade());
            ps.setString(5, circuito.getEstilo());
            ps.execute();
        }
        finally{
            ps.close();
            conn.close();
        }
    }

    public void update(Circuito circuito, String nome) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, circuito.getPais().getSigla());
            ps.setString(2, circuito.getNome());
            ps.setBoolean(3, circuito.isStatus());
            ps.setString(4, circuito.getCidade());
            ps.setString(5, circuito.getEstilo());
            ps.setString(6, nome);

            ps.execute();
        }
        finally{
            ps.close();
            conn.close();
        }
    }
    public void remove(String nome) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setString(1, nome);
            ps.execute();
        }
        finally{
            ps.close();
            conn.close();
        }
    }
    
    public ArrayList<Circuito> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Circuito> list = new ArrayList<>();
            Circuito circuito;
            PaisDAO paisDao = new PaisDAO();
            while (rs.next()){
                circuito = new Circuito();
              
                circuito.setPais(paisDao.find(rs.getString("pais_sigla")));
                circuito.setNome(rs.getString("nome"));
                circuito.setStatus(rs.getBoolean("status"));
                circuito.setCidade(rs.getString("cidade"));
                circuito.setEstilo(rs.getString("estilo"));

                list.add(circuito);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            conn.close();
        }
    }

    public Circuito find(String nome)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setString(1, nome);

            rs = ps.executeQuery();
            Circuito circuito = null ;
            PaisDAO paisDao = new PaisDAO();
            if (rs.next()){
                circuito = new Circuito();
               
                circuito.setPais(paisDao.find(rs.getString("pais_sigla")));
                circuito.setNome(rs.getString("nome"));
                circuito.setStatus(rs.getBoolean("status"));
                circuito.setCidade(rs.getString("cidade"));
                circuito.setEstilo(rs.getString("estilo"));
            }
            return circuito;
        }
        finally{
            rs.close();
            ps.close();
            conn.close();
        }
    }
}