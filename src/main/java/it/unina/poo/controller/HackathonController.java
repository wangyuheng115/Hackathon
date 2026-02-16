package it.unina.poo.controller;

import it.unina.poo.dao.HackathonDAO;
import it.unina.poo.dao.PartecipazioneDAO;
import it.unina.poo.dao.TeamDAO;
import it.unina.poo.implementazionipostgresdao.HackathonDAOImpl;
import it.unina.poo.implementazionipostgresdao.PartecipazioneDAOImpl;
import it.unina.poo.implementazionipostgresdao.TeamDAOImpl;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Partecipazione;
import it.unina.poo.model.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class HackathonController {
    private final PartecipazioneDAO partecipazioneDAO;
    private final TeamDAO teamDAO;
    private final HackathonDAO hackathonDAO;
    private ArrayList<Partecipazione> listaPartecipazioni = new ArrayList<>();

    public HackathonController() {
        partecipazioneDAO = new PartecipazioneDAOImpl();
        teamDAO = new TeamDAOImpl();
        hackathonDAO = new HackathonDAOImpl();
    }

    public String getRuoloUtente(String titoloHackathon, int idUtente){
        try{
            return partecipazioneDAO.getRuoloPartecipazione(titoloHackathon, idUtente);
        } catch (SQLException e) {
            return "";
        }
    }

    public boolean getListaPartecipazioni(String titoloHackathon){
        try{
            listaPartecipazioni.clear();
            partecipazioneDAO.getListPartecipazione(titoloHackathon, listaPartecipazioni);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public ArrayList<Partecipazione> getListaPartecipazioni(){
        return listaPartecipazioni;
    }

    public Team getTeam(int idutente, String titoloHacakthon){
        try{
            return teamDAO.getTeamByUserHackathon(idutente, titoloHacakthon);
        }catch (SQLException e){
            return null;
        }
    }

    public boolean creaTeam(String nomeTeam, String titoloHackathon, int idUtente){
        try{
            Hackathon hackathon = hackathonDAO.getHackathonByTitolo(titoloHackathon);
            Team team = new Team(nomeTeam, hackathon);
            teamDAO.creaTeam(team, idUtente);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
