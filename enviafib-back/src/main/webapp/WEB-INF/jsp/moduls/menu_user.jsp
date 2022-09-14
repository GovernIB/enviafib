<%@ page contentType="text/html;charset=UTF-8" language="java"
%>
<%@page import="org.springframework.security.core.Authentication"%><%@page
	import="org.springframework.context.i18n.LocaleContextHolder"%><%@page
	import="org.springframework.security.core.context.SecurityContext"%><%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%><%@page
	import="es.caib.enviafib.back.security.LoginInfo"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5><fmt:message key="user.menu.title"/></h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

   <%-- Example with security: virtual roles  --%>
   <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/user/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>

	<%-- Botó per redirigir a la llista general de peticions--%>
	<%--
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/list"/>">
        <span style="${(fn:contains(url, 'user/peticio/list'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.peticionsusuari"/></span>
      </a>
    </li>
    --%>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/pendents/list"/>">
        <span style="${(fn:contains(url, 'user/peticio/pendents/list'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.peticionsusuari.pendents"/></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/firmades/list"/>">
        <span style="${(fn:contains(url, 'user/peticio/firmades/list'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.peticionsusuari.firmades"/></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/rebutjades/list"/>">
        <span style="${(fn:contains(url, 'user/peticio/rebutjades/list'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.peticionsusuari.rebutjades"/></span>
      </a>
    </li>
    
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/autofirma/new"/>">
        <span style="${(fn:contains(url, '/user/autofirma'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.autofirma"/></span>
      </a>
    </li>
    
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmapernif/new"/>">
        <span style="${(fn:contains(url, '/user/firmapernif/new'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmapernif"/></span>
      </a>
    </li>
   
       
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/flux/crearflux"/>">
        <span style="${(fn:contains(url, '/user/flux'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmaperflux"/></span>
      </a>
    </li>
    
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmadirector/new"/>">
        <span style="${(fn:contains(url, '/firmadirector'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmacarrec.director"/></span>
      </a>
    </li>
   
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmasecretari/new"/>">
        <span style="${(fn:contains(url, '/firmasecretari'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmacarrec.secretari"/></span>
      </a>
    </li>
   
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmaplantillafluxusuari/new"/>">
        <span style="${(fn:contains(url, '/firmaplantillafluxusuari'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmaplantillaflux.usuari"/></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmaplantillafluxentitat/new"/>">
        <span style="${(fn:contains(url, '/firmaplantillafluxentitat'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmaplantillaflux.entitat"/></span>
      </a>
    </li>
    
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
   
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/plantillesfluxfirmes/list"/>">
        <span style="${(fn:contains(url, '/user/plantillesfluxfirmes'))? "font-weight: bold;" : ""}"><fmt:message key="plantillesfluxfirmes.plural"/></span>
      </a>
    </li>
    
    
    <c:if test = "${LoginInfo.getInstance().getRoles() == '[ROLE_USER]'}">
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    	<li style="list-style-type: disc; list-style-position: inside;">
      		<a href="<c:url value="/user/home"/>">
        		<span style="${(fn:contains(url, '/home'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.home"/></span>
      		</a>
    	</li>
    </c:if>
  </ul>
</div>

