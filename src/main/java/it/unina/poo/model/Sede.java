package it.unina.poo.model;

/**
 * La classe Sede rappresenta una sede dove organizza hackathon.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Sede {
    private int idSede;
    private String via;
    private String numeroCivico;
    private String citta;
    private String cap;
    private String provincia;

    /**
     * Costruttore della classe Sede
     *
     * @param idSede id sede
     * @param via via
     * @param numeroCivico numero civico
     * @param citta città
     * @param cap cap
     * @param provincia provincia
     * */
    public Sede(int idSede,String via, String numeroCivico, String citta, String cap, String provincia) {
        this.idSede = idSede;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
    }

    /**
     * Costruttore della classe Sede senza id
     *
     * @param via via
     * @param numeroCivico numero civico
     * @param citta città
     * @param cap cap
     * @param provincia provincia
     * */
    public Sede(String via, String numeroCivico,  String citta, String cap, String provincia) {
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
    }

    /**
     * Restituisce id sede
     *
     * @return id sede
     * */
    public int getIdSede() {
        return idSede;
    }
    /**
     * Imposta id sede
     *
     * @param idSede  id sede
     * */
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    /**
     * Imposta via sede
     *
     * @param via via sede
     * */
    public void setVia(String via) {
        this.via = via;
    }
    /**
     * Restituisce via sede
     *
     * @return via sede
     * */
    public String getVia() {
        return via;
    }

    /**
     * Imposta numero civico sede
     *
     * @param numeroCivico numero civico sede
     * */
    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }
    /**
     * Restituisce numero civico sede
     *
     * @return numero civico sede
     * */
    public String getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * Imposta città sede
     *
     * @param citta città sede
     * */
    public void setCitta(String citta) {
        this.citta = citta;
    }
    /**
     * Restituisce città sede
     *
     * @return città sede
     * */
    public String getCitta() {
        return citta;
    }

    /**
     * Imposta cap sede
     *
     * @param cap cap sede
     * */
    public void setCap(String cap) {
        this.cap = cap;
    }
    /**
     * Restituisce cap sede
     *
     * @return cap sede
     * */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta provincia sede
     *
     * @param provincia provincia sede
     * */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    /**
     * Restituisce provincia sede
     *
     * @return provincia sede
     * */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Restituisce una rappresentazione testuale della sede.
     *
     * @return stringa che rappresenta la sede
     * */
    @Override
    public String toString() {
        return "Sede{" +
                "idSede=" + idSede +
                ", via='" + via + '\'' +
                ", numeroCivico='" + numeroCivico + '\'' +
                ", citta='" + citta + '\'' +
                ", cap='" + cap + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
