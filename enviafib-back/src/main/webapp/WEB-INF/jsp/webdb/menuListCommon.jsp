<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${menuFilterForm.contexte}"/>
  <c:set var="formName" value="menu" />
  <c:set var="__theFilterForm" value="${menuFilterForm}" />
  <c:if test="${empty menuFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="menu.menu"/>
  </c:if>
  <c:if test="${not empty menuFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${menuFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty menuFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="menu.menu"/>
  </c:if>
  <c:if test="${not empty menuFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${menuFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.menu.submit();  
  }
</script>
