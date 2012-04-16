/**
 * 
 */
package org.cesg.jsharelinks;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.cesg.jsharelinks.ui.MainUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;

/**
 * @author kristian
 * @version 05.04.2012
 */
public class JShareLinksInicio {

    private static final Logger _logger = LoggerFactory
            .getLogger(JShareLinksInicio.class);
    private static final String SO_LINUX = "Linux";
    private static final String GTK_LAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private static final String WIN_LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

    /**
     * @param args
     */
    public static void main ( String[] args) {

        final SimpleUIHanler uiHandler = new SimpleUIHanler();
        try {
            final AcrylLookAndFeel acrylLookAndFeel = new AcrylLookAndFeel();
            if ( acrylLookAndFeel.isSupportedLookAndFeel() )
                UIManager.setLookAndFeel(acrylLookAndFeel);
            else if ( estaEnLinux() )
                UIManager.setLookAndFeel(GTK_LAF);
            else
                UIManager.setLookAndFeel(WIN_LAF);
        } catch ( final ClassNotFoundException e ) {
            _logger.error("# LookAndFeel no encontrada.", e);
        } catch ( final InstantiationException e ) {
            _logger.error("# LookAndFeel no se puede iniciar una instancia.", e);
        } catch ( final IllegalAccessException e ) {
            _logger.error("# LookAndFeel no se puede acceder.", e);
        } catch ( final UnsupportedLookAndFeelException e ) {
            _logger.error("# LookAndFeel no soportada.", e);
        } finally {
            new Thread(new MainUI(uiHandler)).start();
        }

    }

    private static Boolean estaEnLinux () {
        final String so = System.getProperty("os.name");
        _logger.info("# Programa corriendo sobre: {}", so);
        return SO_LINUX.equalsIgnoreCase(so);
    }
}
