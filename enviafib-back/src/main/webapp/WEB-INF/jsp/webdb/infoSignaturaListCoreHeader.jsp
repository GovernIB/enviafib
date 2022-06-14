<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoSignaturaFields" className="es.caib.enviafib.model.fields.InfoSignaturaFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.INFOSIGNATURAID)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.INFOSIGNATURAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNOPERATION)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.SIGNOPERATION)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNTYPE)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.SIGNTYPE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNALGORITHM)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.SIGNALGORITHM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNMODE)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.SIGNMODE)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.SIGNATURESTABLELOCATION)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.TIMESTAMPINCLUDED)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.TIMESTAMPINCLUDED)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.POLICYINCLUDED)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.POLICYINCLUDED)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENITIPOFIRMA)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENITIPOFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENIPERFILFIRMA)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENIPERFILFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENIROLFIRMA)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENIROLFIRMA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNERNAME)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENISIGNERNAME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNLEVEL)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.ENISIGNLEVEL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}">
        <th>${efi:getSortIcons(__theFilterForm,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

