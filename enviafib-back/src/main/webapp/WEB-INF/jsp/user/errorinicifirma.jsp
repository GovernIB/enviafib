<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>
<div class="col text-center mt-5 pt-5">
	<center class="alert alert-danger ml-5 pl-5 mr-5 pr-5 " role="alert"><h2><fmt:message key="error.generic.missatge"/> ${errorMsg}</h2></center>
	<a class="btn btn-primary mt-4" style="width: 250px" href="<c:url value = "${tornarUrl}"/>" role="button"><fmt:message key="tornar"/></a>
</div>

<div class="col text-center mt-5 pt-5 mb-5 pb-5  ml-5 pl-5 mr-5 pr-5 justify-content-center align-items-center">
</div>