<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${infoArxiuFilterForm.contexte}"/>
  <c:set var="formName" value="infoArxiu" />
  <c:set var="__theFilterForm" value="${infoArxiuFilterForm}" />
  <c:if test="${empty infoArxiuFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="infoArxiu.infoArxiu"/>
  </c:if>
  <c:if test="${not empty infoArxiuFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${infoArxiuFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty infoArxiuFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="infoArxiu.infoArxiu"/>
  </c:if>
  <c:if test="${not empty infoArxiuFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${infoArxiuFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.infoArxiu.submit();  
  }
</script>
