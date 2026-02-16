package it.unina.poo.dao;

import it.unina.poo.model.Documento;

import java.sql.SQLException;
import java.util.List;

/**
 * La interface che definisce le operazioni da implenmentare per {@link Documento}.
 *
 * @author Yuheng Wang
 * @version 1.0
 * */
public interface DocumentoDAO {
    /**
     * Crea un documento
     *
     * @param documento oggetto del {@link Documento}
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void creaDocumento(Documento documento) throws SQLException;
    /**
     * Modifica il contenuto del documento
     *
     * @param documentoContent il nuovo cotenuto da aggiornare
     * @param idDocumento id del documento
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void modificaDocumentoContent(String documentoContent, int idDocumento) throws SQLException;
    /**
     * Cancellare il documento
     *
     * @param idDocumento id del documento
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void deleteDocumento(int idDocumento) throws SQLException;
    /**
     * Ottenere la lista di documenti del team
     *
     * @param listDocumento lista del {@link Documento}
     * @param idTeam id del team
     * @throws SQLException quando la operazione su Postgres fallisce
     * */
    void getListDocumentoByTeam(List<Documento> listDocumento, int idTeam) throws SQLException;
}
