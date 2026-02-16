package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.InvitoDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Invito;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;

import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link InvitoDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres crea e risponde un'invito e
 *  tramite la query di SELECT legge la lista di invito.</p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class InvitoDAOImpl implements InvitoDAO {

    /**
     * Chiama la procedura su Postgres per creare un nuovo invito leggendo l'informazioni dall'oggetto dell'Invito
     * */
    @Override
    public void creaInvito(Invito invito) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Invito\"(?,?,?,?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)) {

            stmt.setString(1, invito.getTipoInvito());
            stmt.setInt(2, invito.getUtenteMittente().getIdUtente());
            stmt.setInt(3, invito.getUtenteDestinatario().getIdUtente());
            if(invito.getTeam() != null){
                stmt.setInt(4, invito.getTeam().getIdTeam());
            }
            else{
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setString(5, invito.getHackathon().getTitolo());
            stmt.setString(6, invito.getMotivazione());
            stmt.execute();

        }
    }

    /**
     * Tramite la query di SELECT si trova tutti gli inviti relativi all'utente che corrisponde id utente passato.
     * */
    @Override
    public void getListInvito(int idUtente, List<Invito> invitoList) throws SQLException {
        Invito invito;
        String sql = "SELECT \"idInvito\", motivazione, \"tipoInvito\", stato, \"idUtenteMittente\", " +
                     "\"idUtenteDestinatario\", \"titoloHackathon\", \"idTeam\" " +
                     "FROM \"Hackathon\".\"Invito\" " +
                     "WHERE \"idUtenteMittente\" = ? OR \"idUtenteDestinatario\" = ? " +
                     "ORDER BY \"idInvito\" DESC";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idUtente);
            stmt.setInt(2, idUtente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String motivazione = rs.getString("motivazione");
                String tipoInvito = rs.getString("tipoInvito");
                String stato = rs.getString("stato");
                Utente utenteMit = new UtenteDAOImpl().getUtenteById(rs.getInt("idUtenteMittente"));
                Utente utenteDes = new UtenteDAOImpl().getUtenteById(rs.getInt("idUtenteDestinatario"));
                Hackathon hackathon = new HackathonDAOImpl().getHackathonByTitolo(rs.getString("titoloHackathon"));
                Team team = null;
                if(rs.getInt("idTeam") != 0){
                     team = new TeamDAOImpl().getTeamById(rs.getInt("idTeam"));
                }

                invito = new Invito(motivazione, tipoInvito, stato, utenteMit, utenteDes, hackathon, team);
                invito.setIdInvito(rs.getInt("idInvito"));

                invitoList.add(invito);
            }

            rs.close();
        }
    }

    /**
     * Chiama la procedura su Postgres per rispondere un'invito in attesa, rispetta la risposta
     * verrà creato una nuova partecipazione se la risposta è accettato invece se la risposta
     * è rifiutato verrà solo aggiornato la risposta dell'invito.
     * */
    @Override
    public void rispondeInvito(int idInvito, String risposta) throws SQLException {
        String sql = "CALL \"Hackathon\".\"risponde_Invito\"(?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setInt(1, idInvito);
            stmt.setString(2, risposta);
            stmt.execute();
        }
    }
}
