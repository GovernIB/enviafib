<%@page import="org.springframework.security.core.Authentication"%><%@page
	import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
	import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@page
	import="es.caib.enviafib.back.security.LoginInfo"%><%@ page
	language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
	
<div class="clear"></div>
<div class="spacer"></div>

<div>
	<br />
	<center>
		<img src="<c:url value="/img/app-logo.png"/>" alt="EnviaFIB"
			title="EnviaFIB" style="width: 125px;"/> <br /> <br />
		<fmt:message key="common.benvinguda.missatge" />

		<br /> <br />
		<table border="0">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td valign="top"><a
					href="http://blog.fundaciobit.org/category/admindigital/"
					target="_blank"> <img
						src="<c:url value="/img/fundaciobit.png"/>" alt="Fundació Bit"
						title="Fundació Bit" />
				</a></td>
			</tr>
		</table>
		<br />
	</center>

</div>

<br />

<c:if test="${efi:isDesenvolupament()}">
<br />
<br />
&#36;{efi:hasRole(ROLE_ADMIN)}= ${efi:hasRole('ROLE_ADMIN')}
<br />
&#36;{efi:hasRole(ROLE_USER) }= ${efi:hasRole('ROLE_USER') }
<br />
Locale =
<%=LocaleContextHolder.getLocale()%>
<br />
lang = ${lang}
<br />
Only in Development Mode


<br />
Username: ${loginInfo.username}
<br />
<br />
> UserInformation:
<br />
<c:if test="${not empty loginInfo.usuari}">
	name= ${loginInfo.usuari.nom} <br /> 
 	surname1= ${loginInfo.usuari.llinatge1} <br />
 	surname2= ${loginInfo.usuari.llinatge2} <br />
 	email= ${loginInfo.usuari.email} <br />
 	nif= ${loginInfo.usuari.nif} <br />
	<br />
</c:if>
<c:if test="${empty loginInfo.usuari}">
	Error carregant Plugin UserInfo. Revisar logs per mes informacio.<br />
</c:if>
</c:if>