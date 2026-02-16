package it.unina.poo.model;

/**
 * La classe ClassificaVotoTeam rappresenta la classifica del team dopo l'hackathon è chiuso.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class ClassificaVotoTeam {
    private int idTeam;
    private String nomeTeam;
    private float punteggio;

    /**
     * Costruttore della classe ClassificaVotoTeam
     *
     * @param idTeam id team
     * @param nomeTeam nome team
     * @param punteggio valutazione del team da 0 a 10
     * */
    public  ClassificaVotoTeam(int idTeam, String nomeTeam, float punteggio) {
        this.idTeam = idTeam;
        this.nomeTeam = nomeTeam;
        this.punteggio = punteggio;
    }

    /**
     * Restituisce id team
     *
     * @return id team
     * */
    public int getIdTeam() {
        return idTeam;
    }
    /**
     * Imposta id team
     *
     * @param idTeam id team
     * */
    public void setIdTeam(int idTeam) {
        this.idTeam = idTeam;
    }

    /**
     * Restituisce nome team
     *
     * @return nome team
     * */
    public String getNomeTeam() {
        return nomeTeam;
    }
    /**
     * Imposta nome team
     *
     * @param nomeTeam nome team
     * */
    public void setNomeTeam(String nomeTeam) {
        this.nomeTeam = nomeTeam;
    }

    /**
     * Restituisce la valutazione del team
     *
     * @return punteggio team
     * */
    public float getPunteggio() {
        return punteggio;
    }
    /**
     * Imposta punteggio team
     *
     * @param punteggio punteggio da valutare il team
     * */
    public void setPunteggio(float punteggio) {
        this.punteggio = punteggio;
    }

    /**
     * Restituisce una rappresentazione testuale del team in classifica.
     *
     * @return stringa che rappresenta team in classifica
     * */
    @Override
    public String toString() {
        return "Id Team: " + getIdTeam() + " | Nome: " + getNomeTeam() + " | Punteggio: " + getPunteggio();
    }
}
