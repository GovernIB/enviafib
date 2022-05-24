<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${serieDocumentalFilterForm.contexte}"/>
  <c:set var="formName" value="serieDocumental" />
  <c:set var="__theFilterForm" value="${serieDocumentalFilterForm}" />
  <c:if test="${empty serieDocumentalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="serieDocumental.serieDocumental"/>
  </c:if>
  <c:if test="${not empty serieDocumentalFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${serieDocumentalFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty serieDocumentalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="serieDocumental.serieDocumental"/>
  </c:if>
  <c:if test="${not empty serieDocumentalFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${serieDocumentalFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.serieDocumental.submit();  
  }
</script>
