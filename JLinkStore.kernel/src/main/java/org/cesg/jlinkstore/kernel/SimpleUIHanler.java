/**
 * 
 */
package org.cesg.jlinkstore.kernel;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.cesg.jlinkstore.kernel.services.LinkManager;
import org.cesg.jlinkstore.kernel.services.StaticLinkManager;
import org.cesg.jlinkstore.ui.FrameAdd;
import org.cesg.jlinkstore.ui.UIHandler;
import org.cesg.jlinkstore.ui.models.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * 
 */
public class SimpleUIHanler implements UIHandler {

    private final LinkManager linkManager;
    private static final Logger _logger = LoggerFactory
            .getLogger(SimpleUIHanler.class);

    public SimpleUIHanler () {
        this.linkManager = new StaticLinkManager();
    }

    public void doIr ( final String url) {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch ( IOException | URISyntaxException ex ) {
            _logger.error("# Erro al iniciar el navegador.", ex);
        }
    }

    public void doAgregar ( final Link link) {
        linkManager.insertLink(link);

    }

    public void doBorrarLink ( final Link link) {
        linkManager.deleteLink(link);

    }

    public void doBorrarLink ( final Integer id) {
        linkManager.deleteLinkById(id);

    }

    public void doShowAddLink () {
        new Thread(new FrameAdd(this)).start();
    }

    public void doCerrarConexiones () {
        linkManager.closeSession();
    }

    public List<Link> doSelectAllLink () {
        return linkManager.selectAllLink();
    }

}
