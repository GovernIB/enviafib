<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<c:set var="url" value="${urlActual}" />
<div>
  <h5>Menú ROLE_ADMIN</h5>
  <ul class="tree" style="margin: 3px; padding: 0px;">

   <%-- Example with security: virtual roles  --%>
   <%--
   <sec:authorize access="hasAnyRole('ROLE_SOLI', 'ROLE_DEST', 'ROLE_COLA', 'ROLE_DELE')">
      <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
      <li style="list-style-type: disc; list-style-position: inside;">
       <a href="<c:url value="/admin/rebreAvis/list/1"/>" >
       <span style="${(fn:contains(url, 'optionxxxxx/') && fn:contains(url, '/list'))? "font-weight: bold;" : ""}" >
       Option XXXXX</span></a></li>
   </sec:authorize>
    --%>

    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/usuari/list"/>">
        <span style="${(fn:contains(url, 'admin/usuari/list'))? "font-weight: bold;" : ""}">Llistat d'usuaris</span>
      </a>
    </li>
    
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/serieDocumental/list"/>">
        <span style="${(fn:contains(url, '/admin/serieDocumental/list'))? "font-weight: bold;" : ""}">Sèries documentals</span>
      </a>
    </li>
   
    <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/EstructuraOrganitzativaPlugin/list"/>">
        <span style="${(fn:contains(url, '/admin/EstructuraOrganitzativaPlugin/list'))? "font-weight: bold;" : ""}">Plugins Estructura Organitzativa</span>
      </a>
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/arxiuplugin/list"/>">
        <span style="${(fn:contains(url, '/admin/arxiuplugin/list'))? "font-weight: bold;" : ""}">Plugins Arxiu </span>
      </a>
    </li>
   
         <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
 
      <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/grup/list"/>">
        <span style="${(fn:contains(url, '/grup'))? "font-weight: bold;" : ""}"><fmt:message key="admin.menu.grup"/></span>
      </a>
    </li>
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/plantillesfluxfirmes/list"/>">
        <span style="${(fn:contains(url, '/admin/plantillesfluxfirmes'))? "font-weight: bold;" : ""}"><fmt:message key="plantillesfluxfirmes.admin.plural"/></span>
      </a>
    </li>

    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/admin/netejarplantilles/list"/>">
        <span style="${(fn:contains(url, '/admin/netejarplantilles/list'))? "font-weight: bold;" : ""}"><fmt:message key="plantillesfluxfirmes.obsolet.plural"/></span>
      </a>
    </li>

  </ul>
</div>

