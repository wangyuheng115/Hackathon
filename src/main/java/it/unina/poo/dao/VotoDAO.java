package it.unina.poo.dao;

import it.unina.poo.model.Voto;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare su {@link Voto}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface VotoDAO {
    /**
     * Crea un {@link Voto}(valutazione).
     *
     * @param voto un'oggetto di {@link Voto}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void createVoto(Voto voto) throws SQLException;
    /**
     * Modificare la valutazione
     *
     * @param valore nuovo valore da 0 a 10
     * @param idVoto id di voto da modificare
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void modificatVoto(float valore, int idVoto) throws SQLException;
    /**
     * Ottenere la lista della valutazione
     *
     * @param idTeam id del team
     * @param idGiudice id del giudice
     * @param ruolo ruolo del utente
     * @param votoList la lista di {@link Voto} da riempire
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListVotoOfTeam(int idTeam, int idGiudice, String ruolo, List<Voto> votoList) throws SQLException;
}
