<%-- ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! --%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>
<un:useConstants var="InfoArxiuFields" className="es.caib.enviafib.model.fields.InfoArxiuFields"/>
  
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ORIGINALFILEURL)}">
        <tr id="infoArxiu_originalfileurl_rowid">
          <td id="infoArxiu_originalfileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ORIGINALFILEURL])?'infoArxiu.originalfileurl':__theForm.labels[InfoArxiuFields.ORIGINALFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ORIGINALFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ORIGINALFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_originalfileurl_columnvalueid">
              <form:errors path="infoArxiu.originalfileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ORIGINALFILEURL)? 'true' : 'false'}" path="infoArxiu.originalfileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_originalfileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_originalfileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.originalfileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.originalfileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.originalfileurl'); ta.wrap='hard';">Hard Wrap</a>
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
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSV)? 'true' : 'false'}" path="infoArxiu.csv"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csv" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csv" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csv'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csv'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csv'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.CSVGENERATIONDEFINITION)}">
        <tr id="infoArxiu_csvgenerationdefinition_rowid">
          <td id="infoArxiu_csvgenerationdefinition_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.CSVGENERATIONDEFINITION])?'infoArxiu.csvgenerationdefinition':__theForm.labels[InfoArxiuFields.CSVGENERATIONDEFINITION]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.CSVGENERATIONDEFINITION]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.CSVGENERATIONDEFINITION]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_csvgenerationdefinition_columnvalueid">
              <form:errors path="infoArxiu.csvgenerationdefinition" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVGENERATIONDEFINITION)? 'true' : 'false'}" path="infoArxiu.csvgenerationdefinition"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csvgenerationdefinition" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csvgenerationdefinition" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvgenerationdefinition'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvgenerationdefinition'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvgenerationdefinition'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.CSVVALIDATIONWEB)}">
        <tr id="infoArxiu_csvvalidationweb_rowid">
          <td id="infoArxiu_csvvalidationweb_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.CSVVALIDATIONWEB])?'infoArxiu.csvvalidationweb':__theForm.labels[InfoArxiuFields.CSVVALIDATIONWEB]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.CSVVALIDATIONWEB]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.CSVVALIDATIONWEB]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_csvvalidationweb_columnvalueid">
              <form:errors path="infoArxiu.csvvalidationweb" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.CSVVALIDATIONWEB)? 'true' : 'false'}" path="infoArxiu.csvvalidationweb"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_csvvalidationweb" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_csvvalidationweb" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvvalidationweb'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvvalidationweb'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.csvvalidationweb'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ARXIUEXPEDIENTID)}">
        <tr id="infoArxiu_arxiuexpedientid_rowid">
          <td id="infoArxiu_arxiuexpedientid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ARXIUEXPEDIENTID])?'infoArxiu.arxiuexpedientid':__theForm.labels[InfoArxiuFields.ARXIUEXPEDIENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ARXIUEXPEDIENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ARXIUEXPEDIENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_arxiuexpedientid_columnvalueid">
              <form:errors path="infoArxiu.arxiuexpedientid" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUEXPEDIENTID)? 'true' : 'false'}" path="infoArxiu.arxiuexpedientid"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiuexpedientid" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiuexpedientid" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiuexpedientid'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiuexpedientid'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiuexpedientid'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ARXIUDOCUMENTID)}">
        <tr id="infoArxiu_arxiudocumentid_rowid">
          <td id="infoArxiu_arxiudocumentid_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ARXIUDOCUMENTID])?'infoArxiu.arxiudocumentid':__theForm.labels[InfoArxiuFields.ARXIUDOCUMENTID]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ARXIUDOCUMENTID]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ARXIUDOCUMENTID]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_arxiudocumentid_columnvalueid">
              <form:errors path="infoArxiu.arxiudocumentid" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ARXIUDOCUMENTID)? 'true' : 'false'}" path="infoArxiu.arxiudocumentid"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_arxiudocumentid" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_arxiudocumentid" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiudocumentid'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiudocumentid'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.arxiudocumentid'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.PRINTABLEURL)}">
        <tr id="infoArxiu_printableurl_rowid">
          <td id="infoArxiu_printableurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.PRINTABLEURL])?'infoArxiu.printableurl':__theForm.labels[InfoArxiuFields.PRINTABLEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.PRINTABLEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.PRINTABLEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_printableurl_columnvalueid">
              <form:errors path="infoArxiu.printableurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.PRINTABLEURL)? 'true' : 'false'}" path="infoArxiu.printableurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_printableurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_printableurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.printableurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.printableurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.printableurl'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.ENIFILEURL)}">
        <tr id="infoArxiu_enifileurl_rowid">
          <td id="infoArxiu_enifileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.ENIFILEURL])?'infoArxiu.enifileurl':__theForm.labels[InfoArxiuFields.ENIFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.ENIFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.ENIFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_enifileurl_columnvalueid">
              <form:errors path="infoArxiu.enifileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.ENIFILEURL)? 'true' : 'false'}" path="infoArxiu.enifileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_enifileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_enifileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.enifileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.enifileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.enifileurl'); ta.wrap='hard';">Hard Wrap</a>
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
        
        <c:if test="${!gen:contains(__theForm.hiddenFields,InfoArxiuFields.VALIDATIONFILEURL)}">
        <tr id="infoArxiu_validationfileurl_rowid">
          <td id="infoArxiu_validationfileurl_columnlabelid">
            <label>
              <fmt:message key="${(empty __theForm.labels[InfoArxiuFields.VALIDATIONFILEURL])?'infoArxiu.validationfileurl':__theForm.labels[InfoArxiuFields.VALIDATIONFILEURL]}" />
             </label>
              <c:if test="${not empty __theForm.help[InfoArxiuFields.VALIDATIONFILEURL]}">
              <i class="fas fa-info-circle" title="${__theForm.help[InfoArxiuFields.VALIDATIONFILEURL]}" ></i>
              </c:if>
            </td>
          <td id="infoArxiu_validationfileurl_columnvalueid">
              <form:errors path="infoArxiu.validationfileurl" cssClass="errorField alert alert-danger" />
  <table style="width:100%">
  <tr>
  <td>
       <form:textarea rows="3" wrap="soft" style="overflow:auto;display: inline;resize:both;" cssClass="form-control col-md-9-optional" readonly="${ gen:contains(__theForm.readOnlyFields ,InfoArxiuFields.VALIDATIONFILEURL)? 'true' : 'false'}" path="infoArxiu.validationfileurl"  />
   </td>
   <td style="width:40px">
      <div id="dropdownMenuButton_validationfileurl" style="vertical-align:top;display:inline;position:relative;">
        <button  class="btn btn-secondary btn-sm dropdown-toggle" type="button" style="margin-left:0px;"><span class="caret"></span></button>
        <div id="dropdownMenuContainer_validationfileurl" class="dropdown-menu dropdown-menu-right">
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.validationfileurl'); ta.wrap='off';" >No Wrap</a>
          <a class="dropdown-item"  href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.validationfileurl'); ta.wrap='soft';">Soft Wrap</a>
          <a class="dropdown-item" href="#" onclick="javascript:var ta=document.getElementById('infoArxiu.validationfileurl'); ta.wrap='hard';">Hard Wrap</a>
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
        
