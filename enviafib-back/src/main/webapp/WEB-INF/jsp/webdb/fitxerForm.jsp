
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<form:form modelAttribute="fitxerForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <%@include file="fitxerFormTitle.jsp" %>
 
  <c:set var="contexte" value="${fitxerForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="fitxerFormCorePre.jsp" %>

  <%@include file="fitxerFormCore.jsp" %>

  <%@include file="fitxerFormCorePost.jsp" %>

  <%@include file="fitxerFormButtons.jsp" %>

  <c:if test="${not empty fitxerForm.sections}">
     <c:set var="__basename" value="fitxer" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${fitxerForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/fitxerFormModificable.jsp" %>
  </c:if>

</form:form>


