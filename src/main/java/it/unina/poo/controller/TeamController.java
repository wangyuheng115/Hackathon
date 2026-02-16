package it.unina.poo.controller;

import it.unina.poo.dao.DocumentoDAO;
import it.unina.poo.dao.TeamPartecipanteDAO;
import it.unina.poo.implementazionipostgresdao.DocumentoDAOImpl;
import it.unina.poo.implementazionipostgresdao.TeamPartecipanteDAOImpl;
import it.unina.poo.model.Documento;
import it.unina.poo.model.Team;
import it.unina.poo.model.TeamPartecipante;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeamController {
    private final TeamPartecipanteDAO teamPartecipanteDAO;
    private final DocumentoDAO documentoDAO;
    private final ArrayList<TeamPartecipante> listaTeamPartecipante = new ArrayList<>();
    private ArrayList<Documento> listaDocumento = new ArrayList<>();

    public TeamController() {
        teamPartecipanteDAO = new TeamPartecipanteDAOImpl();
        documentoDAO = new DocumentoDAOImpl();
    }

    public boolean getListMembriTeam(int idTeam){
        try{
            listaTeamPartecipante.clear();
            teamPartecipanteDAO.getListTeamPartecipante(idTeam, listaTeamPartecipante);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<TeamPartecipante> getListTeamPartecipante(){
        return listaTeamPartecipante;
    }

    public boolean getListDocumentiTeam(int idTeam){
        try{
            listaDocumento.clear();
            documentoDAO.getListDocumentoByTeam(listaDocumento, idTeam);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public List<Documento> getListDocumento(){
        return listaDocumento;
    }

    public boolean creaDocumento(Team team, String contenuto){
        try {
            documentoDAO.creaDocumento(new Documento(contenuto, team));
            return true;
        } catch (SQLException e) {
            return  false;
        }
    }

    public boolean modificaDocumento(String contenuto, int idDocumento){
        try{
            documentoDAO.modificaDocumentoContent(contenuto, idDocumento);
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean eliminaDocumento(int idDocumento){
        try {
            documentoDAO.deleteDocumento(idDocumento);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
