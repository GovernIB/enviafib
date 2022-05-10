<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioFields" className="es.caib.enviafib.model.fields.PeticioFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[peticio.peticioID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.TITOLID)}">
          <td>
          <c:set var="tmp">${peticio.titolID}</c:set>
          <c:if test="${not empty tmp}">
          ${peticio.titol.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.PETICIOID)}">
          <td>
          ${peticio.peticioID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticio.datacreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.FITXERID)}">
          <td>
            <c:if test="${not empty peticio.fitxer}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(peticio.fitxer)}"/>">${peticio.fitxer.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.SOLICITANTID)}">
          <td>
          <c:set var="tmp">${peticio.solicitantID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfUsuariForSolicitantID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.IDIOMAID)}">
          <td>
          <c:set var="tmp">${peticio.idiomaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfIdiomaForIdiomaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DESTINATARINIF)}">
          <td>
          ${peticio.destinatarinif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ESTAT)}">
          <td>
          ${peticio.estat}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.FITXERFIRMATID)}">
          <td>
            <c:if test="${not empty peticio.fitxerFirmat}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(peticio.fitxerFirmat)}"/>">${peticio.fitxerFirmat.nom}</a>
            </c:if>
           </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[peticio.peticioID]}" />
             </c:if>
             <c:if test="${not empty __entry.value.valueField }">
               <c:set var="__tmp" value="${pageScope}" />
               <c:set var="__trosos" value="${fn:split(__entry.value.valueField.fullName,'.')}" />
               <c:forEach var="__tros" items="${__trosos}">
                  <c:set var="__tmp" value="${__tmp[__tros]}" />
               </c:forEach>
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__tmp}" />
             </c:if>
          </td>
          </c:if>
          </c:forEach>


