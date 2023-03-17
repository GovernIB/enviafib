
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="grupFormTitle.jsp" %>


<form:form modelAttribute="grupForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${grupForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupFormCorePre.jsp" %>

  <%@include file="grupFormCore.jsp" %>

  <%@include file="grupFormCorePost.jsp" %>

  <%@include file="grupFormButtons.jsp" %>

  <c:if test="${not empty grupForm.sections}">
     <c:set var="__basename" value="grup" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${grupForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupFormModificable.jsp" %>
  </c:if>

</form:form>


