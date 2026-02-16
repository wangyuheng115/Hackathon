package it.unina.poo.controller;

import it.unina.poo.dao.HackathonDAO;
import it.unina.poo.dao.SedeDAO;
import it.unina.poo.implementazionipostgresdao.HackathonDAOImpl;
import it.unina.poo.implementazionipostgresdao.SedeDAOImpl;
import it.unina.poo.model.Hackathon;
import it.unina.poo.model.Sede;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewHackathonController {
    private final SedeDAO sedeDAO;
    private final HackathonDAO hackathonDAO;
    private final ArrayList<Sede> sedeList = new ArrayList<>();

    public NewHackathonController(){
        sedeDAO = new SedeDAOImpl();
        hackathonDAO = new HackathonDAOImpl();
    }

    //Get list sede
    public boolean getListSede(){
        try{
            sedeList.clear();
            sedeDAO.getAllSede(sedeList);
            return true;
        }catch(SQLException e){
            return false;
        }
    }

    //Get ArrayList sede
    public List<Sede> getArrayListSede(){
        return sedeList;
    }

    //Crea nuovo hackathon
    public boolean creaHackathon(String titolo, LocalDate inizio, int numeroMassimoDiIscrizioni, int numeroMassimoPersonePerTeam,
                                 LocalDate inizioIscrizioni, Sede sede, int idUtene){

        Hackathon hackathon = new Hackathon(titolo, inizio, numeroMassimoDiIscrizioni, numeroMassimoPersonePerTeam, inizioIscrizioni, sede);

        try{
            hackathonDAO.creaHackathon(hackathon, idUtene);
            return true;
        }catch(SQLException e){
            return false;
        }
    }
}
