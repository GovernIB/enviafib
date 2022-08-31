<%@ page contentType="text/html;charset=UTF-8" language="java" 
%><%@page import="java.io.InputStream"
%><%@page import="java.util.Properties"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<c:if test = "${efi:hasRole('ROLE_USER')}">
	<c:redirect url="/user/peticio/pendents/list/1"/>
</c:if>
<c:if test = "${!efi:hasRole('ROLE_USER')}">
	<c:if test = "${efi:hasRole('ROLE_ADMIN')}">
		<c:redirect url="/admin/home"/>
	</c:if>
</c:if>

</body>
</html>