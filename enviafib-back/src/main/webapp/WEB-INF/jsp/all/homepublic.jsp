<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br/>
<center>
<img src="<c:url value="/img/app-logo.png"/>"  alt="EnviaFIB" />

<br/>
<br/>

PAGINA PUBLICA <br/>

This page is generated automatically. Please edit.

<br/>
<br/>
<div>
<a href="https://governdigital.fundaciobit.org/" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="IBDigital" />
</a>
</div>
<br/>
</center>
 
</div>

<br/>

LOGIN ANONIM <br/>
Locale = <%=LocaleContextHolder.getLocale() %> <br/>
lang = ${lang} <br/>
<br/>

<c:if test="${efi:isDesenvolupament()}">
Only in Development Mode
</c:if>
