<%--
rows.dsp

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Tue Oct 25 17:06:05     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="/WEB-INF/tld/web/core.dsp.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/tld/zk/core.dsp.tld" prefix="u" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<tbody id="${self.uuid}"${self.outerAttrs}${self.innerAttrs}>
	<c:forEach var="child" items="${self.children}">
	${u:redraw(child, null)}
	</c:forEach>
</tbody>
