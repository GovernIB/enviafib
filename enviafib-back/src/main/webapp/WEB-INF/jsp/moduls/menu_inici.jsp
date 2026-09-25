<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
   <span style="font-size: 1.25rem"><fmt:message key="menuinici" /></span>
  <ul class="tree" style="margin: 3px; padding: 0px;">
  
	<c:if test="${empty loginInfo}">
        <li style="list-style-type: disc; list-style-position: inside;">
          <a href="<c:url value="/common/home.html"/>">
            <span style="${(fn:contains(url, 'home'))? "font-weight: bold;" : ""}"><fmt:message key="inici.menu.about"/></span>
          </a>
        </li>
    </c:if>
    
    <c:if test="${not empty loginInfo}">

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/common/home" />">
        <span style="${(fn:contains(url, 'home'))? "font-weight: bold;" : ""}">Pàgina Inicial</span>
      </a>
    </li>

   <%-- Example with security: virtual roles  --%>
   <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/common/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>

	    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
	    <li style="list-style-type: disc; list-style-position: inside;">
	      <a href="<c:url value="/common/usuari/${loginInfo.usuari.usuariID}/edit"/>">
	        <span style="${(fn:contains(url, 'common/usuari/edit'))? "font-weight: bold;" : ""}"><fmt:message key="inici.menu.editar.usuari"/></span>
	      </a>
	    </li>
   </c:if>
  </ul>
</div>

