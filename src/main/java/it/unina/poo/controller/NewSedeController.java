package it.unina.poo.controller;

import it.unina.poo.dao.SedeDAO;
import it.unina.poo.implementazionipostgresdao.SedeDAOImpl;
import it.unina.poo.model.Sede;

import java.sql.SQLException;

public class NewSedeController {
    private final SedeDAO sedeDAO;

    public NewSedeController() {
        sedeDAO = new SedeDAOImpl();
    }

    //Aggiunge nuovo sede
    public boolean createSede(String via, String numeroCivico, String citta, String cap, String provincia) {
        try {
            Sede sede = new Sede(via,  numeroCivico, citta, cap, provincia);
            sedeDAO.aggiungeSede(sede);
            return true;
        }catch (SQLException ex) {
            return false;
        }
    }
}
