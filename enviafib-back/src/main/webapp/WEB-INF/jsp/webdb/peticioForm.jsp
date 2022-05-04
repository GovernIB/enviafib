
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="peticioFormTitle.jsp" %>


<form:form modelAttribute="peticioForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${peticioForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="peticioFormCorePre.jsp" %>

  <%@include file="peticioFormCore.jsp" %>

  <%@include file="peticioFormCorePost.jsp" %>

  <%@include file="peticioFormButtons.jsp" %>

  <c:if test="${not empty peticioForm.sections}">
     <c:set var="__basename" value="peticio" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${peticioForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/peticioFormModificable.jsp" %>
  </c:if>

</form:form>


