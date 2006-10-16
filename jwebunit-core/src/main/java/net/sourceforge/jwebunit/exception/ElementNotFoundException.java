/******************************************************************************
 * jWebUnit project (http://jwebunit.sourceforge.net)                         *
 * Distributed open-source, see full license under LICENCE.txt                *
 ******************************************************************************/
package net.sourceforge.jwebunit.exception;

import net.sourceforge.jwebunit.locator.Locator;

/**
 * This exception should be used when an expected element is not found.
 * @author Julien Henry (henryju@yahoo.fr)
 * 
 */
@SuppressWarnings("serial")
public class ElementNotFoundException extends JWebUnitException {
    
    private Locator elementNotFound;
   
    //TODO Perhaps add some informations about missing element (tag, name, ...).
    
    /**
     * @return Returns the elementNotFound.
     */
    public Locator getElementNotFound() {
        return elementNotFound;
    }

    public ElementNotFoundException(String msg) {
        super(msg);
    }

    public ElementNotFoundException(String msg, Exception cause) {
        super(msg, cause);
    }
    
    public ElementNotFoundException(Locator elementNotFound) {
        super();
        this.elementNotFound=elementNotFound;        
    }

}