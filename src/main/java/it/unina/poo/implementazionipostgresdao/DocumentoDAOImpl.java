package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.DocumentoDAO;
import it.unina.poo.dao.TeamDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Documento;
import it.unina.poo.model.Team;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

/**
 *  La classe che implementa interface{@link DocumentoDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  creare, modficare, cancellare e leggere i documenti </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class DocumentoDAOImpl implements DocumentoDAO {

    /**
     * Chiama la procedura Postgres per salvare il documento passato nella tabella Problema.
     * */
    @Override
    public void creaDocumento(Documento documento) throws SQLException {
        String sql = "CALL \"Hackathon\".\"insert_Documento\"(?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            CallableStatement stmt = connection.prepareCall(sql)){

            stmt.setInt(1,documento.getTeam().getIdTeam());
            stmt.setString(2,documento.getDocumento());
            stmt.execute();

        }
    }

    /**
     * Tramite idDocumento trovare il documento da modificare e set il nuovo contenuto del documento passato
     * */
    @Override
    public void modificaDocumentoContent(String documentoContent, int idDocumento) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Documento\" " +
                     "Set documento = ? " +
                     "WHERE \"idDocumento\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setString(1,documentoContent);
            stmt.setInt(2,idDocumento);
            stmt.executeUpdate();

        }
    }

    /**
     * Cancellare il documento trovato da idDocumento passato dalla tabella Documento
     * */
    @Override
    public void deleteDocumento(int idDocumento) throws SQLException {
        String sql = "DELETE FROM \"Hackathon\".\"Documento\" WHERE \"idDocumento\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1,idDocumento);
            stmt.executeUpdate();

        }
    }

    /**
     * Ottenere la lista dei documenti del team che corrisponde idTeam passato
     * */
    @Override
    public void getListDocumentoByTeam(List<Documento> listDocumento, int idTeam) throws SQLException {
        String sql = "SELECT \"idDocumento\", data, documento " +
                     "FROM \"Hackathon\".\"Documento\" " +
                     "WHERE \"idTeam\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)){

            stmt.setInt(1,idTeam);
            ResultSet rs = stmt.executeQuery();

            TeamDAO teamDAO = new TeamDAOImpl();

            Team team = teamDAO.getTeamById(idTeam);

            while(rs.next()){
                int idDocumento = rs.getInt("idDocumento");
                LocalDate data = rs.getDate("data").toLocalDate();
                String documentoContent = rs.getString("documento");

                listDocumento.add(new Documento(idDocumento, data, documentoContent, team));
            }

            rs.close();
        }
    }
}
