/* Box.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Sep 16 13:59:54     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.web.servlet.dsp.action.html;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

import com.potix.web.mesg.MWeb;
import com.potix.web.servlet.ServletException;
import com.potix.web.servlet.http.Encodes;
import com.potix.web.servlet.dsp.action.AbstractAction;
import com.potix.web.servlet.dsp.action.ActionContext;

/**
 * Generates a box that has a caption and a border enclosing other tags.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.4 $ $Date: 2006/05/29 04:27:41 $
 */
public class Box extends AbstractAction {
	private String _align;
	private String _color = "black";
	private String _spacing = "3";
	private String _width = "100%";
	private String _caption;
	private boolean _shadow = false;

	/** Returns the horizontal alignment.
	 * Default: null.
	 */
	public String getAlign() {
		return _align;
	}
	/** Sets the horizontal alignment.
	 */
	public void setAlign(String align) {
		_align = align;
	}

	/** Returns the color.
	 * Default: black.
	 */
	public String getColor() {
		return _color;
	}
	/** Sets the color.
	 * <p>You might use any string that HTML supports.
	 */
	public void setColor(String color) {
		_color = color;
	}

	/** Returns whether this box has the shadow effect.
	 * Default: false.
	 */
	public boolean isShadow() {
		return _shadow;
	}
	/** Sets whether this box has the shadow effect.
	 */
	public void setShadow(boolean shadow) {
		_shadow = shadow;
	}

	/** Returns the spacing.
	 * Default: 3.
	 */
	public String getSpacing() {
		return _spacing;
	}
	/** Sets the spacing.
	 */
	public void setSpacing(String spacing) {
		_spacing = spacing;
	}

	/** Returns the width.
	 * Default: 100%.
	 */
	public String getWidth() {
		return _width;
	}
	/** Sets the width.
	 */
	public void setWidth(String width) {
		_width = width;
	}

	/** Returns the caption.
	 * Default: null (no caption at all).
	 */
	public String getCaption() {
		return _caption;
	}
	/** Sets the caption.
	 */
	public void setCaption(String caption) {
		_caption = caption;
	}

	public void render(ActionContext ac, boolean nested)
	throws javax.servlet.ServletException, IOException {
		if (!isEffective())
			return;

		final Map attrs = new HashMap();
		put(attrs, "align", _align);
		put(attrs, "color", _color);
		put(attrs, "shadow", _shadow);
		put(attrs, "spacing", _spacing);
		put(attrs, "caption", _caption);
		put(attrs, "width", _width);
		put(attrs, "actionContext", ac);

		ac.include("~./dsp/action/html/box.dsp", attrs);
	}

	//-- Object --//
	public String toString() {
		return "box";
	}
}
