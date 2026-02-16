package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.PartecipazioneDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Partecipazione;
import it.unina.poo.model.Utente;

import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link PartecipazioneDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare nuova partecipazione, leggere il ruolo della partecipazione e lista delle partecipazioni </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class PartecipazioneDAOImpl implements PartecipazioneDAO {
    /**
     * Attraverso il titolo di hackathon si trovano tutte le informazioni di partecipazioni,
     * scorrendo il ResultSet e riempire la lista di partecipazione.
     * */
    @Override
    public void getListPartecipazione(String titoloHackathon, List<Partecipazione> partecipazioneList) throws SQLException {
        Partecipazione partecipazione;
        String sql = "SELECT \"idUtente\", ruolo " +
                     "FROM \"Hackathon\".\"Partecipazione\" " +
                     "WHERE \"titoloHackathon\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, titoloHackathon);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Hackathon hackathon = new HackathonDAOImpl().getHackathonByTitolo(titoloHackathon);
                Utente utente = new UtenteDAOImpl().getUtenteById(resultSet.getInt("idUtente"));
                String ruolo =  resultSet.getString("ruolo");
                if (hackathon != null && utente != null) {
                    partecipazione = new Partecipazione(ruolo, hackathon, utente);
                    partecipazioneList.add(partecipazione);
                }
            }

            resultSet.close();
        }
    }

    /**
     * Con titolo di hackathon e id di utente legge dalla partecipazione il ruolo di utente all'hackathon
     * */
    @Override
    public String getRuoloPartecipazione(String titoloHackathon, int idUtente) throws SQLException {
        String ruolo = null;

        String sql = "SELECT ruolo " +
                     "FROM \"Hackathon\".\"Partecipazione\" " +
                     "WHERE \"idUtente\" = ? and \"titoloHackathon\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, idUtente);
            preparedStatement.setString(2, titoloHackathon);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                ruolo = resultSet.getString("ruolo");
            }

            resultSet.close();
        }

        return ruolo;
    }

    /**
     * Chiamare la procedura realizzata su Postgres, verrà creata un record di partecipazionee con id utente e hackathon passato
     * */
    @Override
    public void partecipaHackathon(int idUtente, String titoloHackathon) throws SQLException {
        String sql = "CALL \"Hackathon\".\"partecipa_Hackathon\"(?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setInt(1, idUtente);
            stmt.setString(2, titoloHackathon);
            stmt.execute();

        }
    }
}
