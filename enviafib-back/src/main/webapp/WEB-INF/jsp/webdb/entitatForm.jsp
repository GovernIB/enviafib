
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="entitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="entitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${entitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="entitatFormCorePre.jsp" %>

  <%@include file="entitatFormCore.jsp" %>

  <%@include file="entitatFormCorePost.jsp" %>

  <%@include file="entitatFormButtons.jsp" %>

  <c:if test="${not empty entitatForm.sections}">
     <c:set var="__basename" value="entitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${entitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/entitatFormModificable.jsp" %>
  </c:if>

</form:form>


