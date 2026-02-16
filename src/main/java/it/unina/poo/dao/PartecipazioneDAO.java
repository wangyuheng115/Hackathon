package it.unina.poo.dao;

import it.unina.poo.model.Partecipazione;

import java.sql.SQLException;
import java.util.List;
/**
 * La interface che definisce le operazioni da implementare su {@link Partecipazione}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface PartecipazioneDAO {
    /**
     * Ottenere la lista di partecipazione di un'Hackathon
     *
     * @param titoloHackathon titolo di hackathon
     * @param partecipazioneList lista di {@link Partecipazione} di hackathon
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void getListPartecipazione(String titoloHackathon, List<Partecipazione> partecipazioneList)throws SQLException;
     /**
      * Ottenere il ruolo di un'utente all'hackathon
      *
      * @param titoloHackathon titolo di hackathon
      * @param idUtente id di utente
      * @throws SQLException quando la operazione su Postgres fallisce
      * */
     String getRuoloPartecipazione(String titoloHackathon, int idUtente)throws  SQLException;
     /**
      * Utente partecipa all'hackathon
      *
      * @param idUtente id di utente
      * @param titoloHackathon titolo di hackathon da partecipare
      * @throws SQLException quando la operazione su Postgres fallisce
      * */
     void partecipaHackathon(int idUtente, String titoloHackathon) throws SQLException;
}
