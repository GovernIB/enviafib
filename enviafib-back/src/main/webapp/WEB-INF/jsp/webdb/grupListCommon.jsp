<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${grupFilterForm.contexte}"/>
  <c:set var="formName" value="grup" />
  <c:set var="__theFilterForm" value="${grupFilterForm}" />
  <c:if test="${empty grupFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="grup.grup"/>
  </c:if>
  <c:if test="${not empty grupFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${grupFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty grupFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="grup.grup"/>
  </c:if>
  <c:if test="${not empty grupFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${grupFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.grup.submit();  
  }
</script>
