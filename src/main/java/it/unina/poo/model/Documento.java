package it.unina.poo.model;

import java.time.LocalDate;

/**
 * La classe Documento rappresenta un documento che ha caricato da un {@link Team}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Documento {
    private int idDocumento;
    private LocalDate data;
    private String documentoContent;
    private Team team;

    /**
     * Costruttore della classe Documento
     *
     * @param documentoContent contenuto del documento
     * @param team {@link Team} intestatario
     * */
    public Documento(String documentoContent, Team team) {
        this.documentoContent = documentoContent;
        this.team = team;
    }

    /**
     * Costruttore della classe Documento
     *
     * @param idDocumento id documento
     * @param data data caricato il documento
     * @param documentoContent contenuto del documento
     * @param team {@link Team} intestatario
     * */
    public Documento(int idDocumento,LocalDate data, String documentoContent, Team team) {
        this.idDocumento = idDocumento;
        this.data = data;
        this.documentoContent = documentoContent;
        this.team = team;
    }

    /**
     * Restituisce id documento
     *
     * @return id documento
     * */
    public int getIdDocumento() {
        return idDocumento;
    }
    /**
     * Imposta id documento
     *
     * @param idDocumento id documento
     * */
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    /**
     * Restituisce data documento
     *
     * @return data documento
     * */
    public LocalDate getData() {
        return data;
    }
    /**
     * Imposta data documento
     *
     * @param data data documento
     * */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Restituisce contenuto del documento
     *
     * @return contenuto del documento
     * */
    public String getDocumento() {
        return documentoContent;
    }
    /**
     * Imposta contenuto del documento
     *
     * @param documentoContent contenuto del documento
     * */
    public void setDocumento(String documentoContent) {
        this.documentoContent = documentoContent;
    }

    /**
     * Restituisce team intestatario
     *
     * @return team intestatario
     * */
    public Team getTeam() {
        return team;
    }
    /**
     * Imposta {@link Team} intestatario
     *
     * @param team {@link Team} intestatario
     * */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Restituisce una rappresentazione testuale del documento e team intestatario.
     *
     * @return stringa che rappresenta documento e team intestatario
     * */
    @Override
    public String toString() {
        return "<html>" +
                "Contenuto: " + documentoContent + "<br/>" +
                "By team:"+ getTeam().getNome() + "<br/> <hr>" +
                "</html>";
    }
}
