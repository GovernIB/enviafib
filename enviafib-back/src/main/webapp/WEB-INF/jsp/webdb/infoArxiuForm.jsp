
<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
 
  <%@include file="infoArxiuFormTitle.jsp" %>


<form:form modelAttribute="infoArxiuForm" method="${(empty method)?'post':method}"
  enctype="multipart/form-data">
  
  <c:set var="contexte" value="${infoArxiuForm.contexte}"/>
  <form:hidden path="nou" />
  
  <%@include file="infoArxiuFormCorePre.jsp" %>

  <%@include file="infoArxiuFormCore.jsp" %>

  <%@include file="infoArxiuFormCorePost.jsp" %>

  <%@include file="infoArxiuFormButtons.jsp" %>

  <c:if test="${not empty infoArxiuForm.sections}">
     <c:set var="__basename" value="infoArxiu" scope="page" />
     <%@include file="sections.jsp"%>
  </c:if>


  <c:if test="${infoArxiuForm.attachedAdditionalJspCode}">
     <%@include file="../webdbmodificable/infoArxiuFormModificable.jsp" %>
  </c:if>

</form:form>


