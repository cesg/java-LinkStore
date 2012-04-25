package org.cesg.jlinkstore.kernel;

import junit.framework.Assert;

import org.apache.ibatis.session.SqlSessionFactory;
import org.cesg.jlinkstore.kernel.ConnectioFactory;
import org.junit.Test;

public class TestConnectioFactory {

    @Test
    public void testGetSession () {
        SqlSessionFactory connection =   ConnectioFactory.getSession();
        Assert.assertNotNull(connection);
    }

}
