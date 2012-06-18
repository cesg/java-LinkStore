package org.cesg.jlinkstore.kernel.confi;

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

    public OpcionesException ( String mensaje , java.io.File archivo) {
        super(mensaje.concat(archivo.getAbsolutePath()));
        this.mensaje = getMessage();
    }
    public String getMensaje () {
        return mensaje;
    }
}
