package it.unina.poo.controller;

import it.unina.poo.dao.UtenteDAO;
import it.unina.poo.implementazionipostgresdao.UtenteDAOImpl;
import it.unina.poo.model.Utente;

import java.sql.SQLException;

public class SignInController {
    private final UtenteDAO utenteDAO;

    public SignInController(){
        utenteDAO = new UtenteDAOImpl();
    }

    public boolean checkNome(String nome) {
        return nome != null  && !nome.isEmpty();
    }

    public boolean checkEmail(String email){
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean checkPassword(String password){
        if (password == null || password.length() < 8) {
            return false;
        }

        // Password deve avere lettera maiuscola minuscola e un numero
        boolean hasUpperCase = false;
        boolean hasLowerCase = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowerCase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }

            if (hasUpperCase && hasLowerCase && hasDigit) {
                return true;
            }
        }

        return false;
    }

    public boolean signInUtente(String nome, String email, String password){
        Utente utente = new Utente(nome, email, password);

        try {
            utenteDAO.creaUtente(utente);
            return true;
        } catch (SQLException e){
            return false;
        }

    }

}
