/* AuAppendChild.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Oct 13 11:33:16     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zk.au;

import com.potix.zk.ui.Component;
import com.potix.zk.ui.Page;

/**
 * A response to insert an unparsed HTML as the last child of
 * the specified component at the client.
 *
 * <p>data[0]: the uuid of the component/page as the parent<br>
 * data[1]: the unparsed HTML (aka., content)
 * 
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.3 $ $Date: 2006/05/29 04:27:54 $
 */
public class AuAppendChild extends AuResponse {
	public AuAppendChild(Component comp, String content) {
		super("addChd", comp, new String[] {comp.getUuid(), content});
	}
	public AuAppendChild(Page page, String content) {
		super("addChd", page, new String[] {page.getId(), content});
	}
}
