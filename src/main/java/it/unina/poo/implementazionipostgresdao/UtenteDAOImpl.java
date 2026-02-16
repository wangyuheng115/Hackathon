package it.unina.poo.implementazionipostgresdao;

import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.db.DatabaseConnection;
import it.unina.poo.model.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
/**
 *  La classe che implementa interface{@link UtenteDAO}.
 *
 *  <p>Attraverso la chiamata delle procedure implementate su Postgres e alcune query per
 *  salvare, modificare o leggere i dati degli utenti. </p>
 *
 * @author Yuheng Wang
 * @version  1.0
 * */
public class UtenteDAOImpl implements UtenteDAO {

    private static final Logger LOGGER = Logger.getLogger(UtenteDAOImpl.class.getName());

    private static final String IDUTENTE = "idUtente";
    private static final String NOME = "nome";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    /**
     * Usando la procedura realizzata su Postgres per creare un nuovo utente.
     * */
    @Override
    public void creaUtente(Utente utente) throws SQLException {
        //Crea utente con la procedura già realizzata
        String callStatment = "CALL \"Hackathon\".\"insert_Utente\"(?,?,?)";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement callStmt = connection.prepareStatement(callStatment)) {

            callStmt.setString(1, utente.getNome());
            callStmt.setString(2, utente.getEmail());
            callStmt.setString(3, utente.getPassword());
            callStmt.execute();

        }
    }

    /**
     * Usando la query di UPDATE per aggiornare il nome dell'utente attraverso id utente.
     * */
    @Override
    public void modificaNomeUtente(String nomeUtente, int idUtente) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Utente\" " +
                     "SET nome=? " +
                     "WHERE \"idUtente\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,nomeUtente);
            stmt.setInt(2,idUtente);
            stmt.execute();

        }
    }

    /**
     * Usando la query di UPDATE per aggiornare l'email dell'utente attraverso id utente.
     * */
    @Override
    public void modificaEmailUtente(String emailUtente, int idUtente) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Utente\" " +
                     "SET email=? " +
                     "WHERE \"idUtente\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,emailUtente);
            stmt.setInt(2,idUtente);
            stmt.execute();
        }
    }

    /**
     * Usando la query di UPDATE per aggiornare la password dell'utente attraverso id utente.
     * */
    @Override
    public void modificaPasswordUtente(String passwordUtente, int idUtente) throws SQLException {
        String sql = "UPDATE \"Hackathon\".\"Utente\" " +
                     "SET password=? " +
                     "WHERE \"idUtente\"=?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1,passwordUtente);
            stmt.setInt(2,idUtente);
            stmt.execute();

        }
    }

    /**
     * Usando la query di SELECT per ottenere un ResultSet e istanzia un oggetto di utente.
     * */
    @Override
    public Utente getUtenteById(int idUtente) throws SQLException {
        Utente utente = null;
        String sql = "SELECT \"idUtente\", nome, email, password " +
                     "FROM \"Hackathon\".\"Utente\" " +
                     "WHERE \"idUtente\" = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idUtente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                utente = new Utente(rs.getInt(IDUTENTE), rs.getString(NOME), rs.getString(EMAIL), rs.getString(PASSWORD));
            }

            rs.close();
        }catch (SQLException e){
            LOGGER.severe("Errore durante getUtenteById: "+e.getMessage());
        }

        return utente;
    }

    /**
     * Usando la query di SELECT per ottenere un ResultSet e istanzia un oggetto di utente,
     * questo oggetto di utente può anche essere null.
     * */
    public Utente loginUtente(String email, String password) throws SQLException  {
        Utente utente = null;
        String sql = "SELECT \"idUtente\", nome, email, password " +
                     "FROM \"Hackathon\".\"Utente\" " +
                     "WHERE email = ? AND password = ?";

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, email.trim());
            stmt.setString(2, password.trim());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                utente = new Utente(rs.getInt(IDUTENTE), rs.getString(NOME), rs.getString(EMAIL), rs.getString(PASSWORD));
            }
            rs.close();

        }

        return utente;
    }

    /**
     * Attraverso due tipi di query per riempire la List di utente da invitare, e si scorre un ResultSet.
     * */
    @Override
    public void getListInvito(List<Utente> utenteList, String titoloHacakthon, String tipo) throws SQLException {
        String sql = getString(tipo);

        try(Connection connection = DatabaseConnection.getInstance().getConnection();
            PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, titoloHacakthon);
            stmt.setString(2, titoloHacakthon);
            ResultSet rs = stmt.executeQuery();
            int idUtente;
            String nomeUtente;
            String emailUtente;
            String passwordUtente;

            while (rs.next()) {
                idUtente = rs.getInt(IDUTENTE);
                nomeUtente = rs.getString(NOME);
                emailUtente = rs.getString(EMAIL);
                passwordUtente = rs.getString(PASSWORD);

                if (idUtente > 0 && !nomeUtente.isEmpty() && !emailUtente.isEmpty() && !passwordUtente.isEmpty()) {
                    utenteList.add(new Utente(idUtente,  nomeUtente, emailUtente, passwordUtente));
                }
            }
            rs.close();
        }
    }

    private static String getString(String tipo) {
        String sql;
        if (tipo.equals("giudice")){
             sql = "SELECT DISTINCT u.\"idUtente\", u.nome, u.email, u.password " +
                    "FROM \"Hackathon\".\"Utente\" u " +
                    "WHERE NOT EXISTS(SELECT 1 FROM \"Hackathon\".\"Partecipazione\" p WHERE p.\"titoloHackathon\" = ? AND p.\"idUtente\" = u.\"idUtente\") " +
                    "AND NOT EXISTS(SELECT 1 FROM \"Hackathon\".\"Invito\" i WHERE i.\"titoloHackathon\" = ? AND i.stato = 'in attesa' AND i.\"idUtenteDestinatario\" = u.\"idUtente\")" +
                    "ORDER BY u.nome ASC";
        }else {
             sql = "SELECT DISTINCT u.\"idUtente\", u.nome, u.email, u.password " +
                    "FROM \"Hackathon\".\"Utente\" u " +
                    "WHERE EXISTS(SELECT 1 FROM \"Hackathon\".\"Partecipazione\" p WHERE p.\"titoloHackathon\" = ? AND p.\"idUtente\" = u.\"idUtente\" AND p.ruolo = 'partecipante') " +
                    "AND NOT EXISTS(SELECT 1 FROM \"Hackathon\".\"Team_Partecipante\" tp " +
                    "               INNER JOIN \"Hackathon\".\"Team\" t ON tp.\"idTeam\" = t.\"idTeam\" " +
                    "               WHERE t.\"titoloHackathon\" = ? AND tp.\"idUtente\" = u.\"idUtente\") " +
                    "ORDER BY u.nome ASC";
        }
        return sql;
    }
}
