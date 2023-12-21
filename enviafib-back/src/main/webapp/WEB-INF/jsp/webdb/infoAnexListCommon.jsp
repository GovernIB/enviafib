<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${infoAnexFilterForm.contexte}"/>
  <c:set var="formName" value="infoAnex" />
  <c:set var="__theFilterForm" value="${infoAnexFilterForm}" />
  <c:if test="${empty infoAnexFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="infoAnex.infoAnex"/>
  </c:if>
  <c:if test="${not empty infoAnexFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${infoAnexFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty infoAnexFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="infoAnex.infoAnex"/>
  </c:if>
  <c:if test="${not empty infoAnexFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${infoAnexFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.infoAnex.submit();  
  }
</script>
