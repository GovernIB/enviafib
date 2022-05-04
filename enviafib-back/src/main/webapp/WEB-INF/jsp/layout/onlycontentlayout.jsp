<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@include file="/WEB-INF/jsp/moduls/includes.jsp"
%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="<c:out value="${pageContext.response.locale.language}"/>"  lang="<c:out value="${pageContext.response.locale.language}"/>">
<head>

<c:if test="${not empty favicon}">
<link href="<c:url value="${efi:fileUrl(favicon)}"/>" rel="shortcut icon" type="image/x-icon" />
</c:if>

<%@ include file="/WEB-INF/jsp/moduls/imports.jsp"%>
</head>
<body>

    <!--  CONTINGUT  -->
    <div style="padding: 10px">

      <!--  Missatges  -->
      <%-- <tiles:insertAttribute name="missatges" />  --%>
      <jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

      <!-- Contingut de la pagina -->
      <tiles:insertAttribute name="contingut" >           
      </tiles:insertAttribute>

      <!-- FINAL DIV CONTINGUT -->
    </div>

</body>
</html>
