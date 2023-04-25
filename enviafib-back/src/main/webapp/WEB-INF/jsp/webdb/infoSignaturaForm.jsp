
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="infoSignaturaForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="infoSignaturaFormTitle.jsp" %>
 
  <c:set var="contexte" value="${infoSignaturaForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="infoSignaturaFormCorePre.jsp" %>

  <%@include file="infoSignaturaFormCore.jsp" %>

  <%@include file="infoSignaturaFormCorePost.jsp" %>

  <%@include file="infoSignaturaFormButtons.jsp" %>

  <c:if test="${not empty infoSignaturaForm.sections}">
     <c:set var="__basename" value="infoSignatura" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${infoSignaturaForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/infoSignaturaFormModificable.jsp" %>
  </c:if>

</form:form>


