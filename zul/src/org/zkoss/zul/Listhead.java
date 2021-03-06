/* Listhead.java

	Purpose:
		
	Description:
		
	History:
		Fri Aug  5 13:06:38     2005, Created by tomyeh

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 2.1 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zul;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

import org.zkoss.zul.impl.HeadersElement;

/**
 * A list headers used to define multi-columns and/or headers.
 *
 * <p>Difference from XUL:
 * <ol>
 * <li>There is no listcols in ZUL because it is merged into {@link Listhead}.
 * Reason: easier to write Listbox.</li>
 * </ol>
 *  <p>Default {@link #getZclass}: z-listhead.(since 5.0.0)
 *
 * @author tomyeh
 */
public class Listhead extends HeadersElement {
	private Object _value;

	/** Returns the list box that it belongs to.
	 * <p>It is the same as {@link #getParent}.
	 */
	public Listbox getListbox() {
		return (Listbox)getParent();
	}

	/** Returns the value.
	 * <p>Default: null.
	 * <p>Note: the value is application dependent, you can place
	 * whatever value you want.
	 * @since 3.6.0
	 */
	@SuppressWarnings("unchecked")
	public <T> T getValue() {
		return (T)_value;
	}
	/** Sets the value.
	 * @param value the value.
	 * <p>Note: the value is application dependent, you can place
	 * whatever value you want.
	 * @since 3.6.0
	 */
	public <T> void setValue(T value) {
		_value = value;
	}
	/**
	 * @deprecated as of release 6.0.0. To control the size of Listbox related 
	 * components, please refer to {@link Listbox} and {@link Listheader} instead.
	 */
	public void setWidth(String width) {
	}
	/**
	 * @deprecated as of release 6.0.0. To control the size of Listbox related 
	 * components, please refer to {@link Listbox} and {@link Listheader} instead.
	 */
	public void setHflex(String flex) {
	}

	//super//
	public String getZclass() {
		return _zclass == null ? "z-listhead" : _zclass;
	}

	public void beforeParentChanged(Component parent) {
		if (parent != null && !(parent instanceof Listbox))
			throw new UiException("Wrong parent: "+parent);
		super.beforeParentChanged(parent);
	}
	public void beforeChildAdded(Component child, Component refChild) {
		if (!(child instanceof Listheader))
			throw new UiException("Unsupported child for listhead: "+child);
		super.beforeChildAdded(child, refChild);
	}
}
