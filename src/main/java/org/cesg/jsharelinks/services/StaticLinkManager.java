package org.cesg.jsharelinks.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.cesg.jsharelinks.ConnectioFactory;
import org.cesg.jsharelinks.mappers.LinkMapper;
import org.cesg.jsharelinks.models.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StaticLinkManager implements LinkManager {

    private SqlSession session;
    private static final Logger _logger = LoggerFactory
            .getLogger(StaticLinkManager.class);
    private LinkMapper mapper;

    
    /**
     * Inicia un manager, obtiene una sesión desde <br>
     * ConnectionFactory.
     */
    public StaticLinkManager () {
        final SqlSessionFactory sqlSessionFactory = ConnectioFactory.getSession();
        if ( sqlSessionFactory != null ) {
            this.session = sqlSessionFactory.openSession();
            this.mapper = this.session.getMapper(LinkMapper.class);
        }
    }

    /**
     * Inicia un manager con una sesión establecida.
     * @param _session session, para operar.
     */
    public StaticLinkManager ( SqlSession _session) {
        this.session = _session;
        this.mapper = this.session.getMapper(LinkMapper.class);
    }

    public void closeSession () {
        this.session.close();
    }

    public Link selectLink ( Integer id) {

        Link link = null;
        if ( id == null )
            return link;

        try {
            link = mapper.selectLinkById(id);
        } catch ( final Exception e ) {
            _logger.error("# Error al intentar seleccionar un Link. {}",
                    e.getMessage());
        }
        return link;
    }

    public List<Link> selectAllLink () {
        List<Link> allLinks = new ArrayList<Link>();

        try {
            allLinks = mapper.selectAllLink();
        } catch ( final Exception e ) {
            _logger.error("# Error al seleccionar todas las filas.", e);
        }
        return allLinks;
    }

    public Integer insertLink ( Link link) {
        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        try {
            filasAfectadas = mapper.insertLink(link);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al insertar el Link.", e);
        }
        return filasAfectadas;
    }

    public Integer deleteLink ( Link link) {

        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        try {
            filasAfectadas = mapper.deleteLink(link);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al eliminar el link.", e);
        }
        return filasAfectadas;
    }

    public Integer deleteLinkById ( Integer id) {

        Integer filasAfectadas = null;
        if ( id == null )
            return filasAfectadas;

        try {
            filasAfectadas = mapper.deleteLinkById(id);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al eliminar el link.", e);
        }
        return filasAfectadas;
    }

}
