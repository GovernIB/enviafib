<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariFields" className="es.caib.enviafib.model.fields.UsuariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.USERNAME)}">
        <tr id="usuari_username_rowid">
          <td id="usuari_username_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.USERNAME])?'usuari.username':__theForm.labels[UsuariFields.USERNAME]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.USERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.USERNAME]}" ></i>
              </c:if>
            </td>
          <td id="usuari_username_columnvalueid">
            <form:errors path="usuari.username" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.USERNAME)? ' uneditable-input' : ''}"  style="" maxlength="100" path="usuari.username"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NOM)}">
        <tr id="usuari_nom_rowid">
          <td id="usuari_nom_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NOM])?'usuari.nom':__theForm.labels[UsuariFields.NOM]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.NOM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NOM]}" ></i>
              </c:if>
            </td>
          <td id="usuari_nom_columnvalueid">
            <form:errors path="usuari.nom" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NOM)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuari.nom"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE1)}">
        <tr id="usuari_llinatge1_rowid">
          <td id="usuari_llinatge1_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE1])?'usuari.llinatge1':__theForm.labels[UsuariFields.LLINATGE1]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE1]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE1]}" ></i>
              </c:if>
            </td>
          <td id="usuari_llinatge1_columnvalueid">
            <form:errors path="usuari.llinatge1" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE1)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuari.llinatge1"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.LLINATGE2)}">
        <tr id="usuari_llinatge2_rowid">
          <td id="usuari_llinatge2_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.LLINATGE2])?'usuari.llinatge2':__theForm.labels[UsuariFields.LLINATGE2]}" />
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.LLINATGE2]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.LLINATGE2]}" ></i>
              </c:if>
            </td>
          <td id="usuari_llinatge2_columnvalueid">
            <form:errors path="usuari.llinatge2" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.LLINATGE2)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuari.llinatge2"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.NIF)}">
        <tr id="usuari_nif_rowid">
          <td id="usuari_nif_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.NIF])?'usuari.nif':__theForm.labels[UsuariFields.NIF]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.NIF]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.NIF]}" ></i>
              </c:if>
            </td>
          <td id="usuari_nif_columnvalueid">
            <form:errors path="usuari.nif" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? 'true' : 'false'}" cssClass="w-75 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.NIF)? ' uneditable-input' : ''}"  style="" maxlength="50" path="usuari.nif"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariFields.EMAIL)}">
        <tr id="usuari_email_rowid">
          <td id="usuari_email_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariFields.EMAIL])?'usuari.email':__theForm.labels[UsuariFields.EMAIL]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariFields.EMAIL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariFields.EMAIL]}" ></i>
              </c:if>
            </td>
          <td id="usuari_email_columnvalueid">
            <form:errors path="usuari.email" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,UsuariFields.EMAIL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,UsuariFields.EMAIL)? ' uneditable-input' : ''}"  style="" maxlength="256" path="usuari.email"   />

           </td>
        </tr>
        </c:if>
        
