
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="avisForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="avisFormTitle.jsp" %>
 
  <c:set var="contexte" value="${avisForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="avisFormCorePre.jsp" %>

  <%@include file="avisFormCore.jsp" %>

  <%@include file="avisFormCorePost.jsp" %>

  <%@include file="avisFormButtons.jsp" %>

  <c:if test="${not empty avisForm.sections}">
     <c:set var="__basename" value="avis" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${avisForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/avisFormModificable.jsp" %>
  </c:if>

</form:form>


