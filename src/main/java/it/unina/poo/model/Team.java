package it.unina.poo.model;

/**
 * La classe Team rappresenta un team di hackathon.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Team {
    private int idTeam;
    private String nome;
    private Hackathon hackathon;

    /**
     * Costruttore della classe Team
     *
     * @param nome nome team
     * @param hackathon {@link Hackathon} che contiene il team
     * */
    public Team(String nome, Hackathon hackathon) {
        this.nome = nome;
        this.hackathon = hackathon;
    }

    /**
     * Costruttore della classe Team
     *
     * @param idTeam id team
     * @param nome nome team
     * @param hackathon {@link Hackathon} che contiene il team
     * */
    public Team(int idTeam,String nome, Hackathon hackathon) {
        this.idTeam = idTeam;
        this.nome = nome;
        this.hackathon = hackathon;
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
    public String getNome() {
        return nome;
    }
    /**
     * Imposta nome team
     *
     * @param nome nome team
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce {@link Hackathon} del team
     *
     * @return {@link Hackathon} del team
     * */
    public Hackathon getHackathon() {
        return hackathon;
    }
    /**
     * Imposta {@link Hackathon} del team
     *
     * @param hackathon {@link Hackathon} del team
     * */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce nome del team
     *
     * @return nome del team
     * */
    @Override
    public String toString() {
        return "Team: " + getNome();
    }
}
