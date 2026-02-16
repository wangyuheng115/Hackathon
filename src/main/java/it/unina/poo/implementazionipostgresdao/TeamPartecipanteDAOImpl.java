package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.TeamPartecipanteDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Team;
import it.unina.poo.model.TeamPartecipante;
import it.unina.poo.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *  La classe che implementa interface{@link TeamPartecipanteDAO}.
 *
 *  <p>Attraverso la query lavora sulla tabella Team_Partecipante</p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class TeamPartecipanteDAOImpl implements TeamPartecipanteDAO {

    /**
     * Riempe la lista di TeamPartecipante passato, trovando tutti id di utenti del team tramite idTeam
     * poi si utilizza TeamDAOImpl().getTeamById() e UtenteDAOImpl().getUtenteById() per avere oggetto di team e
     * oggetto di utente per costruire un TeamPartecipante.
     * */
    @Override
    public void getListTeamPartecipante(int idTeam, List<TeamPartecipante> teamPartecipanteList) throws SQLException {
        String  sql = "SELECT \"idUtente\" " +
                      "FROM \"Hackathon\".\"Team_Partecipante\" " +
                      "WHERE \"idTeam\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idTeam);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Team team = new TeamDAOImpl().getTeamById(idTeam);
                Utente utente = new UtenteDAOImpl().getUtenteById(rs.getInt("idUtente"));

                if(team != null && utente != null){
                    TeamPartecipante teamPartecipante = new TeamPartecipante(team,utente);
                    teamPartecipanteList.add(teamPartecipante);
                }
            }

            rs.close();
        }
    }

}
