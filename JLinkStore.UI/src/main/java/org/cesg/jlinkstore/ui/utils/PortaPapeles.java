/**
 * 
 */
package org.cesg.jlinkstore.ui.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilizada para obtener y establecer elementos en el clipboard, implementa<br>
 * ClipboardOwner.
 * 
 * @author Kristian
 * 
 */
public final class PortaPapeles implements ClipboardOwner {

    public static final String CADENA_VACIA = "";
    private static final Logger _logger = LoggerFactory
            .getLogger(PortaPapeles.class);

    /**
     * Obtiene la ultima cadena en el clipboard.
     * 
     * @return La cadena almacenada en el board.
     */
    public String getClipboard () {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);
        String text = CADENA_VACIA;
        try {
            if ( t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor) ) {
                text = ( String ) t
                        .getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch ( UnsupportedFlavorException e ) {
            _logger.error("# Error {}", e.getMessage());
        }
        catch ( IOException e ) {
            _logger.error("# Error {}", e.getMessage());
        }
            return text;

    }

    /**
     * Establece el contenido del clipboard.
     * 
     * @param str
     */
    public void setClipboard ( final String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, this);
    }

    public void lostOwnership ( Clipboard arg0 , Transferable arg1) {
        // TODO Auto-generated method stub

    }

}
