<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="AvisFields" className="es.caib.enviafib.model.fields.AvisFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.MISSATGE)}">
        <tr id="avis_missatge_rowid">
          <td id="avis_missatge_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.MISSATGE])?'avis.missatge':__theForm.labels[AvisFields.MISSATGE]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.MISSATGE]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.MISSATGE]}" ></i>
              </c:if>
            </td>
          <td id="avis_missatge_columnvalueid">
              <form:errors path="avis.missatge" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,AvisFields.MISSATGE)? 'true' : 'false'}" path="avis.missatge"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_missatge" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_missatge" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('avis.missatge'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('avis.missatge'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('avis.missatge'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_missatge').on('click', function(){
					var valor = ($('#dropdownMenuContainer_missatge').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_missatge').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.DATAINICI)}">
        <tr id="avis_datainici_rowid">
          <td id="avis_datainici_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.DATAINICI])?'avis.datainici':__theForm.labels[AvisFields.DATAINICI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.DATAINICI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.DATAINICI]}" ></i>
              </c:if>
            </td>
          <td id="avis_datainici_columnvalueid">
    <form:errors path="avis.datainici" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="avis_datainici" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AvisFields.DATAINICI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#avis_datainici" path="avis.datainici" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.DATAINICI)}" >
                    <div class="input-group-append"  data-target="#avis_datainici"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#avis_datainici').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.DATAFI)}">
        <tr id="avis_datafi_rowid">
          <td id="avis_datafi_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.DATAFI])?'avis.datafi':__theForm.labels[AvisFields.DATAFI]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.DATAFI]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.DATAFI]}" ></i>
              </c:if>
            </td>
          <td id="avis_datafi_columnvalueid">
    <form:errors path="avis.datafi" cssClass="errorField alert alert-danger" />
            <div class="form-group"  style="margin-bottom: 0px;" >
                <div class="input-group date" id="avis_datafi" data-target-input="nearest">
                      <form:input readonly="${ gen:contains(__theForm.readOnlyFields ,AvisFields.DATAFI)? 'true' : 'false'}" cssClass="form-control datetimepicker-input"  data-target="#avis_datafi" path="avis.datafi" />
                    <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.DATAFI)}" >
                    <div class="input-group-append"  data-target="#avis_datafi"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#avis_datafi').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.ACTIU)}">
        <tr id="avis_actiu_rowid">
          <td id="avis_actiu_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.ACTIU])?'avis.actiu':__theForm.labels[AvisFields.ACTIU]}" />
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.ACTIU]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.ACTIU]}" ></i>
              </c:if>
            </td>
          <td id="avis_actiu_columnvalueid">
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.ACTIU)}" >
              <form:errors path="avis.actiu" cssClass="errorField alert alert-danger" />
              <form:checkbox cssClass="" onclick="javascript:return ${ gen:contains(__theForm.readOnlyFields ,AvisFields.ACTIU)? 'false' : 'true'}" path="avis.actiu" />
          </c:if>
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.ACTIU)}" >
                <fmt:message key="genapp.checkbox.${__theForm.avis.actiu}" />
          </c:if>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,AvisFields.TIPUS)}">
        <tr id="avis_tipus_rowid">
          <td id="avis_tipus_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[AvisFields.TIPUS])?'avis.tipus':__theForm.labels[AvisFields.TIPUS]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[AvisFields.TIPUS]}">
              <i class="fas fa-info-circle" title="${__theForm.help[AvisFields.TIPUS]}" ></i>
              </c:if>
            </td>
          <td id="avis_tipus_columnvalueid">
          <form:errors path="avis.tipus" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,AvisFields.TIPUS)}" >
          <form:hidden path="avis.tipus"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.avis.tipus,__theForm.listOfValuesForTipus)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,AvisFields.TIPUS)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="avis_tipus"  onchange="if(typeof onChangeTipus == 'function') {  onChangeTipus(this); };"  cssClass="form-control col-md-9-optional" path="avis.tipus">
            <c:forEach items="${__theForm.listOfValuesForTipus}" var="tmp">
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
        
