package it.unina.poo.model;

/**
 * La classe Voto rappresenta una valutazione da un utente giudice per un team.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Voto {
    private int idVoto;
    private float valore;
    private Team team;
    private Utente giudice;

    /**
     * Costruttore della classe Voto
     *
     * @param valore il valore della valutazione
     * @param team oggetto {@link Team} che contiene il voto
     * @param giudice oggetto {@link Utente} giudice che ha segnato il voto
     * */
    public Voto(float valore, Team team, Utente giudice) {
        this.valore = valore;
        this.team = team;
        this.giudice = giudice;
    }

    /**
     * Costruttore della classe Voto
     *
     * @param idVoto id della valutazione
     * @param valore il valore della valutazione
     * @param team oggetto {@link Team} che contiene il voto
     * @param giudice oggetto {@link Utente} giudice che ha segnato il voto
     * */
    public Voto(int idVoto,float valore, Team team, Utente giudice) {
        this.idVoto = idVoto;
        this.valore = valore;
        this.team = team;
        this.giudice = giudice;
    }

    /**
     * Restituisce id della valutazione
     *
     * @return id della valutazione
     * */
    public int getIdVoto() {
        return idVoto;
    }
    /**
     * Imposta id della valutazione
     *
     * @param idVoto id della valutazione
     * */
    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    /**
     * Restituisce il valore della valutazione
     *
     * @return il valore della valutazione
     * */
    public float getValore() {
        return valore;
    }
    /**
     * Imposta il valore della valutazione
     *
     * @param valore il valore della valutazione
     * */
    public void setValore(int valore) {
        this.valore = valore;
    }

    /**
     * Restituisce il {@link Team} della valutazione
     *
     * @return oggetto {@link Team} della valutazione
     * */
    public Team getTeam() {
        return team;
    }
    /**
     * Imposta il {@link Team} della valutazione
     *
     * @param team oggetto {@link Team} della valutazione
     * */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Restituisce {@link Utente} giudice che ha segnato la valutazione
     *
     * @return {@link Utente} giudice che ha segnato la valutazione
     * */
    public Utente getGiudice() {
        return giudice;
    }
    /**
     * Imposta {@link Utente} giudice che ha segnato la valutazione
     *
     * @param giudice {@link Utente} giudice che ha segnato la valutazione
     * */
    public void setGiudice(Utente giudice) {
        this.giudice = giudice;
    }

    /**
     * Restituisce una rappresentazione testuale della valutazione.
     *
     * @return stringa che rappresenta la valutazione
     * */
    @Override
    public String toString() {
        return "Valore: " + valore + " Giudice: " + giudice;
    }
}
