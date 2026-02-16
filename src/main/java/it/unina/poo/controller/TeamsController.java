package it.unina.poo.controller;

import it.unina.poo.dao.DocumentoDAO;
import it.unina.poo.dao.TeamDAO;
import it.unina.poo.implementazionipostgresdao.DocumentoDAOImpl;
import it.unina.poo.implementazionipostgresdao.TeamDAOImpl;
import it.unina.poo.model.Documento;
import it.unina.poo.model.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class TeamsController {
    private final TeamDAO teamDAO;
    private ArrayList<Team> teams = new ArrayList<>();

    private final DocumentoDAO documentoDAO;
    private ArrayList<Documento> documentiList = new ArrayList<>();

    public TeamsController(){
        teamDAO = new TeamDAOImpl();
        documentoDAO = new DocumentoDAOImpl();
    }

    public boolean getListTeams(String titoloHackathon){
        try{
            teams.clear();
            teamDAO.getListTeams(teams, titoloHackathon);
            return true;
        } catch (SQLException e) {
            return  false;
        }
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public boolean getListDocumenti(int idTeam){
        try{
            documentiList.clear();
            documentoDAO.getListDocumentoByTeam(documentiList, idTeam);
            return true;
        }catch (SQLException e){
            return  false;
        }
    }

    public ArrayList<Documento> getDocumentoList(){
        return documentiList;
    }
}
