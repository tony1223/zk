/* DesktopCacheProvider.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Apr 18 10:54:20     2006, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zk.ui.sys;

import com.potix.zk.ui.WebApp;
import com.potix.zk.ui.Session;

/**
 * Used to retrieve a desktop cache ({@link DesktopCache}).
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.2 $ $Date: 2006/05/29 04:28:09 $
 */
public interface DesktopCacheProvider {
	/** Returns a desktop cache of the specified session.
	 *
	 * <p>The implementation might ignore session and use a global cache
	 * instead. Moreover, it might implement a cache supporting clustering.
	 */
	public DesktopCache getCache(Session session);
	/** Called when a session is destroyed.
	 * If the provider is session-based, it has to remove relevant desktops.
	 * If not, it might ignore this method.
	 *
	 * <p>Application shall never access this method.
	 */
	public void sessionDestroyed(Session session);

	/** Starts the provider.
	 */
	public void start(WebApp wapp);
	/** Stops the provider.
	 */
	public void stop(WebApp wapp);
}
