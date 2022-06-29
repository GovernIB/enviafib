<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5>Menú ROLE_USER</h5>
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


    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
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
   
   
  </ul>
</div>

