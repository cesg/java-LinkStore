package org.cesg.jlinkstore.kernel.confi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Opciones.java<br/>
 * Clase base que permite leer y guardar opciones un archivo de texto,
 * utilizadas por la aplicación, se utiliza la clase
 * {@link java.util.Properties Properties} para el manejo de estas.
 * 
 * @author cesg.
 * 
 */
public class Opciones {

    private File archivoConfig;
    private Properties propiedades;
    private static final String COMENTARIO_CABEZA = "Last edit:";

    public Opciones ( File archivoConfig) {
        this.archivoConfig = archivoConfig;
    }

    /**
     * Lee las opciones presentes en el archivo y se almacenan.
     * 
     * @throws OpcionesException
     *             Si el archivo no existe o presenta problemas de lectura.
     */
    public void leerOpciones () throws OpcionesException {

        if ( !archivoConfig.exists() )
            throw new OpcionesException("El archivo no existe :", archivoConfig);

        propiedades = new Properties();
        InputStream fileStream;
        try {
            fileStream = new FileInputStream(archivoConfig);
            propiedades.load(fileStream);
            fileStream.close();
        }
        catch ( final IOException excetion ) {
            throw new OpcionesException(excetion.getMessage());
        }
    }

    /**
     * Escribe el archivo con las opciones almacenadas en el objeto Properties
     * 
     * @throws OpcionesException
     *             Si el archivo no existe o presenta problemas de escritura.
     */
    protected void guardar () throws OpcionesException {

        if ( !archivoConfig.exists() )
            throw new OpcionesException("El archivo no existe :", archivoConfig);

        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(archivoConfig, false);
            propiedades.store(fileOut, COMENTARIO_CABEZA);
            fileOut.close();
        }
        catch ( final IOException excetion ) {
            throw new OpcionesException(excetion.getMessage());
        }

    }

    /**
     * Obtiene una especifica opcion.
     * 
     * @param key
     *            nombre clave de la propiedad.
     * @return si la key existe retorna su valor, de no existir null.
     */
    public String leerPropiedad ( final String key) {
        return propiedades.getProperty(key);
    }

    /**
     * Actualiza una opcion especifica.
     * 
     * @param key
     *            nombre clave de la propiedad.
     * @param value
     *            nuevo valor.
     * @throws OpcionesException
     *             Si la key no existe o presenta problemas al guardar.
     */
    public void actualiza ( final String key , final String value)
            throws OpcionesException {
        if ( propiedades.containsKey(key) ) {
            propiedades.setProperty(key, value);
            guardar();
        }
        else
            throw new OpcionesException("No encontro la propiedad.");
    }

    /**
     * Escribe una nueva propiedad en el archivo, de no existir la crea.
     * 
     * @param key
     *            nombre clave de la propiedad.
     * @param value
     *            valor.
     * @throws OpcionesException
     *             Si ocurre un error al guardar la opcion.
     */
    public void escribe ( String key , String value)
            throws OpcionesException {
        propiedades.setProperty(key, value);
        guardar();
    }
}
