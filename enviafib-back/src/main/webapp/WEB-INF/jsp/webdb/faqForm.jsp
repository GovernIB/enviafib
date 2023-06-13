
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="faqForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="faqFormTitle.jsp" %>
 
  <c:set var="contexte" value="${faqForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="faqFormCorePre.jsp" %>

  <%@include file="faqFormCore.jsp" %>

  <%@include file="faqFormCorePost.jsp" %>

  <%@include file="faqFormButtons.jsp" %>

  <c:if test="${not empty faqForm.sections}">
     <c:set var="__basename" value="faq" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${faqForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/faqFormModificable.jsp" %>
  </c:if>

</form:form>


