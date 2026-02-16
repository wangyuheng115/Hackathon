package it.unina.poo.model;

/**
 * La classe Invito rappresenta un'invito del sistema.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Invito {
    private int idInvito;
    private String motivazione;
    private  String tipoInvito;
    private String stato;
    private Utente utenteMittente;
    private Utente utenteDestinatario;
    private Hackathon hackathon;
    private Team  team;

    /**
     * Costruttore della classe Invito
     *
     * @param motivazione la motivazinoe dell'invito
     * @param tipoInvito il tipo dell'invito (Team/Giudice)
     * @param stato lo stato delli'invito (in attesa/accettato/rifiutato)
     * @param utenteMittente {@link Utente} mittente
     * @param utenteDestinatario {@link Utente} destinatario
     * @param hackathon {@link Hackathon} da partecipare
     * @param team {@link Team} da partecipare
     * */
    public Invito(String motivazione, String tipoInvito, String stato, Utente utenteMittente, Utente utenteDestinatario, Hackathon hackathon, Team  team) {
        this.motivazione = motivazione;
        this.tipoInvito = tipoInvito;
        this.stato = stato;
        this.utenteMittente = utenteMittente;
        this.utenteDestinatario = utenteDestinatario;
        this.hackathon = hackathon;
        this.team = team;
    }

    /**
     * Costruttore della classe Invito senza team
     *
     * @param motivazione la motivazinoe dell'invito
     * @param tipoInvito il tipo dell'invito (Team/Giudice)
     * @param stato lo stato delli'invito (in attesa/accettato/rifiutato)
     * @param utenteMittente {@link Utente} mittente
     * @param utenteDestinatario {@link Utente} destinatario
     * @param hackathon {@link Hackathon} da partecipare
     * */
    public Invito(String motivazione, String tipoInvito, String stato, Utente utenteMittente, Utente utenteDestinatario, Hackathon hackathon) {
        this.motivazione = motivazione;
        this.tipoInvito = tipoInvito;
        this.stato = stato;
        this.utenteMittente = utenteMittente;
        this.utenteDestinatario = utenteDestinatario;
        this.hackathon = hackathon;
    }

    /**
     * Restituisce id invito
     *
     * @return id invito
     * */
    public int getIdInvito() {
        return idInvito;
    }
    /**
     * Imposta id invito
     *
     * @param idInvito id invito
     * */
    public void setIdInvito(int idInvito) {
        this.idInvito = idInvito;
    }

    /**
     * Restituisce la motivazione dell'invito
     *
     * @return la stringa motivazione invito
     * */
    public String getMotivazione() {
        return motivazione;
    }
    /**
     * Imposta la motivazione dell'invito
     *
     * @param motivazione la motivazione dell'invito
     * */
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    /**
     * Restituisce la tipologia dell'invito
     *
     * @return la stringa la tipologia dell'invito
     * */
    public String getTipoInvito() {
        return tipoInvito;
    }
    /**
     * Imposta la tipologia dell'invito
     *
     * @param tipoInvito la tipologia dell'invito
     * */
    public void setTipoInvito(String tipoInvito) {
        this.tipoInvito = tipoInvito;
    }

    /**
     * Restituisce lo stato dell'invito
     *
     * @return la stringa lo stato dell'invito
     * */
    public String getStato() {
        return stato;
    }
    /**
     * Imposta lo stato dell'invito
     *
     * @param stato lo stato dell'invito
     * */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Restituisce il mittente dell'invito
     *
     * @return {@link Utente} mittente
     * */
    public Utente getUtenteMittente() {
        return utenteMittente;
    }
    /**
     * Imposta il mittente dell'invito
     *
     * @param utenteMittente  {@link Utente} mittente
     * */
    public void setUtenteMittente(Utente utenteMittente) {
        this.utenteMittente = utenteMittente;
    }

    /**
     * Restituisce il destinatario dell'invito
     *
     * @return {@link Utente} destinatario
     * */
    public Utente getUtenteDestinatario() {
        return utenteDestinatario;
    }
    /**
     * Imposta il destinatario dell'invito
     *
     * @param utenteDestinatario  {@link Utente} destinatario
     * */
    public void setUtenteDestinatario(Utente utenteDestinatario) {
        this.utenteDestinatario = utenteDestinatario;
    }

    /**
     * Restituisce hackathon dell'invito
     *
     * @return {@link Hackathon} hackathon dell'invito
     * */
    public Hackathon getHackathon() {
        return hackathon;
    }
    /**
     * Imposta hackathon dell'invito
     *
     * @param hackathon  {@link Hackathon} hackathon dell'invito
     * */
    public void setHackathon(Hackathon hackathon) {
        this.hackathon = hackathon;
    }

    /**
     * Restituisce team dell'invito
     *
     * @return {@link Team} team dell'invito
     * */
    public Team getTeam() {
        return team;
    }
    /**
     * Imposta team dell'invito
     *
     * @param team  {@link Team} team dell'invito
     * */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * Restituisce tutte le informazioni di un invito in forma HTML.
     *
     * @return HTML che visualizza tutte le informazioni di un invito
     * */
    @Override
    public String toString() {
        return "<html>" +
                "Stato: " +stato+"<br>" +
                "Tipo Invito: " +tipoInvito+"<br>" +
                "Hackathon: " +hackathon.getTitolo() +"<br>" +
                "Team: " +(team != null? team.getNome() : "") +"<br>" +
                "Mittente: " +utenteMittente.getNome() +"<br>" +
                "Destinatario: " + utenteDestinatario.getNome() +"<br>" +
                "Motivazione: " +motivazione +"<br><hr>" +
                "</html>";
    }
}
