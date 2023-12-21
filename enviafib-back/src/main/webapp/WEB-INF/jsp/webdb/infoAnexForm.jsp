
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="infoAnexForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="infoAnexFormTitle.jsp" %>
 
  <c:set var="contexte" value="${infoAnexForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="infoAnexFormCorePre.jsp" %>

  <%@include file="infoAnexFormCore.jsp" %>

  <%@include file="infoAnexFormCorePost.jsp" %>

  <%@include file="infoAnexFormButtons.jsp" %>

  <c:if test="${not empty infoAnexForm.sections}">
     <c:set var="__basename" value="infoAnex" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${infoAnexForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/infoAnexFormModificable.jsp" %>
  </c:if>

</form:form>


