<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="IdiomaFields" className="es.caib.enviafib.model.fields.IdiomaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.IDIOMAID)}">
        <tr id="idioma_idiomaID_rowid">
          <td id="idioma_idiomaID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.IDIOMAID])?'idioma.idiomaID':__theForm.labels[IdiomaFields.IDIOMAID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IdiomaFields.IDIOMAID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IdiomaFields.IDIOMAID]}" ></i>
              </c:if>
            </td>
          <td id="idioma_idiomaID_columnvalueid">
            <form:errors path="idioma.idiomaID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.IDIOMAID)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,IdiomaFields.IDIOMAID)? ' uneditable-input' : ''}"  style="" maxlength="5" path="idioma.idiomaID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.NOM)}">
        <tr id="idioma_nom_rowid">
          <td id="idioma_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.NOM])?'idioma.nom':__theForm.labels[IdiomaFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IdiomaFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IdiomaFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="idioma_nom_columnvalueid">
            <form:errors path="idioma.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.NOM)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,IdiomaFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="50" path="idioma.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.SUPORTAT)}">
        <tr id="idioma_suportat_rowid">
          <td id="idioma_suportat_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.SUPORTAT])?'idioma.suportat':__theForm.labels[IdiomaFields.SUPORTAT]}" />
             </label>
              <c:if test="${not empty __theForm.help[IdiomaFields.SUPORTAT]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IdiomaFields.SUPORTAT]}" ></i>
              </c:if>
            </td>
          <td id="idioma_suportat_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)}" >
              <form:errors path="idioma.suportat" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)? 'false' : 'true'}" path="idioma.suportat" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,IdiomaFields.SUPORTAT)}" >
                <fmt:message key="genapp.checkbox.${__theForm.idioma.suportat}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,IdiomaFields.ORDRE)}">
        <tr id="idioma_ordre_rowid">
          <td id="idioma_ordre_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[IdiomaFields.ORDRE])?'idioma.ordre':__theForm.labels[IdiomaFields.ORDRE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[IdiomaFields.ORDRE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[IdiomaFields.ORDRE]}" ></i>
              </c:if>
            </td>
          <td id="idioma_ordre_columnvalueid">
            <form:errors path="idioma.ordre" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,IdiomaFields.ORDRE)? 'true' : 'false'}" cssClass="w-25 form-control  ${gen:contains(__theForm.readOnlyFields ,IdiomaFields.ORDRE)? ' uneditable-input' : ''}"  style=""  path="idioma.ordre"   />

           </td>
        </tr>
        </c:if>
        
