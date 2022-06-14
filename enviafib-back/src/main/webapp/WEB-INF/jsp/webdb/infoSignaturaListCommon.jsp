<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${infoSignaturaFilterForm.contexte}"/>
  <c:set var="formName" value="infoSignatura" />
  <c:set var="__theFilterForm" value="${infoSignaturaFilterForm}" />
  <c:if test="${empty infoSignaturaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="infoSignatura.infoSignatura"/>
  </c:if>
  <c:if test="${not empty infoSignaturaFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${infoSignaturaFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty infoSignaturaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="infoSignatura.infoSignatura"/>
  </c:if>
  <c:if test="${not empty infoSignaturaFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${infoSignaturaFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.infoSignatura.submit();  
  }
</script>
