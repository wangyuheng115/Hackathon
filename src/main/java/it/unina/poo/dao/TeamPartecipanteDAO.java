package it.unina.poo.dao;

import it.unina.poo.model.TeamPartecipante;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare sui partecipanti del team.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface TeamPartecipanteDAO {
    /**
     * Ottenere la lista dei partecipanti del team
     *
     * @param idTeam id del team
     * @param teamPartecipanteList la lista di {@link TeamPartecipante} da riempire
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListTeamPartecipante(int idTeam, List<TeamPartecipante> teamPartecipanteList) throws SQLException;
}
