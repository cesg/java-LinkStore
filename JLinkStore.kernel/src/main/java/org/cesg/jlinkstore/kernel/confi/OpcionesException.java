package org.cesg.jlinkstore.kernel.confi;

import java.io.File;
import java.io.IOException;

/**
 * OpcionesException.java<br/>
 * Extends: {@link java.io.IOException IOException}<br/>
 * Representa una excepcion al utilizar la clase
 * {@link org.cesg.utilidades.opciones.Opciones Opciones}
 * 
 * @author cesg
 * 
 */
public class OpcionesException extends IOException {

    private static final long serialVersionUID = 1532L;

    private final String mensaje;

    public OpcionesException ( String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public OpcionesException ( String mensaje , Exception exception) {
        this(mensaje.concat(" :").concat(exception.getMessage()));
    }

    public OpcionesException ( String mensaje , File archivo) {
        this(mensaje.concat(": ").concat(archivo.getAbsolutePath()));
    }
    public String getMensaje () {
        return mensaje;
    }
}
