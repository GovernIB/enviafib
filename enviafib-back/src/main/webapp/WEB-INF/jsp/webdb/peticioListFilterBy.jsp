<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="PeticioFields" className="es.caib.enviafib.model.fields.PeticioFields"/>

  <%-- HIDDEN PARAMS: FILTER BY --%> 
  <form:hidden path="visibleFilterBy"/>

  <%-- FILTRAR PER - INICI --%>
  
  <c:set var="displayFilterDiv" value="${__theFilterForm.visibleFilterBy?'':'display:none;'}" />  
  
  <div id="FilterDiv" class="wellgroupfilter formbox" style="${displayFilterDiv} margin-bottom:3px; margin-left: 1px; padding:3px;">

      <div class="page-header">
        <fmt:message key="genapp.form.filterby"/>
        
        <div class="float-right">

           <a class="float-right" style="margin-left:10px" href="#"> <i title="<fmt:message key="genapp.form.hidefilter"/>" onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';" class="far fa-window-close"></i></a>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="button" onclick="clear_form_elements(this.form)" value="<fmt:message key="genapp.form.clean"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-warning float-right" type="reset" value="<fmt:message key="genapp.form.reset"/>"/>
           <input style="margin-left: 3px" class="btn btn-sm btn-primary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>"/>

        </div>
      </div>
      <div class="form-inline">
      
      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key < 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>


        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.NOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.nom" var="nom" />
              <fmt:message key="genapp.form.searchby" var="cercapernom" >                
                 <fmt:param value="${nom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${nom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapernom}" path="nom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.PETICIOID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.peticioID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="peticioIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="peticioIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.DATACREACIO)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="peticio.dataCreacio" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group">
                <div class="input-group date" id="dataCreacioDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioDesde" path="dataCreacioDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataCreacioDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group">
                <div class="input-group date" id="dataCreacioFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataCreacioFins" path="dataCreacioFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataCreacioFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataCreacioFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.DATAFINAL)}">
