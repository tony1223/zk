/* OperationException.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Oct 14 18:23:42     2003, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2003 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.web.servlet.jsp;

import com.potix.lang.Expectable;

/**
 * The expectable jsp tag exception denoting user's operation errors.
 * 
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.3 $ $Date: 2006/05/29 04:27:43 $
 */
public class OperationException extends JspTagException implements Expectable {
	public OperationException(String msg, Throwable cause) {
		super(msg, cause);
	}
	public OperationException(String s) {
		super(s);
	}
	public OperationException(Throwable cause) {
		super(cause);
	}
	public OperationException() {
	}

	public OperationException(int code, Object[] fmtArgs, Throwable cause) {
		super(code, fmtArgs, cause);
	}
	public OperationException(int code, Object fmtArg, Throwable cause) {
		super(code, fmtArg, cause);
	}
	public OperationException(int code, Object[] fmtArgs) {
		super(code, fmtArgs);
	}
	public OperationException(int code, Object fmtArg) {
		super(code, fmtArg);
	}
	public OperationException(int code, Throwable cause) {
		super(code, cause);
	}
	public OperationException(int code) {
		super(code);
	}
}
