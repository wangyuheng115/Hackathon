package it.unina.poo.dao;

import it.unina.poo.model.Sede;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implementare su {@link Sede}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface SedeDAO {
    /**
     * Crea una nuova {@link Sede}
     *
     * @param sede oggetto della classe {@link Sede}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
     void aggiungeSede(Sede sede) throws SQLException;
     /**
      * Ottenere la lista di {@link Sede}
      *
      * @param listSede lista di {@link Sede} da riempire
      * @throws SQLException quando la operazione su Postgres fallisce
      * */
     void getAllSede(List<Sede> listSede) throws SQLException;

     /**
      * Ottenere un sede tramite id sede
      *
      * @param idSede id sede
      * @throws SQLException quando la operazione su Postgres fallisce
      * */
     Sede getSedeById(int idSede) throws SQLException;
}
