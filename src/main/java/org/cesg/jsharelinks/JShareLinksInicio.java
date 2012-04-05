/**
 * 
 */
package org.cesg.jsharelinks;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.cesg.jsharelinks.ui.MainUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author kristian
 * @version 05.04.2012
 */
public class JShareLinksInicio {

    private static final Logger _logger = LoggerFactory.getLogger(JShareLinksInicio.class);
    private static final String SO_LINUX = "Linux";
    private static final String GTK_LAF = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    private static final String WIN_LAF = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    /**
     * @param args
     */
    public static void main ( String[] args) {
        
        SimpleUIHanler uiHandler = new SimpleUIHanler();
        try {
            if(estaEnLinux())
                UIManager.setLookAndFeel(GTK_LAF);
            else
                UIManager.setLookAndFeel(WIN_LAF);
        } catch ( ClassNotFoundException e ) {
            _logger.error("",e);
        } catch ( InstantiationException e ) {
            _logger.error("",e);
        } catch ( IllegalAccessException e ) {
            _logger.error("",e);
        } catch ( UnsupportedLookAndFeelException e ) {
            _logger.error("",e);
        }
        new Thread(new MainUI(uiHandler)).start();
    }

    private static Boolean estaEnLinux() {
        String so = System.getProperty("os.name");
        _logger.info("# Programa corriendo sobre: {}",so);
        return SO_LINUX.equalsIgnoreCase(so);
    }
}
