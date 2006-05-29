/* AuResponse.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue May 31 11:35:49     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package com.potix.zk.au;

import java.util.Map;
import java.util.HashMap;

import com.potix.zk.ui.Component;
import com.potix.zk.ui.Page;

/**
 * A response sent from the server to the client via
 * {@link com.potix.zk.ui.sys.UiEngine}.
 *
 * <p>Application developers rarely need access this class and its derived
 * directly.
 * Rather, use {@link com.potix.zk.ui.util.Clients} instead.
 * If you prefer to use the derives directly, you can use them with
 * {@link com.potix.zk.ui.Execution#addAuResponse}.
 *
 * @author <a href="mailto:tomyeh@potix.com">tomyeh@potix.com</a>
 * @version $Revision: 1.5 $ $Date: 2006/05/29 04:27:56 $
 */
public class AuResponse {
	private final String _cmd;
	private final Object _depends;
	private final String[] _data;

	/** Constructs a component-independent response.
	 */
	protected AuResponse(String cmd) {
		this(cmd, (Component)null, (String[])null);
	}
	/** Constructs a component-independent response.
	 */
	protected AuResponse(String cmd, String data) {
		this(cmd, (Component)null, data);
	}
	/** Constructs a component-independent response.
	 */
	protected AuResponse(String cmd, String[] data) {
		this(cmd, (Component)null, data);
	}
	/** Constructs a response with one or zero data.
	 *
	 * @param depends specified whether this response depends on whether
	 * the depends component.
	 * If depends is not null, this response shall be purged if the depends
	 * component is removed.
	 * If null, this response is called component-independent, and
	 * always sent to the client.
	 *
	 * <p>Note: info of the depends component doesn't send to the client.
	 * It is used only to optimize what responses to send.
	 */
	protected AuResponse(String cmd, Component depends, String data) {
		this(cmd, depends, data != null ? new String[] {data}: null);
	}
	/** Constructs a response with multiple data.
	 */
	protected AuResponse(String cmd, Component depends, String[] data) {
		if (cmd == null || cmd.length() == 0)
			throw new IllegalArgumentException("cmd");
		_cmd = cmd;
		_depends = depends;
		_data = data;
	}
	/** Constructs a response with multiple data.
	 */
	protected AuResponse(String cmd, Page depends, String data) {
		this(cmd, depends,  data != null ? new String[] {data}: null);
	}
	/** Constructs a response with multiple data.
	 */
	protected AuResponse(String cmd, Page depends, String[] data) {
		if (cmd == null || cmd.length() == 0)
			throw new IllegalArgumentException("cmd");
		_cmd = cmd;
		_depends = depends;
		_data = data;
	}

	/** Returns the command of this response (never null).
	 */
	public final String getCommand() {
		return _cmd;
	}
	/** Returns the associated data of this response (might be null).
	 */
	public final String[] getData() {
		return _data;
	}

	/** Returns the component or page that this response depends on.
	 * If it is not null and the depends component/page is removed,
	 * this response shall be removed, too.
	 *
	 * <p>Note: the returned object is either a {@link Component} or a
	 * {@link Page}.
	 */
	public final Object getDepends() {
		return _depends;
	}

	//-- Object --//
	public final boolean equals(Object o) { //prevent override
		return this == o;
	}
	public String toString() {
		final StringBuffer sb =
			new StringBuffer(60).append("[cmd=").append(_cmd);
		if (_data != null && _data.length > 0) {
			sb.append(", data0=").append(_data[0]);
			if (_data.length > 1 && (_data[1] == null || _data[1].length() < 20)) {
				sb.append(", data1=").append(_data[1]);
				if (_data.length > 2 && (_data[2] == null || _data[2].length() < 20))
					sb.append(", data2=").append(_data[2]);
			}
		}
		return sb.append(']').toString();
	}
}
