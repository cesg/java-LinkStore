/**
 * 
 */
package org.cesg.jlinkstore.kernel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

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
    private static final String SYNTHETICA_LAF = "Synthetica";
    private static final String JTATTOO_LAF = "JTatto";
    private static final String GTK_LAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private static final String WIN_LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    /**
     * Main method.
     * @param args
     */
    public static void main (final String[] args) {

        final UIHandler uiHandler = new SimpleUIHanler();
        Map<Opciones, String> mapOpciones = getConfiguration();
        _logger.debug("Opcion Laf : {}", mapOpciones.get(Opciones.LAF));
        
        LookAndFeel laf = null;
        
        try {

            if ( SYNTHETICA_LAF.equalsIgnoreCase(mapOpciones.get(Opciones.LAF)) ) {
                laf = new SyntheticaSimple2DLookAndFeel();
            }
            else if ( JTATTOO_LAF.equalsIgnoreCase(mapOpciones
                    .get(Opciones.LAF)) ) {
                laf = new AcrylLookAndFeel();
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

    private static Map<Opciones, String> getConfiguration () {
        Properties propiedades = new Properties();
        Map<Opciones, String> mapOpciones = new HashMap<>();
        try {
            propiedades.load(new FileInputStream("JLinkStore.properties"));
            mapOpciones.put(Opciones.LAF, propiedades.get("LAF").toString());
        }
        catch ( IOException e ) {
            _logger.error("# Error al leer el archivo de configuracion: {}",
                    e.getMessage());
        }
        return mapOpciones;
    }

    public enum Opciones {
        LAF
    }
}
