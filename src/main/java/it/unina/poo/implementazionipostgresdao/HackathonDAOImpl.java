package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.HackathonDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Sede;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 *  La classe che implementa interface{@link HackathonDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare nuovo hackathon e leggere le hackathon create </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class HackathonDAOImpl implements HackathonDAO {

    /**
     * Usando la procedura realizzata su Postgres per creare un nuovo hackathon.
     * */
    @Override
    public void creaHackathon(Hackathon hackathon,int idUtente) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Hackathon\"(?,?,?,?,?,?,?)";
        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setString(1,hackathon.getTitolo());
            stmt.setDate(2, Date.valueOf(hackathon.getInizio()));
            stmt.setInt(3,hackathon.getNumeroMassimoDiIscrizioni());
            stmt.setInt(4,hackathon.getNumeroMassimoPersonePerTeam());
            stmt.setDate(5,Date.valueOf(hackathon.getInizioIscrizioni()));
            stmt.setInt(6,hackathon.getSede().getIdSede());
            stmt.setInt(7,idUtente);
            stmt.execute();
        }
    }

    /**
     * Riceve la lista di hackathon da riempire, e in base al type si trovano i hackathon disponibili, in corso o chiusi
     * per l'utente che corrisponde idUtente passato.
     * */
    @Override
    public void getAllHackathon(List<Hackathon> listHackathon, String type, int idUtente) throws SQLException {
        String condizioneSql;

        switch (type){
            case "Disponibili":
                condizioneSql = "WHERE NOT EXISTS (SELECT 1 FROM \"Hackathon\".\"Partecipazione\" p2 WHERE p2.\"titoloHackathon\" = h.\"titolo\" AND p2.\"idUtente\" = ?) and CURRENT_DATE BETWEEN h.\"inizioIscrizioni\" AND h.\"fineIscrizioni\" ";
                break;
            case "In Corso":
                condizioneSql = "WHERE p.\"idUtente\" = ? and h.fine >= CURRENT_DATE ";
                break;
            default:
                condizioneSql = "WHERE p.\"idUtente\" = ? and h.fine < CURRENT_DATE ";
        }

        String sql = "SELECT DISTINCT h.titolo, h.inizio, h.fine, h.\"numeroMassimoDiIscrizione\", h.\"numeroMassimoPersonePerTeam\", " +
                     "h.\"inizioIscrizioni\", h.\"fineIscrizioni\", h.\"idSede\" " +
                     "FROM \"Hackathon\".\"Hackathon\" h " +
                     "JOIN \"Hackathon\".\"Partecipazione\" p ON h.\"titolo\" = p.\"titoloHackathon\" " +
                      condizioneSql+
                     "ORDER BY \"inizioIscrizioni\" DESC";

        String titolo;
        LocalDate inizio;
        LocalDate fine;
        int numeroMassimoDiIscrizione;
        int numeroMassimoPersonePerTeam;
        LocalDate inizioIscrizioni;
        LocalDate fineIscrizioni;
        Sede sede;

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                titolo = rs.getString("titolo");
                inizio = rs.getDate("inizio").toLocalDate();
                fine = rs.getDate("fine").toLocalDate();
                numeroMassimoDiIscrizione = rs.getInt("numeroMassimoDiIscrizione");
                numeroMassimoPersonePerTeam = rs.getInt("numeroMassimoPersonePerTeam");
                inizioIscrizioni = rs.getDate("inizioIscrizioni").toLocalDate();
                fineIscrizioni = rs.getDate("fineIscrizioni").toLocalDate();
                sede = new SedeDAOImpl().getSedeById(rs.getInt("idSede"));
                listHackathon.add(new Hackathon(titolo, inizio, fine,numeroMassimoDiIscrizione, numeroMassimoPersonePerTeam, inizioIscrizioni, fineIscrizioni, sede));
            }

            rs.close();
        }
    }

    /**
     * Ottenere un oggetto di Hackathon che corrisponde il titolo passato.
     * */
    @Override
    public Hackathon getHackathonByTitolo(String titolo) throws SQLException {
        Hackathon hackathon = null;
        String sql = "SELECT titolo, inizio, fine, \"numeroMassimoDiIscrizione\", \"numeroMassimoPersonePerTeam\", " +
                     "\"inizioIscrizioni\", \"fineIscrizioni\", \"idSede\" " +
                     "FROM \"Hackathon\".\"Hackathon\" " +
                     "WHERE titolo=?";

        try (Connection connection = DatabaseConnection.getInstance().getConnection();
        PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, titolo);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                LocalDate inizio = resultSet.getDate("inizio").toLocalDate();
                LocalDate fine = resultSet.getDate("fine").toLocalDate();
                int numeroMassimoDiIscrizione =  resultSet.getInt("numeroMassimoDiIscrizione");
                int numeroMassimoPersonePerTeam = resultSet.getInt("numeroMassimoPersonePerTeam");
                LocalDate inizioIscrizioni = resultSet.getDate("inizioIscrizioni").toLocalDate();
                LocalDate fineIscrizioni = resultSet.getDate("fineIscrizioni").toLocalDate();
                Sede sede = new SedeDAOImpl().getSedeById(resultSet.getInt("idSede"));

                if(sede != null){
                    hackathon = new Hackathon(titolo, inizio, fine, numeroMassimoDiIscrizione, numeroMassimoPersonePerTeam, inizioIscrizioni, fineIscrizioni, sede);
                }
            }

            resultSet.close();
        }

        return hackathon;
    }
}
