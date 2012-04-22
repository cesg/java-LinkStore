package org.cesg.jlinkstore.ui;

import org.cesg.jlinkstore.models.Link;

/**
 * Interfas para la comunicación de la UI. <br>
 * 
 * @author kristian
 * @version 04.04.2012
 */
public interface UIHandler {

    /**
     * Intenta abrir la url.
     * 
     * @param url
     *            dirección.
     */
    void doIr ( String url);

    /**
     * Intenta agregar un link.
     * 
     * @param link
     */
    void doAgregar ( Link link);

    /**
     * Intenta borrar un link.
     * 
     * @param link
     */
    void doBorrarLink ( Link link);

    /**
     * Intenta borrar un link por su id.
     * 
     * @param id
     *            'id' del link.
     */
    void doBorrarLink ( Integer id);

    /**
     * Abre una ventana nueva para insertar <br>
     * un link.
     */
    void doShowAddLink ();

    /**
     * Cierra todas la conexiones a la base de datos.
     */
    void doCerrarConexiones ();
}
