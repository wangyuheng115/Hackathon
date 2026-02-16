package it.unina.poo.controller;

import it.unina.poo.dao.InvitoDAO;
import it.unina.poo.implementazionipostgresdao.InvitoDAOImpl;
import it.unina.poo.model.Invito;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListInvitiController {
    private final InvitoDAO invitoDAO;
    private ArrayList<Invito> listInviti = new ArrayList<>();

    public ListInvitiController() {
        invitoDAO = new InvitoDAOImpl();
    }

    public boolean getListInviti(int idUtente){
        try{
            listInviti.clear();
            invitoDAO.getListInvito(idUtente, listInviti);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Invito> getListInviti() {
        return listInviti;
    }

    public boolean rispostaInvito(int idInvito, String risposta){
        try{
            invitoDAO.rispondeInvito(idInvito, risposta);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
}
