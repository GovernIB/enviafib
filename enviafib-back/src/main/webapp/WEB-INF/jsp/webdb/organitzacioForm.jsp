
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="organitzacioFormTitle.jsp" %>


<form:form modelAttribute="organitzacioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${organitzacioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="organitzacioFormCorePre.jsp" %>

  <%@include file="organitzacioFormCore.jsp" %>

  <%@include file="organitzacioFormCorePost.jsp" %>

  <%@include file="organitzacioFormButtons.jsp" %>

  <c:if test="${not empty organitzacioForm.sections}">
     <c:set var="__basename" value="organitzacio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${organitzacioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/organitzacioFormModificable.jsp" %>
  </c:if>

</form:form>


