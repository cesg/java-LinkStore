/**
 * 
 */
package org.cesg.jsharelinks;

import org.cesg.jsharelinks.models.Link;
import org.cesg.jsharelinks.ui.AddUI;
import org.cesg.jsharelinks.ui.UIHandler;

/**
 * @author kristian
 * 
 */
public class SimpleUIHanler implements UIHandler {

    /*
     * (non-Javadoc)
     * 
     * @see org.cesg.jsharelinks.ui.UIHandler#doIr(java.lang.String)
     */
    public void doIr ( String url) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cesg.jsharelinks.ui.UIHandler#doAgregar(org.cesg.jsharelinks.models
     * .Link)
     */
    public void doAgregar ( Link link) {
        // TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cesg.jsharelinks.ui.UIHandler#doBorrarLink(org.cesg.jsharelinks.models
     * .Link)
     */
    public void doBorrarLink ( Link link) {
        // TODO Auto-generated method stub

    }

    public void doShowAddLink () {
        new Thread(new AddUI(this)).start();

    }

}
