/**
 * 
 */
package net.sourceforge.jwebunit.htmlunit;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.javascript.host.Node;

import net.sourceforge.jwebunit.api.IElement;

/**
 * HtmlUnit implementation of IElement wrapper.
 * 
 * @author jmwright
 *
 */
public class HtmlUnitElementImpl implements IElement {
	
	/**
	 * The wrapped element.
	 */
	private HtmlElement element;
	
	public HtmlUnitElementImpl(HtmlElement element) {
		this.element = element;
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#attribute(java.lang.String)
	 */
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#name()
	 */
	public String getName() {
		return element.getNodeName();
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#getChildren()
	 */
	public List<IElement> getChildren() {
		List<IElement> children = new ArrayList<IElement>();
		for (HtmlElement e : element.getChildElements())
			children.add(new HtmlUnitElementImpl(e));
		return children;
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#getParent()
	 */
	public IElement getParent() {
		DomNode p = element.getParentNode();
		while (true) {
			if (p == null)
				return null;
			
			if (p instanceof HtmlElement)
				return new HtmlUnitElementImpl((HtmlElement) p);
			
			// get next parent
			p = p.getParentNode();
		}
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#getTextContent()
	 */
	public String getTextContent() {
		return element.getTextContent();
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#getElement(java.lang.String)
	 */
	public IElement getElement(String xpath) {
		// if this fails with a ClassCastException, use getElements().get(0) (performance penalty)
		return new HtmlUnitElementImpl((HtmlElement) element.getFirstByXPath(xpath));
	}


	/* (non-Javadoc)
	 * @see net.sourceforge.jwebunit.api.IElement#getElements(java.lang.String)
	 */
	public List<IElement> getElements(String xpath) {
		List<IElement> elements = new ArrayList<IElement>();
		for (Object o : element.getByXPath(xpath)) {
			if (o instanceof HtmlElement)
				elements.add(new HtmlUnitElementImpl((HtmlElement) o));
		}
		return elements;
	}

}
