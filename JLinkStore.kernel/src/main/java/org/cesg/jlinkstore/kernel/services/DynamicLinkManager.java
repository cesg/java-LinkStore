/**
 * 
 */
package org.cesg.jlinkstore.kernel.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.cesg.jlinkstore.kernel.ConnectioFactory;
import org.cesg.jlinkstore.kernel.mappers.LinkMapper;
import org.cesg.jlinkstore.ui.models.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kristian
 * 
 */
public final class DynamicLinkManager implements LinkManager {

    private SqlSession session;
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    public Link selectLink ( Integer id) {

        Link link = null;
        if ( id == null )
            return link;

        OpenSession();
        final LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            link = mapper.selectLinkById(id);
        } catch ( final Exception e ) {
            _logger.error("# Error al intentar seleccionar un Link. {}",
                    e.getMessage());
        } finally {
            closeSession();
        }
        return link;
    }

    public List<Link> selectAllLink () {
        List<Link> allLinks = new ArrayList<Link>();

        OpenSession();
        final LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            allLinks = mapper.selectAllLink();
        } catch ( final Exception e ) {
            _logger.error("# Error al seleccionar todas las filas.", e);
        } finally {
            closeSession();
        }
        return allLinks;
    }

    public Integer insertLink ( Link link) {

        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        OpenSession();
        final LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.insertLink(link);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al insertar el Link.", e);
        } finally {
            closeSession();
        }
        return filasAfectadas;
    }

    public Integer deleteLink ( Link link) {

        Integer filasAfectadas = null;
        if ( link == null )
            return filasAfectadas;

        OpenSession();
        final LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.deleteLink(link);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al eliminar el link.", e);
        } finally {
            closeSession();
        }
        return filasAfectadas;
    }

    public Integer deleteLinkById ( Integer id) {
        Integer filasAfectadas = null;

        if ( id == null )
            return filasAfectadas;
        OpenSession();
        final LinkMapper mapper = this.session.getMapper(LinkMapper.class);
        try {
            filasAfectadas = mapper.deleteLinkById(id);
            this.session.commit();
        } catch ( final Exception e ) {
            _logger.error("# Error al eliminar el link.", e);
        } finally {
            closeSession();
        }
        return filasAfectadas;
    }

    private final void OpenSession () {
        this.session = ConnectioFactory.getSession().openSession();
    }

    public void closeSession () {
        if ( this.session != null )
            this.session.close();
    }
}
