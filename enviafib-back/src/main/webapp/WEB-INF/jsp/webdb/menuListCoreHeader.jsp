<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="MenuFields" className="es.caib.enviafib.model.fields.MenuFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.MENUID)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.MENUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.NOM)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.DESCRIPCIO)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.TITOLMENUID)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.TITOLMENUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.AJUDAMENUID)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.AJUDAMENUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.ORDRE)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.ORDRE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.TIPUS)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.GRUPID)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.GRUPID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.PARAMETRECOMBO)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.PARAMETRECOMBO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.PARAMETRETEXT)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.PARAMETRETEXT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,MenuFields.ACTIU)}">
        <th>${efi:getSortIcons(__theFilterForm,MenuFields.ACTIU)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

