<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${infoCustodyFilterForm.contexte}"/>
  <c:set var="formName" value="infoCustody" />
  <c:set var="__theFilterForm" value="${infoCustodyFilterForm}" />
  <c:if test="${empty infoCustodyFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="infoCustody.infoCustody"/>
  </c:if>
  <c:if test="${not empty infoCustodyFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${infoCustodyFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty infoCustodyFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="infoCustody.infoCustody"/>
  </c:if>
  <c:if test="${not empty infoCustodyFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${infoCustodyFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.infoCustody.submit();  
  }
</script>
