package org.cesg.jsharelinks.ui;

import org.cesg.jsharelinks.models.Link;


/**
 * @author kristian
 * @version 04.04.2012
 */
public interface UIHandler {

    void doIr(String url);
    
    void doAgregar(Link link);
    
    void doBorrarLink(Link link);
}
