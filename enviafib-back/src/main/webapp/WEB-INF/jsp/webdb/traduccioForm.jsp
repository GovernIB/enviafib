
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="traduccioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="traduccioFormTitle.jsp" %>
 
  <c:set var="contexte" value="${traduccioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="traduccioFormCorePre.jsp" %>

  <%@include file="traduccioFormCore.jsp" %>

  <%@include file="traduccioFormCorePost.jsp" %>

  <%@include file="traduccioFormButtons.jsp" %>

  <c:if test="${not empty traduccioForm.sections}">
     <c:set var="__basename" value="traduccio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${traduccioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/traduccioFormModificable.jsp" %>
  </c:if>

</form:form>


