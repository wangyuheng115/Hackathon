package it.unina.poo.dao;

import it.unina.poo.model.Utente;

import java.sql.SQLException;
import java.util.List;
/**
 * La interface che definisce le operazioni da implementare sugli utenti.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface UtenteDAO {
    /**
     * Crea un nuovo {@link Utente}.
     *
     * @param utente un oggetto della classe {@link Utente} che non è null
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void creaUtente(Utente utente) throws SQLException;
    /**
     * Aggiorna il nome di utente.
     *
     * @param nomeUtente nuovo nome da aggiornare
     * @param idUtente id di utente da aggiornare
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void modificaNomeUtente(String nomeUtente, int idUtente) throws SQLException;
    /**
     * Aggiorna l'email di utente.
     *
     * @param emailUtente nuovo email da aggiornare
     * @param idUtente id di utente da aggiornare
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void modificaEmailUtente(String emailUtente, int idUtente) throws SQLException;
    /**
     * Aggiorna la password di utente.
     *
     * @param passwordUtente nuovo email da aggiornare
     * @param idUtente id di utente da aggiornare
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void modificaPasswordUtente(String passwordUtente, int idUtente) throws SQLException;
    /**
     * Ottenere un utente da id.
     *
     * @param idUtente id di utente da ottenere
     * @throws SQLException quando la operazione su Postgres fallisce
     * @return un oggetto di utente
     * */
     Utente getUtenteById(int idUtente) throws SQLException;
    /**
     * Ottenere un utente tramite email e password.
     *
     * @param email email di utente
     * @param password password di utente
     * @throws SQLException quando la operazione su Postgres fallisce
     * @return un oggetto di utente
     * */
     Utente loginUtente(String email, String password) throws SQLException;
    /**
     * Riempire la lista {@link Utente} da inviatare passato.
     *
     * @param utenteList lista {@link Utente} da invitare
     * @param titoloHacakthon hackathon da considerare
     * @param tipo tipo di invito (giudice/partecipante)
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void getListInvito(List<Utente> utenteList, String titoloHacakthon, String tipo) throws SQLException;
}

