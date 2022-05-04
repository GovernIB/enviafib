<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${fitxerFilterForm.contexte}"/>
  <c:set var="formName" value="fitxer" />
  <c:set var="__theFilterForm" value="${fitxerFilterForm}" />
  <c:if test="${empty fitxerFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="fitxer.fitxer"/>
  </c:if>
  <c:if test="${not empty fitxerFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${fitxerFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty fitxerFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="fitxer.fitxer"/>
  </c:if>
  <c:if test="${not empty fitxerFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${fitxerFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.fitxer.submit();  
  }
</script>
