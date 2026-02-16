package it.unina.poo.dao;

import it.unina.poo.model.Invito;

import java.sql.SQLException;
import java.util.List;
/**
 * La interface che definisce le operazioni da implementare su {@link Invito}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface InvitoDAO{
    /**
     * Crea un'invito
     *
     * @param invito un'oggetto della classe {@link Invito}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void creaInvito(Invito invito)throws SQLException;
    /**
     * Riempe la lista dell'{@link Invito}
     *
     * @param idUtente id utente
     * @param invitoList la lista {@link Invito} da riempire
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListInvito(int idUtente, List<Invito> invitoList)throws SQLException;
    /**
     * Modifica la risposta dell'invito
     *
     * @param idInvito id dell'invito
     * @param risposta la risposta dell'invito(accettato\rifiutato)
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void rispondeInvito(int idInvito, String risposta)throws SQLException;

}
