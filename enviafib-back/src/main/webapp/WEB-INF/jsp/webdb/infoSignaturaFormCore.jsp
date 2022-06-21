<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoSignaturaFields" className="es.caib.enviafib.model.fields.InfoSignaturaFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNOPERATION)}">
        <tr id="infoSignatura_signoperation_rowid">
          <td id="infoSignatura_signoperation_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNOPERATION])?'infoSignatura.signoperation':__theForm.labels[InfoSignaturaFields.SIGNOPERATION]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNOPERATION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNOPERATION]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signoperation_columnvalueid">
          <form:errors path="infoSignatura.signoperation" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNOPERATION)}" >
          <form:hidden path="infoSignatura.signoperation"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signoperation,__theForm.listOfValuesForSignoperation)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNOPERATION)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signoperation"  onchange="if(typeof onChangeSignoperation == 'function') {  onChangeSignoperation(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signoperation">
            <c:forEach items="${__theForm.listOfValuesForSignoperation}" var="tmp">
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
        <tr id="infoSignatura_signtype_rowid">
          <td id="infoSignatura_signtype_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNTYPE])?'infoSignatura.signtype':__theForm.labels[InfoSignaturaFields.SIGNTYPE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNTYPE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNTYPE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signtype_columnvalueid">
            <form:errors path="infoSignatura.signtype" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNTYPE)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNTYPE)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.signtype"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNALGORITHM)}">
        <tr id="infoSignatura_signalgorithm_rowid">
          <td id="infoSignatura_signalgorithm_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNALGORITHM])?'infoSignatura.signalgorithm':__theForm.labels[InfoSignaturaFields.SIGNALGORITHM]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNALGORITHM]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNALGORITHM]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signalgorithm_columnvalueid">
            <form:errors path="infoSignatura.signalgorithm" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNALGORITHM)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNALGORITHM)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.signalgorithm"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNMODE)}">
        <tr id="infoSignatura_signmode_rowid">
          <td id="infoSignatura_signmode_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNMODE])?'infoSignatura.signmode':__theForm.labels[InfoSignaturaFields.SIGNMODE]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNMODE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNMODE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signmode_columnvalueid">
          <form:errors path="infoSignatura.signmode" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNMODE)}" >
          <form:hidden path="infoSignatura.signmode"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signmode,__theForm.listOfValuesForSignmode)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNMODE)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signmode"  onchange="if(typeof onChangeSignmode == 'function') {  onChangeSignmode(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signmode">
            <c:forEach items="${__theForm.listOfValuesForSignmode}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoSignatura.signmode }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoSignatura.signmode }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.SIGNATURESTABLELOCATION)}">
        <tr id="infoSignatura_signaturestablelocation_rowid">
          <td id="infoSignatura_signaturestablelocation_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.SIGNATURESTABLELOCATION])?'infoSignatura.signaturestablelocation':__theForm.labels[InfoSignaturaFields.SIGNATURESTABLELOCATION]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.SIGNATURESTABLELOCATION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.SIGNATURESTABLELOCATION]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_signaturestablelocation_columnvalueid">
          <form:errors path="infoSignatura.signaturestablelocation" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}" >
          <form:hidden path="infoSignatura.signaturestablelocation"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoSignatura.signaturestablelocation,__theForm.listOfValuesForSignaturestablelocation)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.SIGNATURESTABLELOCATION)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoSignatura_signaturestablelocation"  onchange="if(typeof onChangeSignaturestablelocation == 'function') {  onChangeSignaturestablelocation(this); };"  cssClass="form-control col-md-9-optional" path="infoSignatura.signaturestablelocation">
            <c:forEach items="${__theForm.listOfValuesForSignaturestablelocation}" var="tmp">
                <form:option value="${tmp.key}">${tmp.value}</form:option>
                <c:if test="${empty tmp.key}">
                  <c:set var="containEmptyValue"  value="true" />
                </c:if>
            </c:forEach>
            <%-- El camp pot ser null, per la qual cosa afegim una entrada buida si no s'ha definit abans --%>
            <c:if test="${not containEmptyValue}">
              <c:if test="${empty __theForm.infoSignatura.signaturestablelocation }">
                  <form:option value="" selected="true" ></form:option>
              </c:if>
              <c:if test="${not empty __theForm.infoSignatura.signaturestablelocation }">
                  <form:option value="" ></form:option>
              </c:if>
            </c:if>
          </form:select>
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.TIMESTAMPINCLUDED)}">
        <tr id="infoSignatura_timestampincluded_rowid">
          <td id="infoSignatura_timestampincluded_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.TIMESTAMPINCLUDED])?'infoSignatura.timestampincluded':__theForm.labels[InfoSignaturaFields.TIMESTAMPINCLUDED]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.TIMESTAMPINCLUDED]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.TIMESTAMPINCLUDED]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_timestampincluded_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.TIMESTAMPINCLUDED)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeTimestampincluded == 'function') {  onChangeTimestampincluded(this); };"  path="infoSignatura.timestampincluded">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.TIMESTAMPINCLUDED)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.timestampincluded}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.POLICYINCLUDED)}">
        <tr id="infoSignatura_policyincluded_rowid">
          <td id="infoSignatura_policyincluded_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.POLICYINCLUDED])?'infoSignatura.policyincluded':__theForm.labels[InfoSignaturaFields.POLICYINCLUDED]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.POLICYINCLUDED]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.POLICYINCLUDED]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_policyincluded_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.POLICYINCLUDED)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangePolicyincluded == 'function') {  onChangePolicyincluded(this); };"  path="infoSignatura.policyincluded">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.POLICYINCLUDED)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.policyincluded}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENITIPOFIRMA)}">
        <tr id="infoSignatura_enitipofirma_rowid">
          <td id="infoSignatura_enitipofirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENITIPOFIRMA])?'infoSignatura.enitipofirma':__theForm.labels[InfoSignaturaFields.ENITIPOFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENITIPOFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENITIPOFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_enitipofirma_columnvalueid">
            <form:errors path="infoSignatura.enitipofirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENITIPOFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENITIPOFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.enitipofirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENIPERFILFIRMA)}">
        <tr id="infoSignatura_eniperfilfirma_rowid">
          <td id="infoSignatura_eniperfilfirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENIPERFILFIRMA])?'infoSignatura.eniperfilfirma':__theForm.labels[InfoSignaturaFields.ENIPERFILFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENIPERFILFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENIPERFILFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_eniperfilfirma_columnvalueid">
            <form:errors path="infoSignatura.eniperfilfirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIPERFILFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIPERFILFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.eniperfilfirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENIROLFIRMA)}">
        <tr id="infoSignatura_enirolfirma_rowid">
          <td id="infoSignatura_enirolfirma_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENIROLFIRMA])?'infoSignatura.enirolfirma':__theForm.labels[InfoSignaturaFields.ENIROLFIRMA]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENIROLFIRMA]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENIROLFIRMA]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_enirolfirma_columnvalueid">
            <form:errors path="infoSignatura.enirolfirma" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIROLFIRMA)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENIROLFIRMA)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.enirolfirma"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNERNAME)}">
        <tr id="infoSignatura_enisignername_rowid">
          <td id="infoSignatura_enisignername_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNERNAME])?'infoSignatura.enisignername':__theForm.labels[InfoSignaturaFields.ENISIGNERNAME]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNERNAME]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNERNAME]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_enisignername_columnvalueid">
            <form:errors path="infoSignatura.enisignername" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERNAME)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERNAME)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.enisignername"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)}">
        <tr id="infoSignatura_enisigneradministrationid_rowid">
          <td id="infoSignatura_enisigneradministrationid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNERADMINISTRATIONID])?'infoSignatura.enisigneradministrationid':__theForm.labels[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNERADMINISTRATIONID]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_enisigneradministrationid_columnvalueid">
            <form:errors path="infoSignatura.enisigneradministrationid" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNERADMINISTRATIONID)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.enisigneradministrationid"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.ENISIGNLEVEL)}">
        <tr id="infoSignatura_enisignlevel_rowid">
          <td id="infoSignatura_enisignlevel_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.ENISIGNLEVEL])?'infoSignatura.enisignlevel':__theForm.labels[InfoSignaturaFields.ENISIGNLEVEL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.ENISIGNLEVEL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.ENISIGNLEVEL]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_enisignlevel_columnvalueid">
            <form:errors path="infoSignatura.enisignlevel" cssClass="errorField alert alert-danger" />
            <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNLEVEL)? 'true' : 'false'}" cssClass="w-100 form-control  ${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.ENISIGNLEVEL)? ' uneditable-input' : ''}"  style="" maxlength="255" path="infoSignatura.enisignlevel"   />

           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}">
        <tr id="infoSignatura_checkadministrationidofsigner_rowid">
          <td id="infoSignatura_checkadministrationidofsigner_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER])?'infoSignatura.checkadministrationidofsigner':__theForm.labels[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkadministrationidofsigner_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckadministrationidofsigner == 'function') {  onChangeCheckadministrationidofsigner(this); };"  path="infoSignatura.checkadministrationidofsigner">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkadministrationidofsigner}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}">
        <tr id="infoSignatura_checkdocumentmodifications_rowid">
          <td id="infoSignatura_checkdocumentmodifications_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS])?'infoSignatura.checkdocumentmodifications':__theForm.labels[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkdocumentmodifications_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckdocumentmodifications == 'function') {  onChangeCheckdocumentmodifications(this); };"  path="infoSignatura.checkdocumentmodifications">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkdocumentmodifications}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}">
        <tr id="infoSignatura_checkvalidationsignature_rowid">
          <td id="infoSignatura_checkvalidationsignature_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE])?'infoSignatura.checkvalidationsignature':__theForm.labels[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoSignaturaFields.CHECKVALIDATIONSIGNATURE]}" ></i>
              </c:if>
            </td>
          <td id="infoSignatura_checkvalidationsignature_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}" >
              <form:select cssClass="form-control col-md-6" onchange="if(typeof onChangeCheckvalidationsignature == 'function') {  onChangeCheckvalidationsignature(this); };"  path="infoSignatura.checkvalidationsignature">
                <form:option value=""><fmt:message key="genapp.checkbox." /></form:option>
                <form:option value="true" ><fmt:message key="genapp.checkbox.true" /></form:option>
                <form:option value="false" ><fmt:message key="genapp.checkbox.false" /></form:option>
              </form:select>
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoSignaturaFields.CHECKVALIDATIONSIGNATURE)}" >
                <fmt:message key="genapp.checkbox.${__theForm.infoSignatura.checkvalidationsignature}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
