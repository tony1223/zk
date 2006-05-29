/* Slider.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Sep 29 20:16:03     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zul.html;

import com.potix.xml.HTMLs;

import com.potix.zk.ui.WrongValueException;
import com.potix.zk.ui.ext.Scrollable;

import com.potix.zul.html.impl.XulElement;

/**
 * A slider.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.10 $ $Date: 2006/05/29 04:28:27 $
 */
public class Slider extends XulElement implements Scrollable {
	private int _curpos, _maxpos = 100, _pginc = 10;

	public Slider() {
		setWidth("100px");
	}

	/** Returns the current position of the slider.
	 *
	 * <p>Default: 0.
	 */
	public final int getCurpos() {
		return _curpos;
	}
	/** Sets the current position of the slider.
	 */
	public final void setCurpos(int curpos)
	throws WrongValueException {
		if (curpos < 0)
			throw new WrongValueException("Negative is not allowed: "+curpos);
		if (_curpos != curpos) {
			_curpos = curpos;
			smartUpdate("zk_curpos", _curpos);
		}
	}

	/** Returns the maximum position of the slider.
	 *
	 * <p>Default: 100.
	 */
	public final int getMaxpos() {
		return _maxpos;
	}
	/** Sets the maximum position of the slider.
	 */
	public final void setMaxpos(int maxpos)
	throws WrongValueException {
		if (maxpos <= 0)
			throw new WrongValueException("Nonpositive is not allowed: "+maxpos);
		if (_maxpos != maxpos) {
			_maxpos = maxpos;
			smartUpdate("zk_maxpos", _maxpos);
		}
	}

	/** Returns the amount that the value of {@link #getCurpos}
	 * changes by when the tray of the scroll bar is clicked. 
	 *
	 * <p>Default: 10.
	 */
	public final int getPageIncrement() {
		return _pginc;
	}
	/** Sets the amount that the value of {@link #getCurpos}
	 * changes by when the tray of the scroll bar is clicked.
	 */
	public final void setPageIncrement(int pginc)
	throws WrongValueException {
		if (pginc <= 0)
			throw new WrongValueException("Nonpositive is not allowed: "+pginc);
		if (_pginc != pginc) {
			_pginc = pginc;
			smartUpdate("zk_pginc", _pginc);
		}
	}

	//-- Scrollable --//
	public final void setCurposByClient(int curpos) {
		if (curpos < 0)
			throw new WrongValueException("Negative is not allowed: "+curpos);
		_curpos = curpos;
	}

	//-- Component --//
	public String getOuterAttrs() {
		final StringBuffer sb =
			new StringBuffer(64).append(super.getOuterAttrs());
		HTMLs.appendAttribute(sb, "zk_curpos", _curpos);
		HTMLs.appendAttribute(sb, "zk_maxpos", _maxpos);
		HTMLs.appendAttribute(sb, "zk_pginc", _pginc);
		if (isAsapRequired("onScroll"))
			HTMLs.appendAttribute(sb, "zk_onScroll", true);
		if (isAsapRequired("onScrolling"))
			HTMLs.appendAttribute(sb, "zk_onScrolling", true);
		return sb.toString();
	}

	//-- Component --//
	/** Not childable. */
	public boolean isChildable() {
		return false;
	}
}
