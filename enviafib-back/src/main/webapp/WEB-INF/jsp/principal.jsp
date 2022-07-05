<%@page import="org.springframework.security.core.Authentication"%><%@page
	import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
	import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@ page
	language="java"%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<div class="clear"></div>
<div class="spacer"></div>

<div>
	<br />
	<center>
		<img src="<c:url value="/img/app-logo.png"/>" alt="EnviaFIB"
			title="EnviaFIB" /> <br /> <br /> This page is generated
		automatically. Please edit. <br /> <br />
		<table border="0">
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td valign="top"><a
					href="http://blog.fundaciobit.org/category/admindigital/"
					target="_blank"> <img
						src="<c:url value="/img/https://www.caib.es/sites/manualdidentitatcorporativa/es/govern_de_les_illes_balears/archivopub.do?ctrl=MCRST9545ZI292840&id=292840"/>" alt="Fundaci� Bit"
						title="Fundació Bit" />
				</a></td>
			</tr>
		</table>
		<br />
	</center>

</div>

<br />
Username: ${loginInfo.username}
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
> UserInformation:
<br />
<c:if test="${not empty loginInfo.userInfo}">
	name= ${loginInfo.userInfo.name} <br /> 
 	surname1= ${loginInfo.userInfo.surname1} <br />
 	surname2= ${loginInfo.userInfo.surname2} <br />
 	email= ${loginInfo.userInfo.email} <br />
 	nif= ${loginInfo.userInfo.attributes["nif"]} <br />
	<br />
</c:if>
<c:if test="${empty loginInfo.userInfo}">
	Error carregant Plugin UserInfo. Revisar logs per mes informacio.<br />
</c:if>

<c:if test="${efi:isDesenvolupament()}">
Only in Development Mode
</c:if>
