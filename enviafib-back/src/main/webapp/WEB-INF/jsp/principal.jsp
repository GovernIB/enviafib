<%@page import="org.springframework.security.core.Authentication"
%><%@page import="org.springframework.context.i18n.LocaleContextHolder"
%><%@page import="org.springframework.security.core.context.SecurityContext"
%><%@page import="org.springframework.security.core.context.SecurityContextHolder"
%><%@ page language="java" 
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp" 
%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
<br>
<div style="text-align: center;">
<img src="<c:url value="/img/app-logo.png"/>"  alt="EnviaFIB" />

<br>
<br>
This page is generated automatically. Please edit.

<br>
<br>
<div>
<a href="https://governdigital.fundaciobit.org" target="_blank">
<img src="<c:url value="/img/fundaciobit.png"/>"  alt="IBDigital" />
</a>
</div>
<br>
</div>
 
</div>

<br>
<c:if test="${efi:isDesenvolupament()}">

<b>Only in Development Mode</b><br>


Username: ${loginInfo.username}<br>
&#36;{efi:hasRole(ROLE_ADMIN)}= ${efi:hasRole('ROLE_ADMIN')}<br>
&#36;{efi:hasRole(ROLE_USER) }= ${efi:hasRole('ROLE_USER') }<br>
Locale = <%=LocaleContextHolder.getLocale() %> <br>
lang = ${lang} <br>
> UserInformation:<br>
 <c:if test="${not empty loginInfo.usuari}">
	name= ${loginInfo.usuari.nom} <br> 
 	surname1= ${loginInfo.usuari.llinatge1} <br>
 	surname2= ${loginInfo.usuari.llinatge2} <br>
 	email= ${loginInfo.usuari.email} <br>
 	nif= ${loginInfo.usuari.nif} <br> 
  <br>
</c:if>
<c:if test="${empty loginInfo.usuari}">
	Error carregant Plugin UserInfo. Revisar logs per mes informacio.<br>
</c:if>



</c:if>
