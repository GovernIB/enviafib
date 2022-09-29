<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="OrganitzacioFields" className="es.caib.enviafib.model.fields.OrganitzacioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganitzacioFields.ORGANITZACIOID)}">
        <th>${efi:getSortIcons(__theFilterForm,OrganitzacioFields.ORGANITZACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganitzacioFields.CODICONSELLERIA)}">
        <th>${efi:getSortIcons(__theFilterForm,OrganitzacioFields.CODICONSELLERIA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganitzacioFields.CODIDIRECCIOGENERAL)}">
        <th>${efi:getSortIcons(__theFilterForm,OrganitzacioFields.CODIDIRECCIOGENERAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganitzacioFields.TIPUS)}">
        <th>${efi:getSortIcons(__theFilterForm,OrganitzacioFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,OrganitzacioFields.VALOR)}">
        <th>${efi:getSortIcons(__theFilterForm,OrganitzacioFields.VALOR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

