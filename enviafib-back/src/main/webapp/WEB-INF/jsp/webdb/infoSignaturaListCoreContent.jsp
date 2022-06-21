<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoSignaturaFields" className="es.caib.enviafib.model.fields.InfoSignaturaFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[infoSignatura.infosignaturaid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.INFOSIGNATURAID)}">
          <td>
          ${infoSignatura.infosignaturaid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNOPERATION)}">
          <td>
          <c:set var="tmp">${infoSignatura.signoperation}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForSignoperation[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNTYPE)}">
          <td>
          ${infoSignatura.signtype}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNALGORITHM)}">
          <td>
          ${infoSignatura.signalgorithm}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNMODE)}">
          <td>
          <c:set var="tmp">${infoSignatura.signmode}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForSignmode[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
          <td>
          <c:set var="tmp">${infoSignatura.signaturestablelocation}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForSignaturestablelocation[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.TIMESTAMPINCLUDED)}">
          <td>
            &nbsp;<c:if test="${not empty infoSignatura.timestampincluded}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${infoSignatura.timestampincluded?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.POLICYINCLUDED)}">
          <td>
            &nbsp;<c:if test="${not empty infoSignatura.policyincluded}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${infoSignatura.policyincluded?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENITIPOFIRMA)}">
          <td>
          ${infoSignatura.enitipofirma}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENIPERFILFIRMA)}">
          <td>
          ${infoSignatura.eniperfilfirma}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENIROLFIRMA)}">
          <td>
          ${infoSignatura.enirolfirma}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNERNAME)}">
          <td>
          ${infoSignatura.enisignername}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
          <td>
          ${infoSignatura.enisigneradministrationid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.ENISIGNLEVEL)}">
          <td>
          ${infoSignatura.enisignlevel}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
          <td>
            &nbsp;<c:if test="${not empty infoSignatura.checkadministrationidofsigner}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${infoSignatura.checkadministrationidofsigner?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}">
          <td>
            &nbsp;<c:if test="${not empty infoSignatura.checkdocumentmodifications}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${infoSignatura.checkdocumentmodifications?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}">
          <td>
            &nbsp;<c:if test="${not empty infoSignatura.checkvalidationsignature}">
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${infoSignatura.checkvalidationsignature?'success':'error'}.png"/>">
            </c:if>
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[infoSignatura.infosignaturaid]}" />
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


