package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.SedeDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Sede;

import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link SedeDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare nuova sede e leggere la sede e la lista di sede</p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class SedeDAOImpl implements SedeDAO {

    /**
     * Chiamare la procedura implementata su Postgres per inserire una nuova sede,
     * si legge le informazioni di sede dall'oggetto della Sede.
     * */
    @Override
    public void aggiungeSede(Sede sede) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Sede\"(?,?,?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setString(1,sede.getVia());
            stmt.setString(2,sede.getNumeroCivico());
            stmt.setString(3,sede.getCitta());
            stmt.setString(4,sede.getCap());
            stmt.setString(5,sede.getProvincia());
            stmt.execute();

        }
    }

    /**
     * Riempire la lista della sede passata attraverso la query di select crescente.
     * */
    @Override
    public void getAllSede(List<Sede> listSede) throws SQLException {
        String sql = "SELECT \"idSede\",via, \"numeroCivico\", citta, cap, provincia " +
                     "FROM \"Hackathon\".\"Sede\" " +
                     "ORDER BY \"idSede\" ASC";
        int idSede;
        String via;
        String numeroCivico;
        String citta;
        String cap;
        String provincia;

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                idSede = rs.getInt("idSede");
                via = rs.getString("via");
                numeroCivico = rs.getString("numeroCivico");
                citta = rs.getString("citta");
                cap = rs.getString("cap");
                provincia = rs.getString("provincia");

                listSede.add(new Sede(idSede,via, numeroCivico, citta, cap, provincia));
            }

            rs.close();
        }
    }

    /**
     * Attraverso id sede trova il sede corrispondente.
     * */
    @Override
    public Sede getSedeById(int idSede) throws SQLException {
        Sede sede = null;
        String sql = "SELECT \"idSede\", via, \"numeroCivico\", citta, cap, provincia " +
                     "FROM \"Hackathon\".\"Sede\" " +
                     "WHERE \"idSede\" = ?";

        String via;
        String numeroCivico;
        String citta;
        String cap;
        String provincia;

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1,idSede);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                via = rs.getString("via");
                numeroCivico = rs.getString("numeroCivico");
                citta = rs.getString("citta");
                cap = rs.getString("cap");
                provincia = rs.getString("provincia");

                sede = new Sede(idSede,via,numeroCivico,citta,cap,provincia);
            }

            rs.close();
        }

        return sede;
    }
}
