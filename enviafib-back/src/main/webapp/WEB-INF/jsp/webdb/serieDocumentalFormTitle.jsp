<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(serieDocumentalForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(serieDocumentalForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty serieDocumentalForm.titleCode}">
    <fmt:message key="${serieDocumentalForm.titleCode}" >
      <fmt:param value="${serieDocumentalForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty serieDocumentalForm.entityNameCode}">
      <fmt:message var="entityname" key="serieDocumental.serieDocumental"/>
    </c:if>
    <c:if test="${not empty serieDocumentalForm.entityNameCode}">
      <fmt:message var="entityname" key="${serieDocumentalForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${serieDocumentalForm.nou?'genapp.createtitle':(serieDocumentalForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty serieDocumentalForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(serieDocumentalForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(serieDocumentalForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${serieDocumentalForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>