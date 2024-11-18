<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${usuariEntitatFilterForm.contexte}"/>
  <c:set var="formName" value="usuariEntitat" />
  <c:set var="__theFilterForm" value="${usuariEntitatFilterForm}" />
  <c:if test="${empty usuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="usuariEntitat.usuariEntitat"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${usuariEntitatFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty usuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="usuariEntitat.usuariEntitat"/>
  </c:if>
  <c:if test="${not empty usuariEntitatFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${usuariEntitatFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.usuariEntitat.submit();  
  }
</script>
