<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
<div class="lead" style="margin-bottom:10px">
<label style="font-size: 1.25rem;font-weight: bold;">
 <c:choose>
  <c:when test="${fn:startsWith(faqForm.titleCode,'=')}">
       <c:out value="${fn:substringAfter(faqForm.titleCode, '=')}" escapeXml="false"/>
  </c:when>
  <c:when test="${not empty faqForm.titleCode}">
    <fmt:message key="${faqForm.titleCode}" >
      <fmt:param value="${faqForm.titleParam}" />
    </fmt:message>
  </c:when>
  <c:otherwise>
    <c:if test="${empty faqForm.entityNameCode}">
      <fmt:message var="entityname" key="faq.faq"/>
    </c:if>
    <c:if test="${not empty faqForm.entityNameCode}">
      <fmt:message var="entityname" key="${faqForm.entityNameCode}"/>
    </c:if>
    <c:set var="keytitle" value="${faqForm.nou?'genapp.createtitle':(faqForm.view?'genapp.viewtitle':'genapp.edittitle')}"/>
    <fmt:message key="${keytitle}">
      <fmt:param value="${entityname}"/>
    </fmt:message>
    </c:otherwise>
 </c:choose></label>
  <c:if test="${not empty faqForm.subTitleCode}">
<h6 style="line-height: 10px; margin-top: 0px; margin-bottom: 0px;font-style:italic;">
<c:set var="subtitleTranslated" value="${fn:startsWith(faqForm.subTitleCode,'=')}" />
<c:if test="${subtitleTranslated}">
   <c:out value="${fn:substringAfter(faqForm.subTitleCode, '=')}" escapeXml="false"/>
</c:if>
<c:if test="${not subtitleTranslated}">
  <fmt:message key="${faqForm.subTitleCode}" />
</c:if>
</h6>
  </c:if>
</div>