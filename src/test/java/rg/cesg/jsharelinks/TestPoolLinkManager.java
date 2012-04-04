package rg.cesg.jsharelinks;

import java.util.List;

import junit.framework.Assert;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.services.LinkManager;
import org.cesg.jsharelinks.services.PoolLinkManager;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPoolLinkManager {

    private final Logger _log = LoggerFactory.getLogger(getClass());
    private LinkManager linkManager;

    private static final Integer link_id = 1;
    private static String link_url = "localhost";
    private static String link_comentario = "local";

    private static final Integer link2_id = 2;
    private static String link2_url = "localhost2";
    private static String link2_comentario = "local2";

    private static Link link;
    private static Link link2;
    @Before
    public void setUp () throws Exception {
        this.linkManager = new PoolLinkManager();
        link = new Link();
        link2 = new Link();
        // link.setId(link_id);
        link.setComentario(link_comentario);
        link.setUrl(link_url);
        // link2.setId(link2_id);
        link2.setComentario(link2_comentario);
        link2.setUrl(link2_url);
    }

    @Test
    public final void testSelectLink () {
        Assert.assertNull(linkManager.selectLink(null));
    }

    @Test
    public final void testInsertLink () {
        Assert.assertNull(linkManager.insertLink(null));
    }

    @Test
    public final void testDeleteLink () {
        Assert.assertNull(linkManager.deleteLink(null));
    }

    @Test
    public final void testInsertLink2 () {
        Integer actual = linkManager.insertLink(link);
        _log.debug("Total de row afectadas : {}", actual.intValue());
        Assert.assertNotNull(actual);
    }

    @Test
    public final void testSelectAll () {
        List<Link> allLinks;
        allLinks = linkManager.selectAllLink();
        _log.debug("Total de filas encontradas: {}", allLinks.size());
        Assert.assertFalse(allLinks.size() == 0);
    }
    //
    // @Test
    // public final void testSelectLink2 () {
    // Link actual = linkManager.selectLink(link.getId());
    // Assert.assertEquals(link, actual);
    // }
}
