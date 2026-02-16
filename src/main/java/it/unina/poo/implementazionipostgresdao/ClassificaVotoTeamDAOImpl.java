package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.ClassificaVotoTeamDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.ClassificaVotoTeam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *  La classe che implementa interface{@link ClassificaVotoTeamDAO}.
 *
 *  <p>Attraverso la chiamata della procedura implementata su Postgres ottenere la classifica dei voti del team</p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class ClassificaVotoTeamDAOImpl implements ClassificaVotoTeamDAO {
    /**
     * Ottenere la classifica tramite la procedura implementata su Postgres
     * */
    @Override
    public void getListClassificaVotoTeam(List<ClassificaVotoTeam> listClassifica, String titoloHackathon) throws SQLException {
        String sql = "SELECT idteam, team_nome, punteggio_medio " +
                     "FROM \"Hackathon\".get_classifica_voto_team(?)";

        try(Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, titoloHackathon);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                int idteam = rs.getInt("idteam");
                String nome = rs.getString("team_nome");
                float punteggioMedio = rs.getFloat("punteggio_medio");

                listClassifica.add(new ClassificaVotoTeam(idteam, nome, punteggioMedio));
            }

            rs.close();
        }
    }
}
