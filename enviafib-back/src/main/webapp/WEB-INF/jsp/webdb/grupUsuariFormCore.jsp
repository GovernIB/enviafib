<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="GrupUsuariFields" className="es.caib.enviafib.model.fields.GrupUsuariFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupUsuariFields.GRUPID)}">
        <tr id="grupUsuari_grupID_rowid">
          <td id="grupUsuari_grupID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupUsuariFields.GRUPID])?'grupUsuari.grupID':__theForm.labels[GrupUsuariFields.GRUPID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[GrupUsuariFields.GRUPID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupUsuariFields.GRUPID]}" ></i>
              </c:if>
            </td>
          <td id="grupUsuari_grupID_columnvalueid">
          <form:errors path="grupUsuari.grupID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupUsuariFields.GRUPID)}" >
          <form:hidden path="grupUsuari.grupID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupUsuari.grupID,__theForm.listOfGrupForGrupID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupUsuariFields.GRUPID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="grupUsuari_grupID"  onchange="if(typeof onChangeGrupID == 'function') {  onChangeGrupID(this); };"  cssClass="form-control col-md-9-optional" path="grupUsuari.grupID">
            <c:forEach items="${__theForm.listOfGrupForGrupID}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,GrupUsuariFields.USUARIID)}">
        <tr id="grupUsuari_usuariID_rowid">
          <td id="grupUsuari_usuariID_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[GrupUsuariFields.USUARIID])?'grupUsuari.usuariID':__theForm.labels[GrupUsuariFields.USUARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[GrupUsuariFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[GrupUsuariFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="grupUsuari_usuariID_columnvalueid">
          <form:errors path="grupUsuari.usuariID" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,GrupUsuariFields.USUARIID)}" >
          <form:hidden path="grupUsuari.usuariID"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.grupUsuari.usuariID,__theForm.listOfUsuariForUsuariID)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,GrupUsuariFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="grupUsuari_usuariID"  onchange="if(typeof onChangeUsuariID == 'function') {  onChangeUsuariID(this); };"  cssClass="form-control col-md-9-optional" path="grupUsuari.usuariID">
            <c:forEach items="${__theForm.listOfUsuariForUsuariID}" var="tmp">
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
        
