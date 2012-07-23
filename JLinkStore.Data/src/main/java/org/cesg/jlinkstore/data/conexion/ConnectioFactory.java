package org.cesg.jlinkstore.data.conexion;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * @version 29.06.2012
 */
public class ConnectioFactory {

    private static final String resource = "mybatis-config.xml";
    private static SqlSessionFactory sqlMapper;
    private static Reader reader;
    private static final Logger _logger = LoggerFactory
            .getLogger(ConnectioFactory.class);

    static {
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlMapper = new SqlSessionFactoryBuilder().build(reader);
        }
        catch ( final Exception e ) {
            _logger.error(
                    "No se a logrado iniciar una sesion en la base de datos.",
                    e);
        }

    }

    /**
     * Obtiene una session para comunicarse con <br/>
     * una base de datos.
     * 
     * @return una instancia de una session.
     */
    public static SqlSessionFactory getSession () {
        return sqlMapper;
    }
}
