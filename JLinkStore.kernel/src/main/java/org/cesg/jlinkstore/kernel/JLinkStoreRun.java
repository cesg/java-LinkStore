/**
 * 
 */
package org.cesg.jlinkstore.kernel;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;

/**
 * @author kristian
 * @version 05.04.2012
 */
public class JLinkStoreRun {

    private static final Logger _logger = LoggerFactory
            .getLogger(JLinkStoreRun.class);
    private static final String SO_LINUX = "Linux";
    private static final String GTK_LAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private static final String WIN_LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    /**
     * Main method.
     * @param args
     */
    public static void main (final String[] args) {

        final SimpleUIHanler uiHandler = new SimpleUIHanler();
        
        try {
            final LookAndFeel laf = new SyntheticaSimple2DLookAndFeel();
            
            if (laf.isSupportedLookAndFeel())
                UIManager.setLookAndFeel(laf);
            else if ( estaEnLinux() )
                UIManager.setLookAndFeel(GTK_LAF);
            else
                UIManager.setLookAndFeel(WIN_LAF);
        } catch ( final Exception e ) {
            _logger.error("# Imposible establecer el LookAndFeel.", e);
        } finally {
            new Thread(new org.cesg.jlinkstore.ui.MainUI(uiHandler)).start();
        }

    }

    /**
     * Comprueba si el sistema es linux.
     * @return True si esta en linux.
     */
    private static Boolean estaEnLinux () {
        final String so = System.getProperty("os.name");
        _logger.info("# Programa corriendo sobre: {}", so);
        return SO_LINUX.equalsIgnoreCase(so);
    }
}
