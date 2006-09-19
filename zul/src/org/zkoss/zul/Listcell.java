/* Listcell.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Aug  5 13:06:17     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zul;

import java.util.List;
import java.util.Iterator;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

import org.zkoss.zul.impl.LabelImageElement;

/**
 * A list cell.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 */
public class Listcell extends LabelImageElement {
	private Object _value;

	public Listcell() {
	}
	public Listcell(String label) {
		setLabel(label);
	}
	public Listcell(String label, String src) {
		setLabel(label);
		setImage(src);
	}

	/** Returns the list box that it belongs to.
	 */
	public Listbox getListbox() {
		final Component comp = getParent();
		return comp != null ? (Listbox)comp.getParent(): null;
	}
	/** Returns the list item that it belongs to.
	 */
	public Listitem getListitem() {
		return (Listitem)getParent();
	}
	/** Returns the list header that is in the same column as
	 * this cell, or null if not available.
	 */
	public Listheader getListheader() {
		final Listbox listbox = getListbox();
		if (listbox != null) {
			final Listhead lcs = listbox.getListhead();
			if (lcs != null) {
				final int j = getColumnIndex();
				final List lcschs = lcs.getChildren();
				if (j < lcschs.size())
					return (Listheader)lcschs.get(j);
			}
		}
		return null;
	}
	/** Returns the column index of this cell, starting from 0.
	 */
	public int getColumnIndex() {
		int j = 0;
		for (Iterator it = getParent().getChildren().iterator();
		it.hasNext(); ++j)
			if (it.next() == this)
				break;
		return j;
	}

	/** Returns the maximal length for this cell.
	 * If listbox's mold is "select", it is the same as
	 * {@link Listbox#getMaxlength}
	 * If not, it is the same as the correponding {@link #getListheader}'s 
	 * {@link Listheader#getMaxlength}.
	 *
	 * <p>Note: {@link Listitem#getMaxlength} is the same as {@link Listbox#getMaxlength}.
	 *
	 * <p>Note: DBCS counts  two bytes (range 0x4E00~0x9FF).
	 * Default: 0 (no limit).
	 */
	public int getMaxlength() {
		final Listbox listbox = getListbox();
		if (listbox == null)
			return 0;
		if (listbox.inSelectMold())
			return listbox.getMaxlength();
		final Listheader lc = getListheader();
		return lc != null ? lc.getMaxlength(): 0;
	}

	/** Returns the value.
	 * <p>Default: null.
	 * <p>Note: the value is application dependent, you can place
	 * whatever value you want.
	 */
	public Object getValue() {
		return _value;
	}
	/** Sets the value.
	 * @param value the value.
	 * <p>Note: the value is application dependent, you can place
	 * whatever value you want.
	 */
	public void setValue(Object value) {
		_value = value;
	}

	//-- super --//
	/** Returns the width which the same as {@link #getListheader}'s width.
	 */
	public String getWidth() {
		final Listheader listheader = getListheader();
		return listheader != null ? listheader.getWidth(): null;
	}
	public void setWidth(String width) {
		throw new UnsupportedOperationException("Set listheader's width instead");
	}

	//-- Internal use only --//
	/** Returns the prefix of the first column (in HTML tags), null if this
	 * is not first column. Called only by listcell.jsp.
	 */
	public String getColumnHtmlPrefix() {
		final Listitem item = getListitem();
		final Listbox listbox = getListbox();
		if (listbox != null && listbox.isCheckmark()
		&& item.getChildren().get(0) == this) {
			final StringBuffer sb = new StringBuffer(64)
				.append("<input type=\"")
				.append(listbox.isMultiple() ? "checkbox": "radio")
				.append('"');
			if (item.isSelected())
				sb.append(" checked=\"checked\"");
			sb.append(" id=\"").append(item.getUuid()).append("!cm\"")
				.append(" zk_type=\"Lcfc\"").append("/>");
			return sb.toString();
		} else {
			//To make the listbox's height more correct, we have to generate &nbsp;
			//for empty cell. Otherwise, IE will make the height too small
			final boolean empty = getImage() == null
			&& getLabel().length() == 0 && getChildren().isEmpty();
			return empty ? "&nbsp;": null;
		}
	}
	/** Returns the postfix of the first column (in HTML tags), null if this
	 * is not first column. Called only by listcell.jsp.
	 */
	public String getColumnHtmlPostfix() {
		final Listitem item = getListitem();
		final Listbox listbox = getListbox();
		if (listbox != null && !listbox.isCheckmark()
		&& item.getChildren().get(0) == this
		&& isFocusRequired(listbox, item)) {
			final StringBuffer sb = new StringBuffer(64)
				.append("<a href=\"javascript:;\" id=\"")
				.append(item.getUuid()).append("!sel\"")
				.append(" zk_type=\"Lcfc\"").append("> </a>");
			return sb.toString();
		} else {
			return null;
		}
	}
	/** Returns whether this cell requires focus.
	 */
	private boolean isFocusRequired(Listbox listbox, Component parent) {
		final Listitem sel = listbox.getSelectedItem();
		return parent == sel
			|| (sel == null && ((Listitem)parent).getIndex() == 0);
	}

	//-- super --//
	public String getOuterAttrs() {
		final String attrs = super.getOuterAttrs();
		final Listheader header = getListheader();
		return header != null ? attrs+header.getColAttrs(): attrs;
	}

	//-- Component --//
 	public void setParent(Component parent) {
		if (parent != null && !(parent instanceof Listitem))
			throw new UiException("Wrong parent: "+parent);
		super.setParent(parent);
	}
	public void invalidate() {
		final Listbox listbox = getListbox();
		if (listbox != null && listbox.inSelectMold()) {
			getParent().invalidate();
			//if HTML select, the cell doesn't exists in client
		} else {
			super.invalidate();
		}
	}
}
