<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
  
          <c:if test="${__theFilterForm.editButtonVisible || __theFilterForm.viewButtonVisible ||__theFilterForm.deleteButtonVisible || not empty __theFilterForm.additionalButtonsForEachItem || not empty __theFilterForm.additionalButtonsByPK}">
          <th><fmt:message key="genapp.actions" /></th>
          </c:if>
