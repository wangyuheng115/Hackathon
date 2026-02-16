package it.unina.poo.model;

/**
 * La classe Partecipazione rappresenta una partecipazione all'hackathon.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Partecipazione {
    private String ruolo;
    private Hackathon hackathon;
    private Utente utente;

    /**
     * Costruttore della classe Partecipazione
     *
     * @param ruolo ruolo nel hackathon
     * @param hackathon {@link Hackathon} da partecipare
     * @param utente {@link Utente} intestatario
     * */
    public  Partecipazione(String ruolo,Hackathon hackathon,Utente utente) {
        this.ruolo = ruolo;
        this.hackathon = hackathon;
        this.utente = utente;
    }

    /**
     * Restituisce ruolo di utente in hackathon
     *
     * @return ruolo di utente in hackathon
     * */
    public String getRuolo() {
        return ruolo;
    }
    /**
     * Imposta ruolo di utente in hackathon
     *
     * @param ruolo  ruolo di utente in hackathon(partecipante/giudice/organizzatore)
     * */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce {@link Hackathon} da partecipare
     *
     * @return {@link Hackathon} da partecipare
     * */
    public Hackathon getHackathon() {
        return hackathon;
    }
    /**
     * Imposta {@link Hackathon} da partecipare
     *
     * @param hackathon  {@link Hackathon} da partecipare
     * */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce {@link Utente} intestatario di invito
     *
     * @return {@link Utente} intestatario di invito
     * */
    public Utente getUtente() {
        return utente;
    }
    /**
     * Imposta {@link Utente} intestatario di invito
     *
     * @param utente {@link Utente} intestatario di invito
     * */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Restituisce una rappresentazione testuale della partecipazione.
     *
     * @return stringa che rappresenta la partecipazione
     * */
    @Override
    public String toString() {
        return "Id: " + utente.getIdUtente() + " | Nome: " + utente.getNome()+" | Ruolo: " + ruolo;
    }
}
