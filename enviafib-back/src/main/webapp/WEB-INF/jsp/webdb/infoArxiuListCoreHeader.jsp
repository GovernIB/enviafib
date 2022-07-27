<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoArxiuFields" className="es.caib.enviafib.model.fields.InfoArxiuFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.INFOARXIUID)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.INFOARXIUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.ORIGINALFILEURL)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.ORIGINALFILEURL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.CSV)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.CSV)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.CSVGENERATIONDEFINITION)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.CSVGENERATIONDEFINITION)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.CSVVALIDATIONWEB)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.CSVVALIDATIONWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.ARXIUEXPEDIENTID)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.ARXIUEXPEDIENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.ARXIUDOCUMENTID)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.ARXIUDOCUMENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.PRINTABLEURL)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.PRINTABLEURL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.ENIFILEURL)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.ENIFILEURL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoArxiuFields.VALIDATIONFILEURL)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoArxiuFields.VALIDATIONFILEURL)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

