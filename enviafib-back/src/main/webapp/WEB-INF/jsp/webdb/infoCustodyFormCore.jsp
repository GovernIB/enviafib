<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoCustodyFields" className="es.caib.enviafib.model.fields.InfoCustodyFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.CUSTODYID)}">
        <tr id="infoCustody_custodyid_rowid">
          <td id="infoCustody_custodyid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.CUSTODYID])?'infoCustody.custodyid':__theForm.labels[InfoCustodyFields.CUSTODYID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.CUSTODYID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.CUSTODYID]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_custodyid_columnvalueid">
              <form:errors path="infoCustody.custodyid" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.CUSTODYID)? 'true' : 'false'}" path="infoCustody.custodyid"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_custodyid" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_custodyid" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.custodyid'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.custodyid'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.custodyid'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_custodyid').on('click', function(){
					var valor = ($('#dropdownMenuContainer_custodyid').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_custodyid').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.ORIGINALFILEURL)}">
        <tr id="infoCustody_originalfileurl_rowid">
          <td id="infoCustody_originalfileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.ORIGINALFILEURL])?'infoCustody.originalfileurl':__theForm.labels[InfoCustodyFields.ORIGINALFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.ORIGINALFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.ORIGINALFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_originalfileurl_columnvalueid">
              <form:errors path="infoCustody.originalfileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.ORIGINALFILEURL)? 'true' : 'false'}" path="infoCustody.originalfileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_originalfileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_originalfileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.originalfileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.originalfileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.originalfileurl'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_originalfileurl').on('click', function(){
					var valor = ($('#dropdownMenuContainer_originalfileurl').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_originalfileurl').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.CSV)}">
        <tr id="infoCustody_csv_rowid">
          <td id="infoCustody_csv_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.CSV])?'infoCustody.csv':__theForm.labels[InfoCustodyFields.CSV]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.CSV]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.CSV]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_csv_columnvalueid">
              <form:errors path="infoCustody.csv" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.CSV)? 'true' : 'false'}" path="infoCustody.csv"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csv" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csv" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csv'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csv'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csv'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_csv').on('click', function(){
					var valor = ($('#dropdownMenuContainer_csv').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_csv').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.CSVGENERATIONDEFINITION)}">
        <tr id="infoCustody_csvgenerationdefinition_rowid">
          <td id="infoCustody_csvgenerationdefinition_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.CSVGENERATIONDEFINITION])?'infoCustody.csvgenerationdefinition':__theForm.labels[InfoCustodyFields.CSVGENERATIONDEFINITION]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.CSVGENERATIONDEFINITION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.CSVGENERATIONDEFINITION]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_csvgenerationdefinition_columnvalueid">
              <form:errors path="infoCustody.csvgenerationdefinition" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.CSVGENERATIONDEFINITION)? 'true' : 'false'}" path="infoCustody.csvgenerationdefinition"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csvgenerationdefinition" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csvgenerationdefinition" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvgenerationdefinition'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvgenerationdefinition'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvgenerationdefinition'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_csvgenerationdefinition').on('click', function(){
					var valor = ($('#dropdownMenuContainer_csvgenerationdefinition').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_csvgenerationdefinition').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.CSVVALIDATIONWEB)}">
        <tr id="infoCustody_csvvalidationweb_rowid">
          <td id="infoCustody_csvvalidationweb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.CSVVALIDATIONWEB])?'infoCustody.csvvalidationweb':__theForm.labels[InfoCustodyFields.CSVVALIDATIONWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.CSVVALIDATIONWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.CSVVALIDATIONWEB]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_csvvalidationweb_columnvalueid">
              <form:errors path="infoCustody.csvvalidationweb" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.CSVVALIDATIONWEB)? 'true' : 'false'}" path="infoCustody.csvvalidationweb"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csvvalidationweb" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csvvalidationweb" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvvalidationweb'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvvalidationweb'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.csvvalidationweb'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_csvvalidationweb').on('click', function(){
					var valor = ($('#dropdownMenuContainer_csvvalidationweb').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_csvvalidationweb').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.ARXIUEXPEDIENTID)}">
        <tr id="infoCustody_arxiuexpedientid_rowid">
          <td id="infoCustody_arxiuexpedientid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.ARXIUEXPEDIENTID])?'infoCustody.arxiuexpedientid':__theForm.labels[InfoCustodyFields.ARXIUEXPEDIENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.ARXIUEXPEDIENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.ARXIUEXPEDIENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_arxiuexpedientid_columnvalueid">
              <form:errors path="infoCustody.arxiuexpedientid" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.ARXIUEXPEDIENTID)? 'true' : 'false'}" path="infoCustody.arxiuexpedientid"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiuexpedientid" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiuexpedientid" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiuexpedientid'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiuexpedientid'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiuexpedientid'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_arxiuexpedientid').on('click', function(){
					var valor = ($('#dropdownMenuContainer_arxiuexpedientid').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_arxiuexpedientid').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.ARXIUDOCUMENTID)}">
        <tr id="infoCustody_arxiudocumentid_rowid">
          <td id="infoCustody_arxiudocumentid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.ARXIUDOCUMENTID])?'infoCustody.arxiudocumentid':__theForm.labels[InfoCustodyFields.ARXIUDOCUMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.ARXIUDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.ARXIUDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_arxiudocumentid_columnvalueid">
              <form:errors path="infoCustody.arxiudocumentid" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.ARXIUDOCUMENTID)? 'true' : 'false'}" path="infoCustody.arxiudocumentid"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiudocumentid" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiudocumentid" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiudocumentid'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiudocumentid'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.arxiudocumentid'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_arxiudocumentid').on('click', function(){
					var valor = ($('#dropdownMenuContainer_arxiudocumentid').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_arxiudocumentid').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.PRINTABLEURL)}">
        <tr id="infoCustody_printableurl_rowid">
          <td id="infoCustody_printableurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.PRINTABLEURL])?'infoCustody.printableurl':__theForm.labels[InfoCustodyFields.PRINTABLEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.PRINTABLEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.PRINTABLEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_printableurl_columnvalueid">
              <form:errors path="infoCustody.printableurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.PRINTABLEURL)? 'true' : 'false'}" path="infoCustody.printableurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_printableurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_printableurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.printableurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.printableurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.printableurl'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_printableurl').on('click', function(){
					var valor = ($('#dropdownMenuContainer_printableurl').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_printableurl').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.ENIFILEURL)}">
        <tr id="infoCustody_enifileurl_rowid">
          <td id="infoCustody_enifileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.ENIFILEURL])?'infoCustody.enifileurl':__theForm.labels[InfoCustodyFields.ENIFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.ENIFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.ENIFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_enifileurl_columnvalueid">
              <form:errors path="infoCustody.enifileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.ENIFILEURL)? 'true' : 'false'}" path="infoCustody.enifileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_enifileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_enifileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.enifileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.enifileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.enifileurl'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_enifileurl').on('click', function(){
					var valor = ($('#dropdownMenuContainer_enifileurl').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_enifileurl').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.VALIDATIONFILEURL)}">
        <tr id="infoCustody_validationfileurl_rowid">
          <td id="infoCustody_validationfileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.VALIDATIONFILEURL])?'infoCustody.validationfileurl':__theForm.labels[InfoCustodyFields.VALIDATIONFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.VALIDATIONFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.VALIDATIONFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_validationfileurl_columnvalueid">
              <form:errors path="infoCustody.validationfileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.VALIDATIONFILEURL)? 'true' : 'false'}" path="infoCustody.validationfileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_validationfileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_validationfileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.validationfileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoCustody.validationfileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoCustody.validationfileurl'); ta.wrap='hard';">Hard Wrap</a>
        </div>
      </div>
      <script type="text/javascript">
			$('#dropdownMenuButton_validationfileurl').on('click', function(){
					var valor = ($('#dropdownMenuContainer_validationfileurl').css('display') != 'none') ? 'none' : 'block';
                 $('#dropdownMenuContainer_validationfileurl').css('display', valor);
                 return false;
				});
      </script>   </td>
   </tr>
   </table>
           </td>
        </tr>
        </c:if>
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoCustodyFields.PETICIOID)}">
        <tr id="infoCustody_peticioid_rowid">
          <td id="infoCustody_peticioid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoCustodyFields.PETICIOID])?'infoCustody.peticioid':__theForm.labels[InfoCustodyFields.PETICIOID]}" /> &nbsp;(*)
             </label>
              <c:if test="${not empty __theForm.help[InfoCustodyFields.PETICIOID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoCustodyFields.PETICIOID]}" ></i>
              </c:if>
            </td>
          <td id="infoCustody_peticioid_columnvalueid">
          <form:errors path="infoCustody.peticioid" cssClass="errorField alert alert-danger" />
          <c:if test="${gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.PETICIOID)}" >
          <form:hidden path="infoCustody.peticioid"/>
          <input type="text" readonly="true" class="form-control col-md-9-optional uneditable-input" value="${gen:findValue(__theForm.infoCustody.peticioid,__theForm.listOfPeticioForPeticioid)}"  />
          </c:if>
          <c:if test="${!gen:contains(__theForm.readOnlyFields ,InfoCustodyFields.PETICIOID)}" >
          <c:set var="containEmptyValue"  value="false" />
          <form:select id="infoCustody_peticioid"  onchange="if(typeof onChangePeticioid == 'function') {  onChangePeticioid(this); };"  cssClass="form-control col-md-9-optional" path="infoCustody.peticioid">
            <c:forEach items="${__theForm.listOfPeticioForPeticioid}" var="tmp">
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
        
