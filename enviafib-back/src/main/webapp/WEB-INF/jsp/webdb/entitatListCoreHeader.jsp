<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.enviafib.model.fields.EntitatFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.ENTITATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.DESCRIPCIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ADREZAHTML)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.ADREZAHTML)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.ACTIVA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.SUPORTTELEFON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.SUPORTWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.SUPORTEMAIL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FAVICONID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.FAVICONID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.LOGOWEBID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.LOGOWEBPEUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.LOGOSEGELLID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEB)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.WEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.MOTIUDELEGACIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SEGELLDETEMPSVIAWEB)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.SEGELLDETEMPSVIAWEB)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.CHECKCANVIATDOCFIRMAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PROPIETATSTAULAFIRMES)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.PROPIETATSTAULAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DIR3)}">
        <th>${efi:getSortIcons(__theFilterForm,EntitatFields.DIR3)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

