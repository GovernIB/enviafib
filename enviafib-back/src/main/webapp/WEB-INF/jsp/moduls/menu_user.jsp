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
        <span style="${(fn:contains(url, 'user/peticio/list'))? "font-weight: bold;" : ""}">Peticions d'usuari XYZ ZZZ</span>
      </a>
    </li>
    
    
        <hr  style="margin-top: 6px;  margin-bottom: 6px;" />
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/autofirma/new"/>">
        <span style="${(fn:contains(url, '/user/autofirma/new'))? "font-weight: bold;" : ""}">Autofirma XYZ ZZZ</span>
      </a>
    </li>
    
    
    <li style="list-style-type: disc; list-style-position: inside;">
      <a href="<c:url value="/user/peticio/new"/>">
        <span style="${(fn:contains(url, 'user/peticio/new'))? "font-weight: bold;" : ""}">Enviar firma per NIF</span>
      </a>
    </li>
    
    
    

   
  </ul>
</div>

