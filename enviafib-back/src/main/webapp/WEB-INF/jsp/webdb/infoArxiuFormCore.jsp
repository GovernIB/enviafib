<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoArxiuFields" className="es.caib.enviafib.model.fields.InfoArxiuFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ORIGINALFILEURL)}">
        <tr id="infoArxiu_originalFileUrl_rowid">
          <td id="infoArxiu_originalFileUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ORIGINALFILEURL])?'infoArxiu.originalFileUrl':__theForm.labels[InfoArxiuFields.ORIGINALFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ORIGINALFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ORIGINALFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_originalFileUrl_columnvalueid">
            <form:errors path="infoArxiu.originalFileUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ORIGINALFILEURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ORIGINALFILEURL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.originalFileUrl"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.CSV)}">
        <tr id="infoArxiu_csv_rowid">
          <td id="infoArxiu_csv_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.CSV])?'infoArxiu.csv':__theForm.labels[InfoArxiuFields.CSV]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.CSV]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.CSV]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_csv_columnvalueid">
            <form:errors path="infoArxiu.csv" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSV)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSV)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.csv"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.CSVGENERATIONDEFINITION)}">
        <tr id="infoArxiu_csvGenerationDefinition_rowid">
          <td id="infoArxiu_csvGenerationDefinition_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.CSVGENERATIONDEFINITION])?'infoArxiu.csvGenerationDefinition':__theForm.labels[InfoArxiuFields.CSVGENERATIONDEFINITION]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.CSVGENERATIONDEFINITION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.CSVGENERATIONDEFINITION]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_csvGenerationDefinition_columnvalueid">
            <form:errors path="infoArxiu.csvGenerationDefinition" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVGENERATIONDEFINITION)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVGENERATIONDEFINITION)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.csvGenerationDefinition"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.CSVVALIDATIONWEB)}">
        <tr id="infoArxiu_csvValidationWeb_rowid">
          <td id="infoArxiu_csvValidationWeb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.CSVVALIDATIONWEB])?'infoArxiu.csvValidationWeb':__theForm.labels[InfoArxiuFields.CSVVALIDATIONWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.CSVVALIDATIONWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.CSVVALIDATIONWEB]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_csvValidationWeb_columnvalueid">
            <form:errors path="infoArxiu.csvValidationWeb" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVVALIDATIONWEB)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVVALIDATIONWEB)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.csvValidationWeb"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ARXIUEXPEDIENTID)}">
        <tr id="infoArxiu_arxiuExpedientID_rowid">
          <td id="infoArxiu_arxiuExpedientID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ARXIUEXPEDIENTID])?'infoArxiu.arxiuExpedientID':__theForm.labels[InfoArxiuFields.ARXIUEXPEDIENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ARXIUEXPEDIENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ARXIUEXPEDIENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_arxiuExpedientID_columnvalueid">
            <form:errors path="infoArxiu.arxiuExpedientID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUEXPEDIENTID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUEXPEDIENTID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.arxiuExpedientID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ARXIUDOCUMENTID)}">
        <tr id="infoArxiu_arxiuDocumentID_rowid">
          <td id="infoArxiu_arxiuDocumentID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ARXIUDOCUMENTID])?'infoArxiu.arxiuDocumentID':__theForm.labels[InfoArxiuFields.ARXIUDOCUMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ARXIUDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ARXIUDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_arxiuDocumentID_columnvalueid">
            <form:errors path="infoArxiu.arxiuDocumentID" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUDOCUMENTID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUDOCUMENTID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.arxiuDocumentID"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.PRINTABLEURL)}">
        <tr id="infoArxiu_printableUrl_rowid">
          <td id="infoArxiu_printableUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.PRINTABLEURL])?'infoArxiu.printableUrl':__theForm.labels[InfoArxiuFields.PRINTABLEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.PRINTABLEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.PRINTABLEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_printableUrl_columnvalueid">
            <form:errors path="infoArxiu.printableUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.PRINTABLEURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.PRINTABLEURL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.printableUrl"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ENIFILEURL)}">
        <tr id="infoArxiu_eniFileUrl_rowid">
          <td id="infoArxiu_eniFileUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ENIFILEURL])?'infoArxiu.eniFileUrl':__theForm.labels[InfoArxiuFields.ENIFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ENIFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ENIFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_eniFileUrl_columnvalueid">
            <form:errors path="infoArxiu.eniFileUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ENIFILEURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ENIFILEURL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.eniFileUrl"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.VALIDATIONFILEURL)}">
        <tr id="infoArxiu_validationFileUrl_rowid">
          <td id="infoArxiu_validationFileUrl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.VALIDATIONFILEURL])?'infoArxiu.validationFileUrl':__theForm.labels[InfoArxiuFields.VALIDATIONFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.VALIDATIONFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.VALIDATIONFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_validationFileUrl_columnvalueid">
            <form:errors path="infoArxiu.validationFileUrl" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.VALIDATIONFILEURL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.VALIDATIONFILEURL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoArxiu.validationFileUrl"   />

           </td>
        </tr>
        </c:if>
        
