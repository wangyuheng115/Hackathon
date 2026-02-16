package it.unina.poo.model;

/**
 * La classe TeamPartecipante rappresenta un partecipante del team.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class TeamPartecipante {
    private Team team;
    private Utente utente;

    /**
     * Costruttore della classe TeamPartecipante
     *
     * @param team {@link Team} del partecipante
     * @param utente {@link Utente} partecipante
     * */
    public TeamPartecipante(Team team, Utente utente) {
        this.team = team;
        this.utente = utente;
    }

    /**
     * Restituisce {@link Team} del partecipante
     *
     * @return {@link Team} del partecipante
     * */
    public Team getTeam() {
        return team;
    }
    /**
     * Imposta {@link Team} del partecipante
     *
     * @param team {@link Team} del partecipante
     * */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Restituisce {@link Utente} partecipante
     *
     * @return {@link Utente} partecipante
     * */
    public Utente getUtente() {
        return utente;
    }
    /**
     * Imposta {@link Utente} partecipante
     *
     * @param utente {@link Utente} partecipante
     * */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Restituisce una rappresentazione testuale dell'utente.
     *
     * @return stringa che rappresenta utente
     * */
    @Override
    public String toString() {
        return utente.toString();
    }
}
