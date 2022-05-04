<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${pluginFilterForm.contexte}"/>
  <c:set var="formName" value="plugin" />
  <c:set var="__theFilterForm" value="${pluginFilterForm}" />
  <c:if test="${empty pluginFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="plugin.plugin"/>
  </c:if>
  <c:if test="${not empty pluginFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${pluginFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty pluginFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="plugin.plugin"/>
  </c:if>
  <c:if test="${not empty pluginFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${pluginFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.plugin.submit();  
  }
</script>
