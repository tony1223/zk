/* ELNode.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Sat Sep 17 15:47:54     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2004 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.web.servlet.dsp.impl;

import java.io.Writer;
import java.io.IOException;
import javax.servlet.jsp.el.ELException;

import com.potix.util.logging.Log;
import com.potix.web.servlet.ServletException;

/**
 * Represents an expression.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.7 $ $Date: 2006/05/29 04:27:41 $
 */
class ELNode extends Node {
	private static final Log log = Log.lookup(ELNode.class);
	private final String _expr;

	ELNode(String expr) {
		_expr = expr;
	}

	//-- super --//
	void interpret(InterpretContext ic)
	throws javax.servlet.ServletException, IOException {
		try {
			final String result = (String)ic.dc.getExpressionEvaluator()
				.evaluate(_expr, String.class, ic.resolver, ic.mapper);
			if (result != null)
				ic.dc.getOut().write(result);
		} catch (ELException ex) {
			log.realCause(ex);
			throw new ServletException("Unable to evaluate an EL expression: "+_expr, ex);
		}
	}
	void addChild(Node node) {
		throw new IllegalStateException("No child allowed");
	}

	public String toString() {
		return "EL[" + _expr + ']';
	}
}
