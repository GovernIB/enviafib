<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${grupUsuariFilterForm.contexte}"/>
  <c:set var="formName" value="grupUsuari" />
  <c:set var="__theFilterForm" value="${grupUsuariFilterForm}" />
  <c:if test="${empty grupUsuariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="grupUsuari.grupUsuari"/>
  </c:if>
  <c:if test="${not empty grupUsuariFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupUsuariFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty grupUsuariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="grupUsuari.grupUsuari"/>
  </c:if>
  <c:if test="${not empty grupUsuariFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${grupUsuariFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.grupUsuari.submit();  
  }
</script>
