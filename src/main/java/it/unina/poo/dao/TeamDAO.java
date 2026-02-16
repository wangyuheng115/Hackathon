package it.unina.poo.dao;

import it.unina.poo.model.Team;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare per {@link Team}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface TeamDAO {
    /**
     * Crea un {@link Team}
     *
     * @param team Oggetto della classe {@link Team}
     * @param idUtente id dell'utente
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void creaTeam(Team team, int idUtente) throws SQLException;
    /**
     * Ottenere la lista di {@link Team} dell'hakathon
     *
     * @param teams Lista di {@link Team}
     * @param titoloHackathon titolo dell'hackathon
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListTeams(List<Team> teams, String titoloHackathon) throws SQLException;
    /**
     * Ottenere un'oggetto di {@link Team} da idTeam
     *
     *
     * @param idTeam id del team
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    Team getTeamById(int idTeam) throws SQLException;
    /**
     * Ottenere un'oggetto di {@link Team} da utente e hackathon
     *
     * @param idUtente id dell'utente
     * @param titoloHackathon titolo dell'hackathon
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    Team getTeamByUserHackathon(int idUtente, String titoloHackathon) throws SQLException;
}
