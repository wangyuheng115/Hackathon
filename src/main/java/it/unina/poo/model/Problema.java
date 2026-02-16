package it.unina.poo.model;

/**
 * La classe Problema rappresenta un problema pubblicato in hackathon.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Problema {
    private String descrizioneProblema;
    private Utente utente;
    private Hackathon hackathon;

    /**
     * Costruttore della classe Problema
     *
     * @param descrizioneProblema il contenuto del problema
     * @param utente {@link Utente} che ha pubblicato il problema
     * @param hackathon {@link Hackathon} che contiene il problema
     * */
    public Problema(String descrizioneProblema, Utente utente, Hackathon hackathon) {
        this.descrizioneProblema = descrizioneProblema;
        this.utente = utente;
        this.hackathon = hackathon;
    }

    /**
     * Restituisce la descrizione del problema
     *
     * @return la descrizione del problema
     * */
    public String getDescrizioneProblema() {
        return descrizioneProblema;
    }
    /**
     * Imposta la descrizione del problema
     *
     * @param descrizioneProblema la descrizione del problema
     * */
    public void setDescrizioneProblema(String descrizioneProblema) {
        this.descrizioneProblema = descrizioneProblema;
    }

    /**
     * Restituisce {@link Utente} che ha pubblicato il problema
     *
     * @return {@link Utente} che ha pubblicato il problema
     * */
    public Utente getUtente() {
        return utente;
    }
    /**
     * Imposta {@link Utente} che ha pubblicato il problema
     *
     * @param utente {@link Utente} che ha pubblicato il problema
     * */
    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    /**
     * Restituisce {@link Hackathon} che contiene il problema
     *
     * @return {@link Hackathon} che contiene il problema
     * */
    public Hackathon getHackathon() {
        return hackathon;
    }
    /**
     * Imposta {@link Hackathon} che contiene il problema
     *
     * @param hackathon {@link Hackathon} che contiene il problema
     * */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce una rappresentazione testuale del problema in forma HTML.
     *
     * @return stringa che rappresenta problema
     * */
    @Override
    public String toString() {
        return "<html>" +
                "<p> Descrizione: "+descrizioneProblema+"</p><br/>" +
                "<p> Pubblicato da: " +utente.getNome()+"</p><br/>" +
                "</html>";
    }
}
