/**
 * 
 */
package org.cesg.jsharelinks.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.cesg.jsharelinks.ConnectioFactory;
import org.cesg.jsharelinks.mappers.LinkMapper;
import org.cesg.jsharelinks.models.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * 
 */
public final class PoolLinkManager implements LinkManager {

    private SqlSession session;
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    public Link selectLink ( Integer id) {

        Link link = null;
        if ( id == null )
            return link;

        OpenSession();
        LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            link = mapper.selectLinkById(id);
        } catch ( Exception e ) {
            _logger.error("Error al intentar seleccionar un Link. {}",
                    e.getMessage());
        } finally {
            this.session.close();
        }
        return link;
    }

    public List<Link> selectAllLink () {
        List<Link> allLinks = new ArrayList<Link>();

        OpenSession();
        LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            allLinks = mapper.selectAllLink();
        } catch ( Exception e ) {
            _logger.error("##Error al seleccionar todas las filas.", e);
        } finally {
            this.session.close();
        }
        return allLinks;
    }

    public Integer insertLink ( Link link) {

        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        OpenSession();
        LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.insertLink(link);
            this.session.commit();
        } catch ( Exception e ) {
            _logger.error("Error al insertar el Link.", e);
        } finally {
            this.session.close();
        }
        return filasAfectadas;
    }

    public Integer deleteLink ( Link link) {

        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        OpenSession();
        LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.deleteLink(link);
        } catch ( Exception e ) {
            _logger.error("Error al eliminar el link.", e);
        } finally {
            this.session.close();
        }
        return filasAfectadas;
    }

    public Integer deleteLinkById ( Integer id) {
        Integer filasAfectadas = null;

        if ( id == null )
            return filasAfectadas;
        OpenSession();
        LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.deleteLinkById(id);
        } catch ( Exception e ) {
            _logger.error("Error al eliminar el link.", e);
        } finally {
            this.session.close();
        }
        return filasAfectadas;
    }

    private final void OpenSession () {
        this.session = ConnectioFactory.getSession().openSession();
    }

}
