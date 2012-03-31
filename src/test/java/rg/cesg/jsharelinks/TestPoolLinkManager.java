package rg.cesg.jsharelinks;

import junit.framework.Assert;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.services.LinkManager;
import org.cesg.jsharelinks.services.PoolLinkManager;
import org.junit.Before;
import org.junit.Test;

public class TestPoolLinkManager {

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
        link = new Link(link_id, link_url, link_comentario);
        link2 = new Link(link2_id, link2_url, link2_comentario);
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
        Integer esperado = 1;
        Integer actual = linkManager.insertLink(link);
        Assert.assertNotNull(actual);
        Assert.assertEquals(esperado, actual);
    }

    @Test
    public final void testSelectLink2 () {
        Link actual = linkManager.selectLink(link.getId());
        Assert.assertEquals(link, actual);
    }
}
