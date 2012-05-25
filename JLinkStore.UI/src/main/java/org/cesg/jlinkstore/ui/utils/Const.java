package org.cesg.jlinkstore.ui.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

public final class Const {

    /**
     * Representa una instacia de una cadena vacia.
     */
    public static final String CADENA_VACIA = "";
    /**
     * Una instancia del PortaPapeles.
     */
    public static final PortaPapeles PORTAPAPELES = new PortaPapeles();
    private static final String IMAGE_ICON_PATH = "icono.png";

    public Const () {
    }

    /**
     * Obtiene la imagen para ser usada en los JFrame.
     * 
     * @return Imagen Usada como icono.
     */
    public Image getFrameImage () {
        return new ImageIcon(getClass().getResource(IMAGE_ICON_PATH))
                .getImage();
    }
}
