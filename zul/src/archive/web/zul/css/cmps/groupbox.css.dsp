<%@ page contentType="text/css;charset=UTF-8" %>
<%@ taglib uri="http://www.zkoss.org/dsp/web/core" prefix="c" %>

.z-fieldset legend{
	font-family: ${fontFamilyC};font-size: ${fontSizeM}; font-weight: normal;
}
.z-fieldset-cnt {
	overflow: hidden;
}
.z-fieldset-collapsed {
	padding-bottom: 0 !important; border-width: 2px 0 0 0 !important;
}
.z-fieldset-collapsed .z-fieldset-cnt {
	visibility: hidden; position: absolute; left: -1000px; top: -1000px;
}

<%-- 3D --%>

.z-groupbox{
	margin:0;
	overflow:hidden;
	padding:0;
}

.z-groupbox-tl {
	background:transparent no-repeat scroll 0 top;
	background-image:url(${c:encodeURL('~./zul/img/groupbox/groupbox-corner.png')});
	font-size:0;
	height:5px;
	line-height:0;
	margin-right:5px;
	zoom:1;
}
.z-groupbox-tr{
	background:transparent no-repeat scroll right -5px;
	background-image:url(${c:encodeURL('~./zul/img/groupbox/groupbox-corner.png')});
	font-size:0;
	height:5px;
	line-height:0;
	margin-right:-5px;
	position:relative;
	zoom:1;
}

.z-groupbox-hl {
	background:transparent repeat-y scroll 0 0;
	background-image:url(${c:encodeURL('~./zul/img/groupbox/groupbox-hl.png')});
	border-bottom:1px solid #B2CCD9;
	padding-left:6px;
	zoom: 1;
}
.z-groupbox-hr {
	background:transparent repeat-y scroll right 0;
	background-image:url(${c:encodeURL('~./zul/img/groupbox/groupbox-hr.png')});
	padding-right:6px;
	zoom: 1;
}
.z-groupbox-hm {
	background:transparent repeat-x scroll 0 0;
	background-image:url(${c:encodeURL('~./zul/img/groupbox/groupbox-hm.png')});
	overflow:hidden;
	zoom: 1;
}
.z-groupbox-header {
	overflow: hidden; 
	zoom: 1;
}
.z-groupbox-hl .z-groupbox-header {
	color: #373737;
	font-family: ${fontFamilyT};
	font-size: ${fontSizeM};
	font-weight: normal;
	padding: 0 0 4px 0;
	border: 0 none; 
	background: transparent;
}

.z-groupbox-cnt {<%-- content of 3d groupbox-new2 --%>
	border: 1px solid #B2CCD9; 
	padding: 5px;
}

.z-groupbox-bl {
	background:transparent url(${c:encodeURL('~./img/shdlf.gif')}) no-repeat 0 bottom;
	padding-left: 6px; 
	zoom: 1;
}
.z-groupbox-br {
	background:transparent url(${c:encodeURL('~./img/shdrg.gif')}) no-repeat right bottom;
	padding-right: 6px; 
	zoom: 1;
}
.z-groupbox-bm {
	background:transparent url(${c:encodeURL('~./img/shdmd.gif')}) repeat-x 0 0;
	height: 6px; 
	font-size: 0; 
	line-height: 0; 
	zoom: 1;
}
