/**
 * 
 */
package org.cesg.jsharelinks;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.services.LinkManager;
import org.cesg.jsharelinks.services.PoolLinkManager;
import org.cesg.jsharelinks.ui.AddUI;
import org.cesg.jsharelinks.ui.UIHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * 
 */
public class SimpleUIHanler implements UIHandler {

    private final LinkManager linkManager = new PoolLinkManager();
    private static final Logger _logger = LoggerFactory
            .getLogger(SimpleUIHanler.class);

    /*
     * (non-Javadoc)
     * 
     * @see org.cesg.jsharelinks.ui.UIHandler#doIr(java.lang.String)
     */
    public void doIr ( String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch ( IOException e ) {
            _logger.error("", e);
        } catch ( URISyntaxException e ) {
            _logger.error("", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cesg.jsharelinks.ui.UIHandler#doAgregar(org.cesg.jsharelinks.models
     * .Link)
     */
    public void doAgregar ( final Link link) {
        if ( link != null )
            linkManager.insertLink(link);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cesg.jsharelinks.ui.UIHandler#doBorrarLink(org.cesg.jsharelinks.models
     * .Link)
     */
    public void doBorrarLink ( final Link link) {
        linkManager.deleteLink(link);
    }

    public void doShowAddLink () {
        new Thread(new AddUI(this)).start();
    }

}