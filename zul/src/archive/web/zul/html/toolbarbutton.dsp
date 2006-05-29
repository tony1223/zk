<%--
toolbarbutton.dsp

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Thu Jun 23 16:39:54     2005, Created by tomyeh@potix.com
}}IS_NOTE

Copyright (C) 2005 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
--%><%@ taglib uri="/WEB-INF/tld/web/core.dsp.tld" prefix="c" %>
<c:set var="self" value="${requestScope.arg.self}"/>
<a id="${self.uuid}" zk_type="zul.html.widget.Tbtn" ${self.outerAttrs}${self.innerAttrs}>
<c:choose>
<c:when test="${self.dir == 'reverse'}">
	<c:out value="${self.label}"/><c:if test="${self.imageAssigned and self.orient == 'vertical'}"><br/></c:if>${self.imgTag}
</c:when>
<c:otherwise>
	${self.imgTag}<c:if test="${self.imageAssigned and self.orient == 'vertical'}"><br/></c:if><c:out value="${self.label}"/>
</c:otherwise>
</c:choose>
</a>
