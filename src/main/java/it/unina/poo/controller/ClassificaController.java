package it.unina.poo.controller;

import it.unina.poo.dao.ClassificaVotoTeamDAO;
import it.unina.poo.dao.TeamDAO;
import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.dao.VotoDAO;
import it.unina.poo.implementazionipostgresdao.ClassificaVotoTeamDAOImpl;
import it.unina.poo.implementazionipostgresdao.TeamDAOImpl;
import it.unina.poo.implementazionipostgresdao.UtenteDAOImpl;
import it.unina.poo.implementazionipostgresdao.VotoDAOImpl;
import it.unina.poo.model.ClassificaVotoTeam;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;
import it.unina.poo.model.Voto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassificaController {
    private final ClassificaVotoTeamDAO classificaVotoTeamDAO;
    private final VotoDAO votoDAO;
    private final TeamDAO teamDAO;
    private final UtenteDAO utenteDAO;
    private final ArrayList<ClassificaVotoTeam> listClassificaVotoTeam =  new ArrayList<>();
    private final ArrayList<Voto> listVoto =  new ArrayList<>();

    public ClassificaController() {
        classificaVotoTeamDAO = new ClassificaVotoTeamDAOImpl();
        votoDAO = new VotoDAOImpl();
        teamDAO = new TeamDAOImpl();
        utenteDAO = new UtenteDAOImpl();
    }

    public boolean initListClassificaVotoTeam(String titoloHackathon) {
        try {
            listClassificaVotoTeam.clear();
            classificaVotoTeamDAO.getListClassificaVotoTeam(listClassificaVotoTeam, titoloHackathon);
            return true;
        } catch (SQLException e) {
            return false;
        }

    }

    public List<ClassificaVotoTeam> getListClassificaVotoTeam() {
        return  listClassificaVotoTeam;
    }

    public boolean valutaTeam(int idUtente, int idTeam, float valore){
        try {
            Team team = teamDAO.getTeamById(idTeam);
            Utente utente = utenteDAO.getUtenteById(idUtente);
            votoDAO.createVoto(new Voto(valore, team, utente));

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean getListVoto(int idTeam, int idGiudice, String ruolo){
        try{
            listVoto.clear();
            votoDAO.getListVotoOfTeam(idTeam, idGiudice, ruolo, listVoto);

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Voto> getListVoto() {
        return listVoto;
    }

    public boolean modificaValutazione(float newValore, int idVoto){
        try{
            votoDAO.modificatVoto(newValore, idVoto);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
