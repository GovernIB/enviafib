
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="serieDocumentalFormTitle.jsp" %>


<form:form modelAttribute="serieDocumentalForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${serieDocumentalForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="serieDocumentalFormCorePre.jsp" %>

  <%@include file="serieDocumentalFormCore.jsp" %>

  <%@include file="serieDocumentalFormCorePost.jsp" %>

  <%@include file="serieDocumentalFormButtons.jsp" %>

  <c:if test="${not empty serieDocumentalForm.sections}">
     <c:set var="__basename" value="serieDocumental" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${serieDocumentalForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/serieDocumentalFormModificable.jsp" %>
  </c:if>

</form:form>


