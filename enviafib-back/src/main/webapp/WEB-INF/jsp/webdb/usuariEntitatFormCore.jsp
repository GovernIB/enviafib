<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="UsuariEntitatFields" className="es.caib.enviafib.model.fields.UsuariEntitatFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.USUARIID)}">
        <tr id="usuariEntitat_usuariid_rowid">
          <td id="usuariEntitat_usuariid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.USUARIID])?'usuariEntitat.usuariid':__theForm.labels[UsuariEntitatFields.USUARIID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.USUARIID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.USUARIID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_usuariid_columnvalueid">
          <form:errors path="usuariEntitat.usuariid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <form:hidden path="usuariEntitat.usuariid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.usuariid,__theForm.listOfUsuariForUsuariid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.USUARIID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_usuariid"  onchange="if(typeof onChangeUsuariid == 'function') {  onChangeUsuariid(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.usuariid">
            <c:forEach items="${__theForm.listOfUsuariForUsuariid}" var="tmp">
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,UsuariEntitatFields.ENTITATID)}">
        <tr id="usuariEntitat_entitatid_rowid">
          <td id="usuariEntitat_entitatid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[UsuariEntitatFields.ENTITATID])?'usuariEntitat.entitatid':__theForm.labels[UsuariEntitatFields.ENTITATID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[UsuariEntitatFields.ENTITATID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[UsuariEntitatFields.ENTITATID]}" ></i>
              </c:if>
            </td>
          <td id="usuariEntitat_entitatid_columnvalueid">
          <form:errors path="usuariEntitat.entitatid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <form:hidden path="usuariEntitat.entitatid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.usuariEntitat.entitatid,__theForm.listOfEntitatForEntitatid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,UsuariEntitatFields.ENTITATID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="usuariEntitat_entitatid"  onchange="if(typeof onChangeEntitatid == 'function') {  onChangeEntitatid(this); };"  cssClass="form-control col-md-9-optional" path="usuariEntitat.entitatid">
            <c:forEach items="${__theForm.listOfEntitatForEntitatid}" var="tmp">
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
        
