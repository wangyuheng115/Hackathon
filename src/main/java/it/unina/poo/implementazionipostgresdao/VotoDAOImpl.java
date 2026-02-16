package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.VotoDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;
import it.unina.poo.model.Voto;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;

/**
 *  La classe che implementa interface{@link VotoDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare, aggiornare e leggere le valutazioni</p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class VotoDAOImpl implements VotoDAO {

    /**
     * Chiama la procedura su Postgres per inserire un nuovo voto alla tabella Voto
     * */
    @Override
    public void createVoto(Voto voto) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Voto\"(?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setInt(1, voto.getGiudice().getIdUtente());
            stmt.setInt(2, voto.getTeam().getIdTeam());
            stmt.setBigDecimal(3, BigDecimal.valueOf(voto.getValore()));
            stmt.execute();

        }
    }

    /**
     * Modifica il valore della valutazione che corrisponde idVoto passato.
     * */
    @Override
    public void modificatVoto(float valore, int idVoto) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Voto\" " +
                     "SET valore = ? " +
                     "WHERE \"idVoto\" = ? ";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setFloat(1, valore);
            stmt.setInt(2, idVoto);
            stmt.executeUpdate();

        }
    }

    /**
     * Riempe la lista di Voto passato, verifica il ruolo, se è partecipante normale può visualizzare tutte le valutazioni
     * mentre se è un giudice può vedere solo le valutazioni di se stesso.
     * */
    @Override
    public void getListVotoOfTeam(int idTeam, int idGiudice, String ruolo, List<Voto> votoList) throws SQLException {
        String sql = getTipoListVoto(ruolo);

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1, idTeam);
            if (ruolo.equals("giudice")){
                stmt.setInt(2, idGiudice);
            }

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int idVoto = rs.getInt("idVoto");
                float valoreVoto = rs.getFloat("valore");
                Team team = new TeamDAOImpl().getTeamById(idTeam);
                Utente giudice = new UtenteDAOImpl().getUtenteById(rs.getInt("idGiudice"));

                if (team != null && giudice != null){
                    Voto voto = new Voto(idVoto,valoreVoto, team, giudice);
                    votoList.add(voto);
                }
            }

            rs.close();
        }
    }

    private static String getTipoListVoto(String ruolo) {
        String sql;
        if(ruolo.equals("giudice")){
            sql = "SELECT \"idVoto\", valore, \"idGiudice\" " +
                    "FROM \"Hackathon\".\"Voto\" " +
                    "WHERE \"idTeam\" = ? AND \"idGiudice\" = ? " +
                    "ORDER BY \"idVoto\"";
        }else{
            sql = "SELECT \"idVoto\", valore, \"idGiudice\" " +
                    "FROM \"Hackathon\".\"Voto\" " +
                    "WHERE \"idTeam\" = ? " +
                    "ORDER BY \"idVoto\"";
        }
        return sql;
    }
}
