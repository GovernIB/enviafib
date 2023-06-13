<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FaqFields" className="es.caib.enviafib.model.fields.FaqFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[faq.faqID]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FAQID)}">
          <td>
          ${faq.faqID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ENUNCIAT_ES)}">
          <td>
          ${faq.enunciat_es}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ENUNCIAT_CA)}">
          <td>
          ${faq.enunciat_ca}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.RESPOSTA_ES)}">
          <td>
          ${faq.resposta_es}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.RESPOSTA_CA)}">
          <td>
          ${faq.resposta_ca}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ORDRE)}">
          <td>
          ${faq.ordre}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER1ID)}">
          <td>
            <c:if test="${not empty faq.fitxer1}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(faq.fitxer1)}"/>">${faq.fitxer1.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER2ID)}">
          <td>
            <c:if test="${not empty faq.fitxer2}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(faq.fitxer2)}"/>">${faq.fitxer2.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER3ID)}">
          <td>
            <c:if test="${not empty faq.fitxer3}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(faq.fitxer3)}"/>">${faq.fitxer3.nom}</a>
            </c:if>
           </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[faq.faqID]}" />
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


