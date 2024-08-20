<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="EntitatFields" className="es.caib.enviafib.model.fields.EntitatFields"/>



        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key < 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatid]}" />
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


        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ENTITATID)}">
          <td>
          ${entitat.entitatid}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.NOM)}">
          <td>
          ${entitat.nom}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.DESCRIPCIO)}">
          <td>
          ${entitat.descripcio}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ADREZAHTML)}">
          <td>
          ${entitat.adrezahtml}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.ACTIVA)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.activa?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTTELEFON)}">
          <td>
          ${entitat.suporttelefon}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTWEB)}">
          <td>
          ${entitat.suportweb}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SUPORTEMAIL)}">
          <td>
          ${entitat.suportemail}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.FAVICONID)}">
          <td>
            <c:if test="${not empty entitat.favicon}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(entitat.favicon)}"/>">${entitat.favicon.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBID)}">
          <td>
            <c:if test="${not empty entitat.logoweb}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(entitat.logoweb)}"/>">${entitat.logoweb.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOWEBPEUID)}">
          <td>
            <c:if test="${not empty entitat.logowebpeu}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(entitat.logowebpeu)}"/>">${entitat.logowebpeu.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.LOGOSEGELLID)}">
          <td>
            <c:if test="${not empty entitat.logosegell}">
              <a target="_blank" href="<c:url value="${efi:fileUrl(entitat.logosegell)}"/>">${entitat.logosegell.nom}</a>
            </c:if>
           </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.WEB)}">
          <td>
          ${entitat.web}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.MOTIUDELEGACIOID)}">
          <td>
          <c:set var="tmp">${entitat.motiudelegacioID}</c:set>
          <c:if test="${not empty tmp}">
          ${entitat.motiudelegacio.traduccions[lang].valor}
          </c:if>
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.SEGELLDETEMPSVIAWEB)}">
          <td>
          ${entitat.segelldetempsviaweb}
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.CHECKCANVIATDOCFIRMAT)}">
          <td>
            <img height="18" width="18" src="<c:url value="/img/icn_alert_${entitat.checkcanviatdocfirmat?'success':'error'}.png"/>">
          </td>
        </c:if>
        <c:if test="${!gen:contains(__theFilterForm.hiddenFields,EntitatFields.PROPIETATSTAULAFIRMES)}">
          <td>
          ${entitat.propietatstaulafirmes}
          </td>
        </c:if>


        <!--  /** Additional Fields */  -->
        <c:forEach var="__entry" items="${__theFilterForm.additionalFields}" >
        <c:if test="${ __entry.key >= 0  && ((empty __entry.value.searchBy)? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.searchBy)) && ((empty __entry.value.groupBy )? true : !gen:contains(__theFilterForm.hiddenFields, __entry.value.groupBy ))}">
          <td>
             <c:if test="${not empty __entry.value.valueMap }">
               <c:out escapeXml="${__entry.value.escapeXml}" value="${__entry.value.valueMap[entitat.entitatid]}" />
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


