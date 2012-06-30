package org.cesg.jlinkstore.kernel.config;

import java.io.File;

import org.cesg.utilidades.opciones.Opciones;
import org.cesg.utilidades.opciones.OpcionesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JlinkStoreOpciones.java<br/>
 * Extends: {@link org.cesg.jlinkstore.kernel.confi.Opciones Opciones}<br/>
 * 
 * @author cesg
 * 
 */
public class JlinkStoreOpciones extends Opciones {

    private String keyLookAndFeel = "LookAndFeel";
    private String valueLookAndFeel;
    private String direccionArchivo;

    private static final Logger logger = LoggerFactory
            .getLogger(JlinkStoreOpciones.class);

    public JlinkStoreOpciones ( File archivoConfig) {
        super(archivoConfig);
        direccionArchivo = archivoConfig.getAbsolutePath();
        try {
            leerOpciones();
            generar();
        }
        catch ( final OpcionesException e ) {
            logger.error("# Error al leer la opciones. {}", e.getMensaje());
        }
    }

    public JlinkStoreOpciones ( String archivoPath) {
        this(new File(archivoPath));
    }

    private void generar () {
        valueLookAndFeel = leerPropiedad(keyLookAndFeel);
    }

    /**
     * Obtiene el lookAndFeel obtenido del archivo de configuracion, de no tener
     * acceso al archivo retorna el caso "DEFECTO".
     * 
     * @return {@link PosiblesLaf PosiblesLaf} con la opcion.
     */
    public PosiblesLaf getOpcionLookAndFeel () {
        PosiblesLaf posiblesLaf = PosiblesLaf.DEFECTO;
        if ( valueLookAndFeel.equalsIgnoreCase(PosiblesLaf.JTATTOO.value) ) {
            posiblesLaf = PosiblesLaf.JTATTOO;
        }
        else if ( valueLookAndFeel.equals(PosiblesLaf.SYNTHETICA.value) ) {
            posiblesLaf = PosiblesLaf.SYNTHETICA;
        }
        return posiblesLaf;
    }

    /**
     * Establece el lookAndFeel de la aplicacion.
     * 
     * @param lookAndFeel
     *            nuevo valor para el lookAndFeel
     */
    public void setOpcionLookAndFeel ( PosiblesLaf lookAndFeel) {
        try {
            actualiza(keyLookAndFeel, lookAndFeel.value);
            valueLookAndFeel = lookAndFeel.value;
        }
        catch ( OpcionesException e ) {
            logger.error("# Error al guardar la opcion.", e);
        }
    }

    public String getDireccionArchivo () {
        return direccionArchivo;
    }

    /**
     * Enumeracion con los lookAndFeel soportados por la aplicacion.
     * 
     * @author cesg
     * 
     */
    public enum PosiblesLaf {
        SYNTHETICA("synthetica"), JTATTOO("jtattoo"), DEFECTO("defecto");

        private String value;

        private PosiblesLaf ( String value) {
            this.value = value;
        }

        public String getStringValue () {
            return value;
        }
    }
}
