<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${organitzacioFilterForm.contexte}"/>
  <c:set var="formName" value="organitzacio" />
  <c:set var="__theFilterForm" value="${organitzacioFilterForm}" />
  <c:if test="${empty organitzacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="organitzacio.organitzacio"/>
  </c:if>
  <c:if test="${not empty organitzacioFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${organitzacioFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty organitzacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="organitzacio.organitzacio"/>
  </c:if>
  <c:if test="${not empty organitzacioFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${organitzacioFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.organitzacio.submit();  
  }
</script>
