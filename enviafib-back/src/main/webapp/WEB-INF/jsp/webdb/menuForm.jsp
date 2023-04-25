
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="menuForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="menuFormTitle.jsp" %>
 
  <c:set var="contexte" value="${menuForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="menuFormCorePre.jsp" %>

  <%@include file="menuFormCore.jsp" %>

  <%@include file="menuFormCorePost.jsp" %>

  <%@include file="menuFormButtons.jsp" %>

  <c:if test="${not empty menuForm.sections}">
     <c:set var="__basename" value="menu" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${menuForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/menuFormModificable.jsp" %>
  </c:if>

</form:form>


