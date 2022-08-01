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


    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/list"/>">
        <span style="${(fn:contains(url, 'user/peticio/list'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.peticionsusuari"/></span>
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
        <span style="${(fn:contains(url, '/firmadirector'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmadirector"/></span>
      </a>
    </li>
   
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/firmasecretari/new"/>">
        <span style="${(fn:contains(url, '/firmasecretari'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.firmasecretari"/></span>
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
      		<a href="<c:url value="/user/peticio/home"/>">
        		<span style="${(fn:contains(url, '/home'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.home"/></span>
      		</a>
    	</li>
    </c:if>
   
         <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
 
      <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/grup/list"/>">
        <span style="${(fn:contains(url, '/grup'))? "font-weight: bold;" : ""}"><fmt:message key="user.menu.grup"/></span>
      </a>
    </li>
    
  </ul>
</div>

