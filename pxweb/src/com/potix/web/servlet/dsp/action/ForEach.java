/* ForEach.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Sep  6 15:33:11     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.web.servlet.dsp.action;

import java.util.Collection;
import java.util.Map;
import java.util.Iterator;
import java.util.Enumeration;
import java.io.StringWriter;
import java.io.IOException;

import com.potix.web.mesg.MWeb;
import com.potix.web.servlet.ServletException;

/**
 * Iterators thru a collection/array of items.
 * 
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.8 $ $Date: 2006/05/29 04:27:40 $
 */
public class ForEach extends AbstractAction {
	private String _var, _varStatus;
	private Object _items;
	private boolean _trim = true;

	/** Returns the variable name used to iterate thru items. */
	public String getVar() {
		return _var;
	}
	/** Sets the variable name used to iterate thru items. */
	public void setVar(String var) {
		_var = var;
	}
	/** Returns the variable name used to hold the current iteration
	 * status, an instance of {@link LoopStatus}.
	 */
	public String getVarStatus() {
		return _varStatus;
	}
	/** Sets the variable name used to hold the current iteration status.
	 */
	public void setVarStatus(String varStatus) {
		_varStatus = varStatus;
	}
	/** Returns the attribute items. */
	public Object getItems() {
		return _items;
	}
	/** Sets the attribute items. */
	public void setItems(Object items) {
		_items = items;
	}

	/** Returns whether to trim the result. */
	public boolean isTrim() {
		return _trim;
	}
	/** Sets whether to trim the result.
	 * <p>Default: true.
	 */
	public void setTrim(boolean trim) {
		_trim = trim;
	}

	//-- Action --//
	public void render(ActionContext ac, boolean nested)
	throws javax.servlet.ServletException, IOException {
		if (!nested || _items == null || !isEffective())
			return;

		final Object old1 =
			_var != null ? ac.getAttribute(_var, ac.PAGE_SCOPE): null;
		final Object old2;
		final Status st;
		if (_varStatus != null) {
			old2 = ac.getAttribute(_varStatus, ac.PAGE_SCOPE);
			ac.setAttribute(_varStatus, st = new Status(), ac.PAGE_SCOPE);
		} else {
			old2 = null;
			st = null;
		}

		if (_items.getClass().isArray()) {
			if (_items instanceof Object[])
				renderWith(ac, st, (Object[])_items);
			else if (_items instanceof int[])
				renderWith(ac, st, (int[])_items);
			else if (_items instanceof short[])
				renderWith(ac, st, (short[])_items);
			else if (_items instanceof long[])
				renderWith(ac, st, (long[])_items);
			else if (_items instanceof byte[])
				renderWith(ac, st, (byte[])_items);
			else if (_items instanceof char[])
				renderWith(ac, st, (char[])_items);
			else if (_items instanceof double[])
				renderWith(ac, st, (double[])_items);
			else if (_items instanceof float[])
				renderWith(ac, st, (float[])_items);
			else
				throw new InternalError("Unknown "+_items.getClass());
		} else if (_items instanceof Collection) {
			renderWith(ac, st, ((Collection)_items).iterator());
		} else if (_items instanceof Map) {
			renderWith(ac, st, ((Map)_items).entrySet().iterator());
		} else if (_items instanceof Iterator) {
			renderWith(ac, st, (Iterator)_items);
		} else if (_items instanceof Enumeration) {
			renderWith(ac, st, (Enumeration)_items);
		} else if (_items instanceof String) {
			renderWith(ac, st, (String)_items);
		} else {
			throw new ServletException(MWeb.DSP_UNKNOWN_ATTRIBUTE_VALUE,
				new Object[] {this, "items", new Integer(ac.getLineNumber())});
		}

		if (_var != null) ac.setAttribute(_var, old1, ac.PAGE_SCOPE);
		if (_varStatus != null) ac.setAttribute(_varStatus, old2, ac.PAGE_SCOPE);
	}

	private void renderWith(ActionContext ac, Status st, Iterator it)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; it.hasNext(); ++j) {
			final Object val = it.next();
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, Enumeration enm)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; enm.hasMoreElements(); ++j) {
			final Object val = enm.nextElement();
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, Object[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = ary[j];
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, int[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Integer(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, short[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Short(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, long[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Long(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, char[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Character(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, byte[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Byte(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, float[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Float(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, double[] ary)
	throws javax.servlet.ServletException, IOException {
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0; j < ary.length; ++j) {
			final Object val = new Double(ary[j]);
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(j, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}
	private void renderWith(ActionContext ac, Status st, String txt)
	throws javax.servlet.ServletException, IOException {
		final StringBuffer sb = new StringBuffer();
		int idx = 0;
		final StringWriter out = _trim ? new StringWriter(): null;
		for (int j = 0, len = txt.length(); j < len; ++j) {
			char cc = txt.charAt(j);
			if (cc == ',') {
				final Object val = sb.toString();
				if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
				if (st != null) st.update(idx++, val);
				ac.renderFragment(out);
				sb.setLength(0);
			} else if (cc == '\\' && j + 1 < len) {
				cc = txt.charAt(j + 1);
				switch (cc) {
				case 'n': cc = '\n'; break;
				case 'r': cc = '\r'; break;
				case 't': cc = '\t'; break;
				case 'b': cc = '\b'; break;
				}
			}
			sb.append(cc);
		}
		if (sb.length() > 0) {
			final Object val = sb.toString();
			if (_var != null) ac.setAttribute(_var, val, ac.PAGE_SCOPE);
			if (st != null) st.update(idx++, val);
			ac.renderFragment(out);
		}
		if (out != null)
			ac.getOut().write(out.toString().trim());
	}

	//-- Object --//
	public String toString() {
		return "forEach";
	}
	private static class Status implements LoopStatus {
		private int _j;
		private Object _cur;
		public int getIndex() {
			return _j;
		}
		public Object getCurrent() {
			return _cur;
		}
		private void update(int j, Object cur) {
			_j = j;
			_cur = cur;
		}
	}
}
