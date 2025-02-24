<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${avisFilterForm.contexte}"/>
  <c:set var="formName" value="avis" />
  <c:set var="__theFilterForm" value="${avisFilterForm}" />
  <c:if test="${empty avisFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="avis.avis"/>
  </c:if>
  <c:if test="${not empty avisFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${avisFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty avisFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="avis.avis"/>
  </c:if>
  <c:if test="${not empty avisFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${avisFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.avis.submit();  
  }
</script>
