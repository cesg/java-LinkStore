package org.cesg.jlinkstore.ui.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class UiUtils {
    private static final Logger _logger = LoggerFactory
            .getLogger(UiUtils.class);
    
    public static final String CADENA_VACIA = "";

    public static String tryGetTextFromClipBoard () {
        String resultado = CADENA_VACIA;
        final Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
                .getContents(null);
        try {
            if ( t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor) ) {
                final String text = ( String ) t
                        .getTransferData(DataFlavor.stringFlavor);

                resultado = text.trim();
            }
        } catch ( final Exception e ) {
            _logger.error(
                    "# Error al intentar obtener string desde el clipboard. ",
                    e);
        }
        return resultado;
    }
}
