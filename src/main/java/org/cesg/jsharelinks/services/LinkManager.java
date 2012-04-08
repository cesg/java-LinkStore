package org.cesg.jsharelinks.services;

import java.util.List;

import org.cesg.jsharelinks.models.Link;

/**
 * Manejador de link <br>
 * 
 * @author kristian
 * @version 30.03.2012
 */
public interface LinkManager {

    /**
     * selecciona un Link segun la 'id'
     * 
     * @param id
     *            'id' del Link
     * @return Link asociado al id, nulo si no sencuentra.
     */
    Link selectLink ( final Integer id);

    /**
     * selecciona todos los links.
     * 
     * @return Lista con los Links.
     */
    List<Link> selectAllLink ();

    /**
     * inserta un Link.
     * 
     * @param link
     *            Link a insertar.
     * @return Integer con el numero de filas afectadas, <br>
     *         null si no modifico ninguna.
     */
    Integer insertLink ( final Link link);

    /**
     * Borra un Link.
     * 
     * @param link
     *            Link a borrar.
     * @return Integer con el numero de filas afectadas, <br>
     *         null si no modifico ninguna.
     */
    Integer deleteLink ( final Link link);

    /**
     * Borra un link.
     * 
     * @param id
     *            'id' del link.
     * @return Integer con el numero de filas afectadas, <br>
     *         null si no modifico ninguna.
     */
    Integer deleteLinkById ( Integer id);
    
    /**
     * Cierra la session.
     */
    void closeSession();
}
