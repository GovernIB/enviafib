
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="pluginForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="pluginFormTitle.jsp" %>
 
  <c:set var="contexte" value="${pluginForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="pluginFormCorePre.jsp" %>

  <%@include file="pluginFormCore.jsp" %>

  <%@include file="pluginFormCorePost.jsp" %>

  <%@include file="pluginFormButtons.jsp" %>

  <c:if test="${not empty pluginForm.sections}">
     <c:set var="__basename" value="plugin" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${pluginForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/pluginFormModificable.jsp" %>
  </c:if>

</form:form>


