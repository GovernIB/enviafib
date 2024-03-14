<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioFields" className="es.caib.enviafib.model.fields.PeticioFields"/>
  


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key < 0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.PETICIOID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.PETICIOID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.NOM)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.NOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DATACREACIO)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.DATACREACIO)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DATAFINAL)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.DATAFINAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.FITXERID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.FITXERID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.SOLICITANTID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.SOLICITANTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.IDIOMAID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.IDIOMAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.DESTINATARINIF)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.DESTINATARINIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ESTAT)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ESTAT)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.FITXERFIRMATID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.FITXERFIRMATID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.TIPUSDOCUMENTAL)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.TIPUSDOCUMENTAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.IDIOMADOC)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.IDIOMADOC)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.INFOSIGNATURAID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.INFOSIGNATURAID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.TIPUS)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.TIPUS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ERRORMSG)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ERRORMSG)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ERROREXCEPTION)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ERROREXCEPTION)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.PETICIOPORTAFIRMES)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.PETICIOPORTAFIRMES)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.REASON)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.REASON)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUFUNCIONARIUSERNAME)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUFUNCIONARIUSERNAME)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINOM)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUPARAMFUNCIONARINOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARINIF)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUPARAMFUNCIONARINIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMINTERESSATS)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMINTERESSATS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANIF)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMCIUTADANIF)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMCIUTADANOM)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMCIUTADANOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMORGANS)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMORGANS)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.ARXIUREQPARAMORIGEN)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.ARXIUREQPARAMORIGEN)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.INFOARXIUID)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.INFOARXIUID)}</th>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,PeticioFields.REVISOR)}">
        <th>${efi:getSortIcons(__theFilterForm,PeticioFields.REVISOR)}</th>
        </c:if>


        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
        <c:if test="${ __entry.key >=0 && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
        <th>
        ${efi:getSortIconsAdditionalField(__theFilterForm,__entry.value)}
        </th>
        </c:if>
        </c:forEach>

