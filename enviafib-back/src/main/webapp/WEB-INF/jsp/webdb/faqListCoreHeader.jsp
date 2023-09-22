<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FaqFields" className="es.caib.enviafib.model.fields.FaqFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FAQID)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.FAQID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ORDRE)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.ORDRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ENUNCIAT_ES)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.ENUNCIAT_ES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.ENUNCIAT_CA)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.ENUNCIAT_CA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.RESPOSTA_ES)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.RESPOSTA_ES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.RESPOSTA_CA)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.RESPOSTA_CA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER1ID)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.FITXER1ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER2ID)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.FITXER2ID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FaqFields.FITXER3ID)}">
        <th>${efi:getSortIcons(__theFilterForm,FaqFields.FITXER3ID)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

