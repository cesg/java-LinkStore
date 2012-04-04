/**
 * 
 */
package org.cesg.jsharelinks;

import org.cesg.jsharelinks.ui.MainUI;

/**
 * @author kristian
 *
 */
public class JShareLinksInicio {

    /**
     * @param args
     */
    public static void main ( String[] args) {
        
        SimpleUIHanler uiHandler = new SimpleUIHanler();
        new Thread(new MainUI(uiHandler)).start();
    }

}
