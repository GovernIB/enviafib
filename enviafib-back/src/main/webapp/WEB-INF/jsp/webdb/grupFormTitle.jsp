<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(grupForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(grupForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty grupForm.titleCode}">
    <fmt:message key="${grupForm.titleCode}" >
      <fmt:param value="${grupForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty grupForm.entityNameCode}">
      <fmt:message var="entityname" key="grup.grup"/>
    </c:if>
    <c:if test="${not empty grupForm.entityNameCode}">
      <fmt:message var="entityname" key="${grupForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${grupForm.nou?'genapp.createtitle':(grupForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty grupForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(grupForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(grupForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${grupForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>