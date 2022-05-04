
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="usuariFormTitle.jsp" %>


<form:form modelAttribute="usuariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${usuariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="usuariFormCorePre.jsp" %>

  <%@include file="usuariFormCore.jsp" %>

  <%@include file="usuariFormCorePost.jsp" %>

  <%@include file="usuariFormButtons.jsp" %>

  <c:if test="${not empty usuariForm.sections}">
     <c:set var="__basename" value="usuari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${usuariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/usuariFormModificable.jsp" %>
  </c:if>

</form:form>


