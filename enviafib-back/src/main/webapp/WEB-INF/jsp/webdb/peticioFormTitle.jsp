<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(peticioForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(peticioForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty peticioForm.titleCode}">
    <fmt:message key="${peticioForm.titleCode}" >
      <fmt:param value="${peticioForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty peticioForm.entityNameCode}">
      <fmt:message var="entityname" key="peticio.peticio"/>
    </c:if>
    <c:if test="${not empty peticioForm.entityNameCode}">
      <fmt:message var="entityname" key="${peticioForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${peticioForm.nou?'genapp.createtitle':(peticioForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty peticioForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(peticioForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(peticioForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${peticioForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>