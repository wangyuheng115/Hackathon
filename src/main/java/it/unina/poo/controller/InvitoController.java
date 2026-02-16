package it.unina.poo.controller;

import it.unina.poo.dao.InvitoDAO;
import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.implementazionipostgresdao.InvitoDAOImpl;
import it.unina.poo.implementazionipostgresdao.UtenteDAOImpl;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Invito;
import it.unina.poo.model.Team;
import it.unina.poo.model.Utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitoController {
    private final InvitoDAO invitoDAO;
    private final UtenteDAO utenteDAO;
    private final ArrayList<Utente> listInvito = new ArrayList<>();

    public InvitoController(){
        invitoDAO = new InvitoDAOImpl();
        utenteDAO = new UtenteDAOImpl();
    }

    //Get List Invito
    public boolean getListInvito(String titoloHackathon, String tipo){
        try{
            listInvito.clear();
            utenteDAO.getListInvito(listInvito, titoloHackathon, tipo);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Utente> getListInvito(){
        return listInvito;
    }

    //Invio invito
    public boolean invioInvito(String motivazione, String tipoInvito, Utente utenteMittente, Utente utenteDestinatario, Hackathon hackathon, Team team){
        try{
            invitoDAO.creaInvito(new Invito(motivazione, tipoInvito, "in attesa", utenteMittente, utenteDestinatario, hackathon, team));
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
