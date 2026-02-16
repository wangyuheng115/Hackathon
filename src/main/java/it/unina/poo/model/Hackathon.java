package it.unina.poo.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * La classe Hackathon rappresenta un'hackathon del sistema.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Hackathon {
    private String titolo;
    private LocalDate inizio;
    private LocalDate fine;
    private int numeroMassimoDiIscrizioni;
    private int numeroMassimoPersonePerTeam;
    private LocalDate inizioIscrizioni;
    private LocalDate fineIscrizioni;
    private Sede sede;

    /**
     * Costruttore della classe Hackathon
     *
     * @param titolo titolo hackathon
     * @param inizio data inizio hackathon
     * @param fine data fine hackathon
     * @param numeroMassimoDiIscrizioni numero massimo di iscrizioni
     * @param numeroMassimoPersonePerTeam numero massimo delle persone in un team
     * @param inizioIscrizioni data inizia iscrizione
     * @param fineIscrizioni data fine iscrizione
     * @param sede {@link Sede} dove organizza l'hackathon
     * */
    public Hackathon(String titolo, LocalDate inizio, LocalDate fine, int numeroMassimoDiIscrizioni, int numeroMassimoPersonePerTeam, LocalDate inizioIscrizioni, LocalDate fineIscrizioni, Sede sede) {
        this.titolo = titolo;
        this.inizio = inizio;
        this.fine = fine;
        this.numeroMassimoDiIscrizioni = numeroMassimoDiIscrizioni;
        this.numeroMassimoPersonePerTeam = numeroMassimoPersonePerTeam;
        this.inizioIscrizioni = inizioIscrizioni;
        this.fineIscrizioni = fineIscrizioni;
        this.sede = sede;
    }

    /**
     * Costruttore della classe Hackathon senza le date fine(calcola automatico quando crea un nuovo hackathon)
     *
     * @param titolo titolo hackathon
     * @param inizio data inizio hackathon
     * @param numeroMassimoDiIscrizioni numero massimo di iscrizioni
     * @param numeroMassimoPersonePerTeam numero massimo delle persone in un team
     * @param inizioIscrizioni data inizia iscrizione
     * @param sede {@link Sede} dove organizza l'hackathon
     * */
    public Hackathon(String titolo, LocalDate inizio, int numeroMassimoDiIscrizioni, int numeroMassimoPersonePerTeam, LocalDate inizioIscrizioni, Sede sede) {
        this.titolo = titolo;
        this.inizio = inizio;
        this.numeroMassimoDiIscrizioni = numeroMassimoDiIscrizioni;
        this.numeroMassimoPersonePerTeam = numeroMassimoPersonePerTeam;
        this.inizioIscrizioni = inizioIscrizioni;
        this.sede = sede;
    }

    /**
     * Restituisce titolo hackathon
     *
     * @return titolo hackathon
     * */
    public String getTitolo() {
        return titolo;
    }
    /**
     * Imposta titolo hackathon
     *
     * @param  titolo titolo hackathon
     * */
    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    /**
     * Restituisce data inizio hackathon
     *
     * @return data inizio hackathon
     * */
    public LocalDate getInizio() {
        return inizio;
    }
    /**
     * Imposta data inizio hackathon
     *
     * @param  inizio data inizio hackathon
     * */
    public void setInizio(LocalDate inizio) {
        this.inizio = inizio;
    }

    /**
     * Restituisce data fine hackathon
     *
     * @return data fine hackathon
     * */
    public LocalDate getFine() {
        return fine;
    }
    /**
     * Imposta data fine hackathon
     *
     * @param fine data fine hackathon
     * */
    public void setFine(LocalDate fine) {
        this.fine = fine;
    }

    /**
     * Restituisce numero massimo di iscrizioni
     *
     * @return numero massimo di iscrizioni
     * */
    public int getNumeroMassimoDiIscrizioni() {
        return numeroMassimoDiIscrizioni;
    }
    /**
     * Imposta numero massimo di iscrizioni
     *
     * @param numeroMassimoDiIscrizioni numero massimo di iscrizioni
     * */
    public void setNumeroMassimoDiIscrizioni(int numeroMassimoDiIscrizioni) {
        this.numeroMassimoDiIscrizioni = numeroMassimoDiIscrizioni;
    }

    /**
     * Restituisce numero massimo delle persone in un team
     *
     * @return numero massimo delle persone in un team
     * */
    public int getNumeroMassimoPersonePerTeam() {
        return numeroMassimoPersonePerTeam;
    }
    /**
     * Imposta numero massimo delle persone in un team
     *
     * @param numeroMassimoPersonePerTeam numero massimo delle persone in un team
     * */
    public void setNumeroMassimoPersonePerTeam(int numeroMassimoPersonePerTeam) {
        this.numeroMassimoPersonePerTeam = numeroMassimoPersonePerTeam;
    }

    /**
     * Restituisce data inizia iscrizione
     *
     * @return data inizia iscrizione
     * */
    public LocalDate getInizioIscrizioni() {
        return inizioIscrizioni;
    }
    /**
     * Imposta data inizia iscrizione
     *
     * @param inizioIscrizioni data inizia iscrizione
     * */
    public void setInizioIscrizioni(LocalDate inizioIscrizioni) {
        this.inizioIscrizioni = inizioIscrizioni;
    }

    /**
     * Restituisce data fine iscrizione
     *
     * @return data fine iscrizione
     * */
    public LocalDate getFineIscrizioni() {
        return fineIscrizioni;
    }
    /**
     * Imposta data fine iscrizione
     *
     * @param fineIscrizioni data fine iscrizione
     * */
    public void setFineIscrizioni(LocalDate fineIscrizioni) {
        this.fineIscrizioni = fineIscrizioni;
    }

    /**
     * Restituisce {@link Sede} dove organizza l'hackathon
     *
     * @return {@link Sede} dove organizza l'hackathon
     * */
    public Sede getSede() {
        return sede;
    }
    /**
     * Imposta {@link Sede} dove organizza l'hackathon
     *
     * @param sede {@link Sede} dove organizza l'hackathon
     * */
    public void setSede(Sede sede) {
        this.sede = sede;
    }

    /**
     * Restituisce una rappresentazione testuale del'hackathon.
     *
     * @return stringa rappresenta hackathon
     * */
    @Override
    public String toString() {
        return "<html>" +
                "Titolo: " + titolo + "<br>" +
                "Inizio: " + formatData(inizio) + ", Fine: " + formatData(fine) + "<br>" +
                "Inizio Iscrizione: " + formatData(inizioIscrizioni) + ", Fine Iscrizione: " + formatData(fineIscrizioni) + "<br>" +
                "Numero massimo di iscrizione: " + numeroMassimoDiIscrizioni + "<br>" +
                "Numero massimo persone per team: " + numeroMassimoPersonePerTeam + "<br>"+
                "Sede: " + getSede().toString() + "<hr>" +
                "</html>";
    }

    private String formatData(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return  date.format(formatter);
    }

}
