/**
 * 
 */
package org.cesg.jlinkstore;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import org.cesg.jlinkstore.models.Link;
import org.cesg.jlinkstore.services.LinkManager;
import org.cesg.jlinkstore.services.StaticLinkManager;
import org.cesg.jlinkstore.ui.AddUI;
import org.cesg.jlinkstore.ui.UIHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * 
 */
public class SimpleUIHanler implements UIHandler {

    private final LinkManager linkManager = new StaticLinkManager();
    private static final Logger _logger = LoggerFactory
            .getLogger(SimpleUIHanler.class);

    public void doIr ( String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch ( final IOException e ) {
            _logger.error("", e);
        } catch ( final URISyntaxException e ) {
            _logger.error("", e);
        }
    }

    public void doAgregar ( final Link link) {
        if ( link != null )
            linkManager.insertLink(link);
    }

    public void doBorrarLink ( final Link link) {
        linkManager.deleteLink(link);
    }

    public void doShowAddLink () {
        new Thread(new AddUI(this)).start();
    }

    public void doBorrarLink ( Integer id) {
        linkManager.deleteLinkById(id);
    }

    public void doCerrarConexiones () {
        linkManager.closeSession();
    }

}
