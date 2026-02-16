package it.unina.poo.controller;

import it.unina.poo.dao.HackathonDAO;
import it.unina.poo.dao.PartecipazioneDAO;
import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.implementazionipostgresdao.HackathonDAOImpl;
import it.unina.poo.implementazionipostgresdao.PartecipazioneDAOImpl;
import it.unina.poo.implementazionipostgresdao.UtenteDAOImpl;
import it.unina.poo.model.Hackathon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    private final HackathonDAO hackathonDAO;
    private final PartecipazioneDAO partecipazioneDAO;
    private final UtenteDAO utenteDAO;
    private final ArrayList<Hackathon> listHackathonDisp = new ArrayList<>();
    private final ArrayList<Hackathon> listHackathonInCorso = new ArrayList<>();
    private final ArrayList<Hackathon> listHackathonChiuso = new ArrayList<>();

    public HomeController() {
        hackathonDAO = new HackathonDAOImpl();
        partecipazioneDAO = new PartecipazioneDAOImpl();
        utenteDAO = new UtenteDAOImpl();
    }

    public boolean getListHackathon(String type, int idUtente){
        try {
            switch (type){
                case "Disponibili":
                    listHackathonDisp.clear();
                    hackathonDAO.getAllHackathon(listHackathonDisp, type, idUtente);
                    break;
                case "In Corso":
                    listHackathonInCorso.clear();
                    hackathonDAO.getAllHackathon(listHackathonInCorso, type, idUtente);
                    break;
                default:
                    listHackathonChiuso.clear();
                    hackathonDAO.getAllHackathon(listHackathonChiuso, type, idUtente);
            }
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    public List<Hackathon> getListHackathonDisp() {
        return listHackathonDisp;
    }

    public List<Hackathon> getListHackathonInCorso() {
        return listHackathonInCorso;
    }

    public List<Hackathon> getListHackathonChiuso() {
        return listHackathonChiuso;
    }

    public boolean partecipaHacakthon(int idUtente, String titoloHackathon){
        try{
            partecipazioneDAO.partecipaHackathon(idUtente, titoloHackathon);
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    public boolean modificaNomeUtente(String nome, int idUtente){
        try{
            utenteDAO.modificaNomeUtente(nome, idUtente);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean modificaEmailUtente(String email, int idUtente){
        try {
            utenteDAO.modificaEmailUtente(email, idUtente);
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    public boolean modificaPasswordUtente(String password, int idUtente){
        try {
            utenteDAO.modificaPasswordUtente(password, idUtente);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
