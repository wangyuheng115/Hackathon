package it.unina.poo.dao;

import it.unina.poo.model.Hackathon;

import java.sql.SQLException;
import java.util.List;
/**
 * La interface che definisce le operazioni da implenmentare su {@link Hackathon}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface HackathonDAO {
    /**
     * Crea un nuovo {@link Hackathon}.
     *
     * @param hackathon oggetto della classe {@link Hackathon}
     * @param idUtente id utente organizzatore
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void creaHackathon(Hackathon hackathon, int idUtente) throws SQLException;
    /**
     * Riempire la lista {@link Hackathon} passato
     *
     * @param listHackathon lista di {@link Hackathon} da riempire
     * @param type tipo della lista Hackathon(Disponibili/In Corso/Chiusi)
     * @param idUtente id utente
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void getAllHackathon(List<Hackathon> listHackathon, String type, int idUtente) throws SQLException;
    /**
     * Ottenere {@link Hackathon} da titolo
     *
     * @param titolo titolo di Hackathon
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     Hackathon getHackathonByTitolo(String titolo) throws SQLException;
}
