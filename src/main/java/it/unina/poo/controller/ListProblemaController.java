package it.unina.poo.controller;

import it.unina.poo.dao.ProblemaDAO;
import it.unina.poo.implementazionipostgresdao.ProblemaDAOImpl;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Problema;
import it.unina.poo.model.Utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListProblemaController {
    private final ProblemaDAO problemaDAO;
    private ArrayList<Problema> listaProblema;

    public ListProblemaController(){
        problemaDAO = new ProblemaDAOImpl();
        listaProblema = new ArrayList<>();
    }

    public boolean getProblemaLista(String titoloHackathon){
        try{
            listaProblema.clear();
            problemaDAO.getListProblema(titoloHackathon, listaProblema);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Problema> getListaProblema(){
        return listaProblema;
    }

    public boolean creaProblema(String descrizione, Utente utente, Hackathon hackathon){

        try {
            problemaDAO.creaProblema(new Problema(descrizione, utente, hackathon));
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean modificaProblema(String descrizione, Utente utente, Hackathon hackathon){
        try{
            problemaDAO.modificaDescrizioneProblema(new Problema(descrizione, utente, hackathon));
            return true;
        }catch (SQLException e){
            return false;
        }
    }

    public boolean eliminaProblema(Utente utente, Hackathon hackathon){
        try {
            problemaDAO.deleteProblema(hackathon.getTitolo(), utente.getIdUtente());
            return true;
        }catch (SQLException e){
            return false;
        }
    }
}
