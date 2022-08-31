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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.PETICIOID)}">
          <td>
          ${peticio.peticioID}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DATACREACIO)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticio.dataCreacio}" /></td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DATAFINAL)}">
          <td> <fmt:formatDate pattern="${gen:getDateTimePattern()}" value="${peticio.dataFinal}" /></td>
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
          ${peticio.destinatariNif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ESTAT)}">
          <td>
          <c:set var="tmp">${peticio.estat}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForEstat[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.FITXERFIRMATID)}">
          <td>
            <c:if test="${not empty peticio.fitxerFirmat}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(peticio.fitxerFirmat)}"/>">${peticio.fitxerFirmat.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.TIPUSDOCUMENTAL)}">
          <td>
          <c:set var="tmp">${peticio.tipusDocumental}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipusDocumental[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.IDIOMADOC)}">
          <td>
          <c:set var="tmp">${peticio.idiomaDoc}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForIdiomaDoc[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.INFOSIGNATURAID)}">
          <td>
          <c:set var="tmp">${peticio.infoSignaturaID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfInfoSignaturaForInfoSignaturaID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.TIPUS)}">
          <td>
          <c:set var="tmp">${peticio.tipus}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForTipus[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ERRORMSG)}">
          <td>
          ${peticio.errorMsg}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ERROREXCEPTION)}">
          <td>
          ${peticio.errorException}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.PETICIOPORTAFIRMES)}">
          <td>
          ${peticio.peticioPortafirmes}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.REASON)}">
          <td>
          ${peticio.reason}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUFUNCIONARIUSERNAME)}">
          <td>
          ${peticio.arxiuFuncionariUsername}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINOM)}">
          <td>
          ${peticio.arxiuParamFuncionariNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINIF)}">
          <td>
          ${peticio.arxiuParamFuncionariNif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)}">
          <td>
          ${peticio.arxiuParamFuncionariDir3}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}">
          <td>
          <c:set var="tmp">${peticio.arxiuReqParamDocEstatElabora}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForArxiuReqParamDocEstatElabora[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMINTERESSATS)}">
          <td>
          ${peticio.arxiuReqParamInteressats}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANIF)}">
          <td>
          ${peticio.arxiuReqParamCiutadaNif}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANOM)}">
          <td>
          ${peticio.arxiuReqParamCiutadaNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMORGANS)}">
          <td>
          ${peticio.arxiuReqParamOrgans}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)}">
          <td>
          ${peticio.arxiuOptParamProcedimentCodi}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)}">
          <td>
          ${peticio.arxiuOptParamProcedimentNom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)}">
          <td>
          ${peticio.arxiuOptParamSerieDocumental}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)}">
          <td>
          ${peticio.arxiuOptParamExpedientId}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMORIGEN)}">
          <td>
          <c:set var="tmp">${peticio.arxiuReqParamOrigen}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfValuesForArxiuReqParamOrigen[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.INFOARXIUID)}">
          <td>
          <c:set var="tmp">${peticio.infoArxiuID}</c:set>
          <c:if test="${not empty tmp}">
          ${__theFilterForm.mapOfInfoArxiuForInfoArxiuID[tmp]}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.NOM)}">
          <td>
          ${peticio.nom}
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


