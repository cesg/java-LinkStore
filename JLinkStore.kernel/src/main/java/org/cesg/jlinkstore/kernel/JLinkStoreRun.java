package org.cesg.jlinkstore.kernel;

import java.net.URL;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import org.cesg.jlinkstore.kernel.confi.JlinkStoreOpciones;
import org.cesg.jlinkstore.kernel.confi.JlinkStoreOpciones.PosiblesLaf;
import org.cesg.jlinkstore.ui.UIHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

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
    private static JlinkStoreOpciones opciones;
    private static final String fileConfName = "/JLinkStore.properties";

    /**
     * Main method.
     * @param args
     */
    public static void main (final String[] args) {

        final UIHandler uiHandler = new SimpleUIHanler();
        URL fileConfPath = JLinkStoreRun.class.getClass().getResource(
                fileConfName);

        if ( fileConfPath != null )
            opciones = new JlinkStoreOpciones(fileConfPath.getPath());

        LookAndFeel laf = null;
        PosiblesLaf opcionLaf = PosiblesLaf.DEFECTO;

        if ( opciones != null )
            opcionLaf = opciones.getOpcionLookAndFeel();

        try {

            switch (opcionLaf) {
            case DEFECTO:
                break;
            case SYNTHETICA:
                laf = new SyntheticaSimple2DLookAndFeel();
                break;
            case JTATTOO:
                laf = new AcrylLookAndFeel();
            default:
                break;
            }
            
            if ( laf != null && laf.isSupportedLookAndFeel() )
                UIManager.setLookAndFeel(laf);
            else if ( estaEnLinux() )
                UIManager.setLookAndFeel(GTK_LAF);
            else
                UIManager.setLookAndFeel(WIN_LAF);
        } catch ( final Exception e ) {
            _logger.error("# Imposible establecer el LookAndFeel.", e);
        } finally {
            new Thread(new org.cesg.jlinkstore.ui.FrameMain(uiHandler)).start();
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
