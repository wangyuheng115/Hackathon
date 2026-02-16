package it.unina.poo.dao;

import it.unina.poo.model.ClassificaVotoTeam;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare sulla {@link  ClassificaVotoTeam}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface ClassificaVotoTeamDAO {
    /**
     * Ottenere la classifica dei voti del team
     *
     * @param listClassifica la lista della {@link  ClassificaVotoTeam}
     * @param titoloHackathon il titolo dell'hackathon
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListClassificaVotoTeam(List<ClassificaVotoTeam> listClassifica, String titoloHackathon) throws SQLException;
}
