<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

  <c:set var="contexte" value="${faqFilterForm.contexte}"/>
  <c:set var="formName" value="faq" />
  <c:set var="__theFilterForm" value="${faqFilterForm}" />
  <c:if test="${empty faqFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="faq.faq"/>
  </c:if>
  <c:if test="${not empty faqFilterForm.entityNameCode}">
    <fmt:message var="entityname" key="${faqFilterForm.entityNameCode}"/>
  </c:if>
  <c:if test="${empty faqFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="faq.faq"/>
  </c:if>
  <c:if test="${not empty faqFilterForm.entityNameCodePlural}">
    <fmt:message var="entitynameplural" key="${faqFilterForm.entityNameCodePlural}"/>
  </c:if>
  <%-- HIDDEN PARAMS: ORDER BY --%> 
  <form:hidden id="orderBy" path="orderBy"/> 
  <form:hidden id="orderAsc" path="orderAsc"/>

  <form:hidden path="nou" value="false"/>

<script type="text/javascript">
  function executeOrderBy(orderBy, orderType) {
    document.getElementById('orderBy').value = orderBy;
    document.getElementById('orderAsc').value = orderType;
    document.faq.submit();  
  }
</script>
