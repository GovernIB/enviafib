<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariFilterForm.contexte}"/>
  <c:set var="formName" value="usuari" />
  <c:set var="__theFilterForm" value="${usuariFilterForm}" />
  <c:if test="${empty usuariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuari.usuari"/>
  </c:if>
  <c:if test="${not empty usuariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuari.usuari"/>
  </c:if>
  <c:if test="${not empty usuariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuari.submit();  
  }
</script>
