/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.krinsoft.race.regions;

/**
 *
 * @author Krin
 */
public class IncompleteRegionException extends Exception {

    /**
     * Creates a new instance of <code>IncompleteRegionException</code> without detail message.
     */
    public IncompleteRegionException() {
    }


    /**
     * Constructs an instance of <code>IncompleteRegionException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public IncompleteRegionException(String msg) {
        super(msg);
    }
}
