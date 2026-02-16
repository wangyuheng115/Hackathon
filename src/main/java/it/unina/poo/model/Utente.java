package it.unina.poo.model;

/**
 * La classe Utente rappresenta un utente del sistema.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public class Utente {
    private int idUtente;
    private String nome;
    private String email;
    private String password;

    /**
     * Costruttore della classe Utente
     *
     * @param idUtente id utente
     * @param nome nome utente
     * @param email email utente
     * @param password password utente
     * */
    public Utente(int idUtente,String nome, String email, String password) {
        this.idUtente = idUtente;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     * Costruttore della classe Utente senza id
     *
     * @param nome nome utente
     * @param email email utente
     * @param password password utente
     * */
    public Utente(String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    /**
     * Restituisce id utente
     *
     * @return id utente
     * */
    public int getIdUtente() {
        return idUtente;
    }
    /**
     * Imposta id utente
     *
     * @param idUtente id utente
     * */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Restituisce nome utente
     *
     * @return nome utente
     * */
    public String getNome() {
        return nome;
    }
    /**
     * Imposta nome utente
     *
     * @param nome nome utente
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce email utente
     *
     * @return email utente
     * */
    public String getEmail() {
        return email;
    }
    /**
     * Imposta email utente
     *
     * @param email email utente
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce password utente
     *
     * @return password utente
     * */
    public String getPassword() {
        return password;
    }
    /**
     * Imposta password utente
     *
     * @param password password utente
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce una rappresentazione testuale dell'utente.
     *
     * @return stringa che rappresenta utente
     * */
    @Override
    public String toString() {
        return "Id: "+this.idUtente+" | Nome: "+getNome()+" | Email: "+getEmail();
    }
}
