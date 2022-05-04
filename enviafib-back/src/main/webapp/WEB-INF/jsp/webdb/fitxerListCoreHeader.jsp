<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="FitxerFields" className="es.caib.enviafib.model.fields.FitxerFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FitxerFields.FITXERID)}">
        <th>${efi:getSortIcons(__theFilterForm,FitxerFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FitxerFields.NOM)}">
        <th>${efi:getSortIcons(__theFilterForm,FitxerFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FitxerFields.MIME)}">
        <th>${efi:getSortIcons(__theFilterForm,FitxerFields.MIME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FitxerFields.TAMANY)}">
        <th>${efi:getSortIcons(__theFilterForm,FitxerFields.TAMANY)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,FitxerFields.DESCRIPCIO)}">
        <th>${efi:getSortIcons(__theFilterForm,FitxerFields.DESCRIPCIO)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

