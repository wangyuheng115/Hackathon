package it.unina.poo.dao;

import it.unina.poo.model.Problema;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare per {@link Problema}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface ProblemaDAO {
    /**
     * Crea un {@link Problema}
     *
     * @param problema Oggetto della classe {@link Problema}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void creaProblema(Problema problema)throws SQLException;
    /**
     * Modifica il contenuto del {@link Problema}
     *
     * @param problema Oggetto della classe {@link Problema}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void modificaDescrizioneProblema(Problema problema)throws SQLException;
    /**
     * Cancellare il problema dalla tabella
     *
     * @param titoloHackathon titolo dell'hackathon
     * @param idUtente id dell'utente
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void deleteProblema(String titoloHackathon, int idUtente)throws SQLException;
    /**
     * Ottenere la lista della {@link Problema}
     *
     * @param titoloHackathon titolo dell'hackathon
     * @param problemaList la lista di {@link Problema}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListProblema(String titoloHackathon, List<Problema> problemaList)throws SQLException;
}
