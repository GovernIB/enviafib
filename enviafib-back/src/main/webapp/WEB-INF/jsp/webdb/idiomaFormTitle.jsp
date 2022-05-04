<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(idiomaForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(idiomaForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty idiomaForm.titleCode}">
    <fmt:message key="${idiomaForm.titleCode}" >
      <fmt:param value="${idiomaForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty idiomaForm.entityNameCode}">
      <fmt:message var="entityname" key="idioma.idioma"/>
    </c:if>
    <c:if test="${not empty idiomaForm.entityNameCode}">
      <fmt:message var="entityname" key="${idiomaForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${idiomaForm.nou?'genapp.createtitle':(idiomaForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty idiomaForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(idiomaForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(idiomaForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${idiomaForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>