<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<center><h1>Option Page ${optionNumber}</h1></center>

<center><img src="<c:url value="/img/icn_alert_success.png"/>"  alt="exemple" title="exemple"/></center>