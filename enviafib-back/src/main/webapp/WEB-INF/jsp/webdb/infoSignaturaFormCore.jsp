<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoSignaturaFields" className="es.caib.enviafib.model.fields.InfoSignaturaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNOPERATION)}">
        <tr id="infoSignatura_signOperation_rowid">
          <td id="infoSignatura_signOperation_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNOPERATION])?'infoSignatura.signOperation':__theForm.labels[InfoSignaturaFields.SIGNOPERATION]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNOPERATION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNOPERATION]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signOperation_columnvalueid">
          <form:errors path="infoSignatura.signOperation" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNOPERATION)}" >
          <form:hidden path="infoSignatura.signOperation"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signOperation,__theForm.listOfValuesForSignOperation)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNOPERATION)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signOperation"  onchange="if(typeof onChangeSignOperation == 'function') {  onChangeSignOperation(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signOperation">
            <c:forEach items="${__theForm.listOfValuesForSignOperation}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNTYPE)}">
        <tr id="infoSignatura_signType_rowid">
          <td id="infoSignatura_signType_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNTYPE])?'infoSignatura.signType':__theForm.labels[InfoSignaturaFields.SIGNTYPE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNTYPE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNTYPE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signType_columnvalueid">
            <form:errors path="infoSignatura.signType" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNTYPE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNTYPE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.signType"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNALGORITHM)}">
        <tr id="infoSignatura_signAlgorithm_rowid">
          <td id="infoSignatura_signAlgorithm_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNALGORITHM])?'infoSignatura.signAlgorithm':__theForm.labels[InfoSignaturaFields.SIGNALGORITHM]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNALGORITHM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNALGORITHM]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signAlgorithm_columnvalueid">
            <form:errors path="infoSignatura.signAlgorithm" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNALGORITHM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNALGORITHM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.signAlgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNMODE)}">
        <tr id="infoSignatura_signMode_rowid">
          <td id="infoSignatura_signMode_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNMODE])?'infoSignatura.signMode':__theForm.labels[InfoSignaturaFields.SIGNMODE]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNMODE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNMODE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signMode_columnvalueid">
          <form:errors path="infoSignatura.signMode" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNMODE)}" >
          <form:hidden path="infoSignatura.signMode"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signMode,__theForm.listOfValuesForSignMode)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNMODE)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signMode"  onchange="if(typeof onChangeSignMode == 'function') {  onChangeSignMode(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signMode">
            <c:forEach items="${__theForm.listOfValuesForSignMode}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoSignatura.signMode }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoSignatura.signMode }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
        <tr id="infoSignatura_signaturesTableLocation_rowid">
          <td id="infoSignatura_signaturesTableLocation_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNATURESTABLELOCATION])?'infoSignatura.signaturesTableLocation':__theForm.labels[InfoSignaturaFields.SIGNATURESTABLELOCATION]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNATURESTABLELOCATION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNATURESTABLELOCATION]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signaturesTableLocation_columnvalueid">
          <form:errors path="infoSignatura.signaturesTableLocation" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}" >
          <form:hidden path="infoSignatura.signaturesTableLocation"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signaturesTableLocation,__theForm.listOfValuesForSignaturesTableLocation)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signaturesTableLocation"  onchange="if(typeof onChangeSignaturesTableLocation == 'function') {  onChangeSignaturesTableLocation(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signaturesTableLocation">
            <c:forEach items="${__theForm.listOfValuesForSignaturesTableLocation}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoSignatura.signaturesTableLocation }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoSignatura.signaturesTableLocation }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.TIMESTAMPINCLUDED)}">
        <tr id="infoSignatura_timestampIncluded_rowid">
          <td id="infoSignatura_timestampIncluded_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.TIMESTAMPINCLUDED])?'infoSignatura.timestampIncluded':__theForm.labels[InfoSignaturaFields.TIMESTAMPINCLUDED]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.TIMESTAMPINCLUDED]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.TIMESTAMPINCLUDED]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_timestampIncluded_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.TIMESTAMPINCLUDED)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeTimestampIncluded == 'function') {  onChangeTimestampIncluded(this); };"  path="infoSignatura.timestampIncluded">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.TIMESTAMPINCLUDED)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.timestampIncluded}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.POLICYINCLUDED)}">
        <tr id="infoSignatura_policyIncluded_rowid">
          <td id="infoSignatura_policyIncluded_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.POLICYINCLUDED])?'infoSignatura.policyIncluded':__theForm.labels[InfoSignaturaFields.POLICYINCLUDED]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.POLICYINCLUDED]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.POLICYINCLUDED]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_policyIncluded_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.POLICYINCLUDED)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangePolicyIncluded == 'function') {  onChangePolicyIncluded(this); };"  path="infoSignatura.policyIncluded">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.POLICYINCLUDED)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.policyIncluded}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENITIPOFIRMA)}">
        <tr id="infoSignatura_eniTipoFirma_rowid">
          <td id="infoSignatura_eniTipoFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENITIPOFIRMA])?'infoSignatura.eniTipoFirma':__theForm.labels[InfoSignaturaFields.ENITIPOFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENITIPOFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENITIPOFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniTipoFirma_columnvalueid">
            <form:errors path="infoSignatura.eniTipoFirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENITIPOFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENITIPOFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniTipoFirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENIPERFILFIRMA)}">
        <tr id="infoSignatura_eniPerfilFirma_rowid">
          <td id="infoSignatura_eniPerfilFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENIPERFILFIRMA])?'infoSignatura.eniPerfilFirma':__theForm.labels[InfoSignaturaFields.ENIPERFILFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENIPERFILFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENIPERFILFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniPerfilFirma_columnvalueid">
            <form:errors path="infoSignatura.eniPerfilFirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIPERFILFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIPERFILFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniPerfilFirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENIROLFIRMA)}">
        <tr id="infoSignatura_eniRolFirma_rowid">
          <td id="infoSignatura_eniRolFirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENIROLFIRMA])?'infoSignatura.eniRolFirma':__theForm.labels[InfoSignaturaFields.ENIROLFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENIROLFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENIROLFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniRolFirma_columnvalueid">
            <form:errors path="infoSignatura.eniRolFirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIROLFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIROLFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniRolFirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNERNAME)}">
        <tr id="infoSignatura_eniSignerName_rowid">
          <td id="infoSignatura_eniSignerName_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNERNAME])?'infoSignatura.eniSignerName':__theForm.labels[InfoSignaturaFields.ENISIGNERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNERNAME]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniSignerName_columnvalueid">
            <form:errors path="infoSignatura.eniSignerName" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniSignerName"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
        <tr id="infoSignatura_eniSignerAdministrationId_rowid">
          <td id="infoSignatura_eniSignerAdministrationId_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNERADMINISTRATIONID])?'infoSignatura.eniSignerAdministrationId':__theForm.labels[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniSignerAdministrationId_columnvalueid">
            <form:errors path="infoSignatura.eniSignerAdministrationId" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniSignerAdministrationId"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNLEVEL)}">
        <tr id="infoSignatura_eniSignLevel_rowid">
          <td id="infoSignatura_eniSignLevel_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNLEVEL])?'infoSignatura.eniSignLevel':__theForm.labels[InfoSignaturaFields.ENISIGNLEVEL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNLEVEL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNLEVEL]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniSignLevel_columnvalueid">
            <form:errors path="infoSignatura.eniSignLevel" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNLEVEL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNLEVEL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniSignLevel"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <tr id="infoSignatura_checkAdministrationIdOfSigner_rowid">
          <td id="infoSignatura_checkAdministrationIdOfSigner_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER])?'infoSignatura.checkAdministrationIdOfSigner':__theForm.labels[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkAdministrationIdOfSigner_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckAdministrationIdOfSigner == 'function') {  onChangeCheckAdministrationIdOfSigner(this); };"  path="infoSignatura.checkAdministrationIdOfSigner">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkAdministrationIdOfSigner}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <tr id="infoSignatura_checkDocumentModifications_rowid">
          <td id="infoSignatura_checkDocumentModifications_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS])?'infoSignatura.checkDocumentModifications':__theForm.labels[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkDocumentModifications_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckDocumentModifications == 'function') {  onChangeCheckDocumentModifications(this); };"  path="infoSignatura.checkDocumentModifications">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkDocumentModifications}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}">
        <tr id="infoSignatura_checkValidationSignature_rowid">
          <td id="infoSignatura_checkValidationSignature_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE])?'infoSignatura.checkValidationSignature':__theForm.labels[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkValidationSignature_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckValidationSignature == 'function') {  onChangeCheckValidationSignature(this); };"  path="infoSignatura.checkValidationSignature">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkValidationSignature}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
