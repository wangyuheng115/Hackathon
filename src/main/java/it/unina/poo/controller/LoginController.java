package it.unina.poo.controller;

import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.implementazionipostgresdao.UtenteDAOImpl;
import it.unina.poo.model.Utente;

import java.sql.SQLException;

public class LoginController {
    private final UtenteDAO utenteDAO;

    public LoginController(){
        utenteDAO = new UtenteDAOImpl();
    }

    public boolean checkEmail(String email){
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean checkPassword(String password){
        return password != null && !password.trim().isEmpty();
    }

    public Utente login(String email, String password) throws SQLException {
        return utenteDAO.loginUtente(email,password);
    }
}
