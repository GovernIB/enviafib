<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${peticioFilterForm.contexte}"/>
  <c:set var="formName" value="peticio" />
  <c:set var="__theFilterForm" value="${peticioFilterForm}" />
  <c:if test="${empty peticioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="peticio.peticio"/>
  </c:if>
  <c:if test="${not empty peticioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${peticioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty peticioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="peticio.peticio"/>
  </c:if>
  <c:if test="${not empty peticioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${peticioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.peticio.submit();  
  }
</script>
