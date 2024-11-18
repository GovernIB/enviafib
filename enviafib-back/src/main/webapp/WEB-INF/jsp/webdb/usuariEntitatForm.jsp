
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="usuariEntitatForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="usuariEntitatFormTitle.jsp" %>
 
  <c:set var="contexte" value="${usuariEntitatForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariEntitatFormCorePre.jsp" %>

  <%@include file="usuariEntitatFormCore.jsp" %>

  <%@include file="usuariEntitatFormCorePost.jsp" %>

  <%@include file="usuariEntitatFormButtons.jsp" %>

  <c:if test="${not empty usuariEntitatForm.sections}">
     <c:set var="__basename" value="usuariEntitat" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariEntitatForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariEntitatFormModificable.jsp" %>
  </c:if>

</form:form>


