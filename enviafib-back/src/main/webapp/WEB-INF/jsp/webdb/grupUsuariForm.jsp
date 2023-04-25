
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="grupUsuariForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="grupUsuariFormTitle.jsp" %>
 
  <c:set var="contexte" value="${grupUsuariForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="grupUsuariFormCorePre.jsp" %>

  <%@include file="grupUsuariFormCore.jsp" %>

  <%@include file="grupUsuariFormCorePost.jsp" %>

  <%@include file="grupUsuariFormButtons.jsp" %>

  <c:if test="${not empty grupUsuariForm.sections}">
     <c:set var="__basename" value="grupUsuari" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${grupUsuariForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/grupUsuariFormModificable.jsp" %>
  </c:if>

</form:form>


