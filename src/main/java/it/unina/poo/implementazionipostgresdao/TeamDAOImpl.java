package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.HackathonDAO;
import it.unina.poo.dao.TeamDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Team;

import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link TeamDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare nuovo team e leggere i teams creati </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class TeamDAOImpl implements TeamDAO {

    private static final String IDTEAM = "idTeam";

    /**
     * Chiama la procedura Postgres, leggendo le informazioni del team dall'oggetto team
     * crea nuovo team per hackathon e collegando utente trovato da idUtente con team creato.
     * */
    @Override
    public void creaTeam(Team team, int idUtente) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Team\"(?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement preparedStatement = connection.prepareCall(sql)){

            preparedStatement.setString(1, team.getNome());
            preparedStatement.setString(2, team.getHackathon().getTitolo());
            preparedStatement.setInt(3, idUtente);
            preparedStatement.execute();

        }
    }

    /**
     * Riempe la lista teams passato, i teams sono tutti quelli collegati con Hackathon trovato da titoloHackathon passato.
     * */
    @Override
    public void getListTeams(List<Team> teams, String titoloHackathon) throws SQLException {
        String sql = "SELECT \"idTeam\", nome " +
                     "FROM \"Hackathon\".\"Team\" " +
                     "WHERE \"titoloHackathon\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt =  connection.prepareStatement(sql)){
            stmt.setString(1, titoloHackathon);
            ResultSet rs = stmt.executeQuery();
            HackathonDAO hackathonDAO = new HackathonDAOImpl();
            Hackathon hackathon = hackathonDAO.getHackathonByTitolo(titoloHackathon);

            while(rs.next()){
                int idTeam = rs.getInt(IDTEAM);
                String nome = rs.getString("nome");

                teams.add(new Team(idTeam, nome, hackathon));
            }

            rs.close();
        }
    }

    /**
     * Con idTeam passato trova dalla tabella Team tutte le informazioni del team e return questo team.
     * */
    @Override
    public Team getTeamById(int idTeam) throws SQLException {
        Team team = null;
        String sql = "SELECT \"idTeam\", nome, \"titoloHackathon\" FROM \"Hackathon\".\"Team\" WHERE \"idTeam\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setInt(1, idTeam);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Hackathon hackathon = new HackathonDAOImpl().getHackathonByTitolo(resultSet.getString("titoloHackathon"));
                if(hackathon != null){
                    team = new Team(resultSet.getInt(IDTEAM),resultSet.getString("nome"),hackathon);
                }
            }

            resultSet.close();
        }

        return team;
    }

    /**
     * La query di SELECT ritorna il team dell'utente all'hackathon.
     * */
    @Override
    public Team getTeamByUserHackathon(int idUtente, String titoloHackathon) throws SQLException {
        String sql = "SELECT t.\"idTeam\", t.nome, t.\"titoloHackathon\" " +
                     "FROM \"Hackathon\".\"Team_Partecipante\" tp " +
                     "INNER JOIN \"Hackathon\".\"Team\" t on tp.\"idTeam\" = t.\"idTeam\" and t.\"titoloHackathon\" = ? " +
                     "WHERE tp.\"idUtente\" = ? ";

        Team team = null;
        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1, titoloHackathon);
            stmt.setInt(2, idUtente);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Hackathon hackathon = new HackathonDAOImpl().getHackathonByTitolo(resultSet.getString("titoloHackathon"));
                if(hackathon != null){
                    team = new Team(resultSet.getInt(IDTEAM), resultSet.getString("nome"), hackathon);
                }
            }
        }

        return team;
    }
}
