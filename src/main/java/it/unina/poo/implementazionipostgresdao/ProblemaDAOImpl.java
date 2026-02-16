package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.ProblemaDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Problema;
import it.unina.poo.model.Utente;

import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link ProblemaDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare, modficare, cancellare e leggere il problema </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class ProblemaDAOImpl implements ProblemaDAO {

    /**
     * Chiama la procedura Postgres per in serire un nuovo problema.
     * */
    @Override
    public void creaProblema(Problema problema) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Problema\"(?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setInt(1, problema.getUtente().getIdUtente());
            stmt.setString(2, problema.getHackathon().getTitolo());
            stmt.setString(3, problema.getDescrizioneProblema());
            stmt.execute();

        }
    }

    /**
     * Usa UPDATE per modificare il contenuto del problema.
     * */
    @Override
    public void modificaDescrizioneProblema(Problema problema) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Problema\" " +
                     "SET \"descrizioneProblema\"=? " +
                     "WHERE \"idUtente\"=? and \"titoloHackathon\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1,problema.getDescrizioneProblema());
            stmt.setInt(2,problema.getUtente().getIdUtente());
            stmt.setString(3,problema.getHackathon().getTitolo());
            stmt.executeUpdate();

        }
    }

    /**
     * Usa DELETE per cancellare il problema
     * */
    @Override
    public void deleteProblema(String titoloHackathon, int idUtente) throws SQLException {
        String sql = "DELETE FROM \"Hackathon\".\"Problema\" WHERE \"idUtente\"=? and \"titoloHackathon\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idUtente);
            stmt.setString(2, titoloHackathon);
            stmt.executeUpdate();

        }
    }

    /**
     * Riempe la lista Problema passato, si trova dalla query di SELECT con titoloHackathon passato
     * */
    @Override
    public void getListProblema(String titoloHackathon, List<Problema> problemaList) throws SQLException {
        String sql = "SELECT \"idUtente\", \"descrizioneProblema\" " +
                     "FROM \"Hackathon\".\"Problema\" " +
                     "WHERE \"titoloHackathon\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, titoloHackathon);
            ResultSet rs = stmt.executeQuery();

            Hackathon hackathon = new HackathonDAOImpl().getHackathonByTitolo(titoloHackathon);

            while(rs.next()) {
                Utente utente = new UtenteDAOImpl().getUtenteById(rs.getInt("idUtente"));
                String descrizioneProblema = rs.getString("descrizioneProblema");

                if(hackathon != null && utente != null) {
                    Problema problema = new Problema(descrizioneProblema, utente, hackathon);
                    problemaList.add(problema);
                }
            }

            rs.close();
        }
    }
}