<%-- FILTRE DATE-TIME --%>
            <div class="input-group" style="padding-right:4px;padding-bottom:4px;align-items:center;">
              <span class="add-on"><fmt:message key="peticio.dataFinal" />:</span>
              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
            <div class="form-group">
                <div class="input-group date" id="dataFinalDesde" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFinalDesde" path="dataFinalDesde" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFinalDesde"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataFinalDesde').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>
            <div class="form-group">
                <div class="input-group date" id="dataFinalFins" data-target-input="nearest">
                      <form:input  cssClass="form-control datetimepicker-input"  data-target="#dataFinalFins" path="dataFinalFins" />
                    <c:if test="${!false}" >
                    <div class="input-group-append"  data-target="#dataFinalFins"  data-toggle="datetimepicker">
                        <div class="input-group-text"><i class="fa fa-calendar"></i></div>
                    </div>
                    </c:if>
                </div>
            </div>
        <script type="text/javascript">
            $(function () {
                $('#dataFinalFins').datetimepicker({
                    format: '${gen:getJSDateTimePattern()}',
                    locale: '${lang}',
                    icons: {
                       time: 'far fa-clock'
                    }
                });
            });
        </script>            </div>

    
        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.SOLICITANTID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.solicitantID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="solicitantIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="solicitantIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.IDIOMAID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.idiomaID" var="idiomaID" />
              <fmt:message key="genapp.form.searchby" var="cercaperidiomaID" >                
                 <fmt:param value="${idiomaID}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${idiomaID}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperidiomaID}" path="idiomaID" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.DESTINATARINIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.destinatariNif" var="destinatariNif" />
              <fmt:message key="genapp.form.searchby" var="cercaperdestinatariNif" >                
                 <fmt:param value="${destinatariNif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${destinatariNif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperdestinatariNif}" path="destinatariNif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ESTAT)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.estat" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="estatDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="estatFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.TIPUSDOCUMENTAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.tipusDocumental" var="tipusDocumental" />
              <fmt:message key="genapp.form.searchby" var="cercapertipusDocumental" >                
                 <fmt:param value="${tipusDocumental}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${tipusDocumental}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapertipusDocumental}" path="tipusDocumental" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.IDIOMADOC)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.idiomaDoc" var="idiomaDoc" />
              <fmt:message key="genapp.form.searchby" var="cercaperidiomaDoc" >                
                 <fmt:param value="${idiomaDoc}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${idiomaDoc}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperidiomaDoc}" path="idiomaDoc" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.INFOSIGNATURAID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.infoSignaturaID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="infoSignaturaIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="infoSignaturaIDFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.TIPUS)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.tipus" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="tipusDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="tipusFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ERRORMSG)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.errorMsg" var="errorMsg" />
              <fmt:message key="genapp.form.searchby" var="cercapererrorMsg" >                
                 <fmt:param value="${errorMsg}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${errorMsg}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapererrorMsg}" path="errorMsg" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ERROREXCEPTION)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.errorException" var="errorException" />
              <fmt:message key="genapp.form.searchby" var="cercapererrorException" >                
                 <fmt:param value="${errorException}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${errorException}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercapererrorException}" path="errorException" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.PETICIOPORTAFIRMES)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.peticioPortafirmes" var="peticioPortafirmes" />
              <fmt:message key="genapp.form.searchby" var="cercaperpeticioPortafirmes" >                
                 <fmt:param value="${peticioPortafirmes}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${peticioPortafirmes}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperpeticioPortafirmes}" path="peticioPortafirmes" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.REASON)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.reason" var="reason" />
              <fmt:message key="genapp.form.searchby" var="cercaperreason" >                
                 <fmt:param value="${reason}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${reason}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperreason}" path="reason" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUFUNCIONARIUSERNAME)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuFuncionariUsername" var="arxiuFuncionariUsername" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuFuncionariUsername" >                
                 <fmt:param value="${arxiuFuncionariUsername}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuFuncionariUsername}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuFuncionariUsername}" path="arxiuFuncionariUsername" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUPARAMFUNCIONARINOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuParamFuncionariNom" var="arxiuParamFuncionariNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuParamFuncionariNom" >                
                 <fmt:param value="${arxiuParamFuncionariNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuParamFuncionariNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuParamFuncionariNom}" path="arxiuParamFuncionariNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUPARAMFUNCIONARINIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuParamFuncionariNif" var="arxiuParamFuncionariNif" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuParamFuncionariNif" >                
                 <fmt:param value="${arxiuParamFuncionariNif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuParamFuncionariNif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuParamFuncionariNif}" path="arxiuParamFuncionariNif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUPARAMFUNCIONARIDIR3)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuParamFuncionariDir3" var="arxiuParamFuncionariDir3" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuParamFuncionariDir3" >                
                 <fmt:param value="${arxiuParamFuncionariDir3}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuParamFuncionariDir3}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuParamFuncionariDir3}" path="arxiuParamFuncionariDir3" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMDOCESTATELABORA)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuReqParamDocEstatElabora" var="arxiuReqParamDocEstatElabora" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuReqParamDocEstatElabora" >                
                 <fmt:param value="${arxiuReqParamDocEstatElabora}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuReqParamDocEstatElabora}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuReqParamDocEstatElabora}" path="arxiuReqParamDocEstatElabora" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMINTERESSATS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuReqParamInteressats" var="arxiuReqParamInteressats" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuReqParamInteressats" >                
                 <fmt:param value="${arxiuReqParamInteressats}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuReqParamInteressats}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuReqParamInteressats}" path="arxiuReqParamInteressats" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMCIUTADANIF)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuReqParamCiutadaNif" var="arxiuReqParamCiutadaNif" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuReqParamCiutadaNif" >                
                 <fmt:param value="${arxiuReqParamCiutadaNif}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuReqParamCiutadaNif}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuReqParamCiutadaNif}" path="arxiuReqParamCiutadaNif" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMCIUTADANOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuReqParamCiutadaNom" var="arxiuReqParamCiutadaNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuReqParamCiutadaNom" >                
                 <fmt:param value="${arxiuReqParamCiutadaNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuReqParamCiutadaNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuReqParamCiutadaNom}" path="arxiuReqParamCiutadaNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMORGANS)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuReqParamOrgans" var="arxiuReqParamOrgans" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuReqParamOrgans" >                
                 <fmt:param value="${arxiuReqParamOrgans}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuReqParamOrgans}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuReqParamOrgans}" path="arxiuReqParamOrgans" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuOptParamProcedimentCodi" var="arxiuOptParamProcedimentCodi" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuOptParamProcedimentCodi" >                
                 <fmt:param value="${arxiuOptParamProcedimentCodi}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuOptParamProcedimentCodi}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuOptParamProcedimentCodi}" path="arxiuOptParamProcedimentCodi" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuOptParamProcedimentNom" var="arxiuOptParamProcedimentNom" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuOptParamProcedimentNom" >                
                 <fmt:param value="${arxiuOptParamProcedimentNom}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuOptParamProcedimentNom}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuOptParamProcedimentNom}" path="arxiuOptParamProcedimentNom" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuOptParamSerieDocumental" var="arxiuOptParamSerieDocumental" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuOptParamSerieDocumental" >                
                 <fmt:param value="${arxiuOptParamSerieDocumental}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuOptParamSerieDocumental}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuOptParamSerieDocumental}" path="arxiuOptParamSerieDocumental" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUOPTPARAMEXPEDIENTID)}">
            <%-- FILTRE STRING --%>
            <div class="input-prepend" style="padding-right: 4px;padding-bottom: 4px;">
              <fmt:message key="peticio.arxiuOptParamExpedientId" var="arxiuOptParamExpedientId" />
              <fmt:message key="genapp.form.searchby" var="cercaperarxiuOptParamExpedientId" >                
                 <fmt:param value="${arxiuOptParamExpedientId}"/>
              </fmt:message>
              <span class="add-on"><c:out value="${arxiuOptParamExpedientId}" />:</span>
              <form:input cssClass="search-query input-medium" placeholder="${cercaperarxiuOptParamExpedientId}" path="arxiuOptParamExpedientId" />
            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.ARXIUREQPARAMORIGEN)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.arxiuReqParamOrigen" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="arxiuReqParamOrigenDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="arxiuReqParamOrigenFins" />

            </div>


        </c:if>
        <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.INFOARXIUID)}">
            <%-- FILTRE NUMERO --%>      
            <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
              <span class="add-on"><fmt:message key="peticio.infoArxiuID" />:</span>

              <span class="add-on">&nbsp;<fmt:message key="genapp.from" /></span>
              
              <form:input cssClass="input-append input-small" path="infoArxiuIDDesde" />


              <span class="add-on">&nbsp;<fmt:message key="genapp.to" />&nbsp;</span>

              <form:input cssClass="input-append input-small search-query" path="infoArxiuIDFins" />

            </div>


        </c:if>

      <c:forEach var="__entry" items="${__theFilterForm.additionalFields}">
      <c:if test="${ __entry.key >= 0 && not empty __entry.value.searchBy }">
      <div class="input-group" style="padding-right: 4px;padding-bottom: 4px;">
        <span class="add-on"><fmt:message key="${__entry.value.codeName}" />:</span>
        <fmt:message key="genapp.form.searchby" var="cercaperAF" >
          <fmt:param>
            <fmt:message key="${__entry.value.codeName}" />
          </fmt:param>
        </fmt:message>
        <c:choose>
          <c:when test="${gen:isFieldSearchInRange(__entry.value.searchBy)}">
            <span class="add-on"><fmt:message key="genapp.from" /></span>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="input-small input-medium" type="text" value="${__entry.value.searchByValue}"/>
            <span class="add-on"><fmt:message key="genapp.to" /></span>
            <input id="${__entry.value.searchBy.fullName}Fins" name="${__entry.value.searchBy.fullName}Fins" class="input-small input-medium search-query" type="text" value="${__entry.value.searchByValueFins}"/>
          </c:when>
          <c:otherwise>
            <input id="${__entry.value.searchBy.fullName}" name="${__entry.value.searchBy.fullName}" class="search-query input-medium" placeholder="${cercaperAF}" type="text" value="${__entry.value.searchByValue}"/>
          </c:otherwise>
        </c:choose>
      </div>
      </c:if>
      </c:forEach>
      </div>
    </div>



    <%-- FILTRAR PER - FINAL --%>
  
