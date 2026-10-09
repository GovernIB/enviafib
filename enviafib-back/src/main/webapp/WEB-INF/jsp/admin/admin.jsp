<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<h1 style="text-align: center;">Option Page ${optionNumber}</h1>

<div style="text-align: center;"><img src="<c:url value="/img/icn_alert_success.png"/>"  alt="exemple" title="exemple"/></div>