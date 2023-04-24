<script type="text/javascript">

function cridaEmail(peticioId){
	var mail = window.prompt('<fmt:message key="email.demanar.destinatari"/>','');
	if(mail){
		window.location = '<%=request.getContextPath()%>${contexte}/enviaremail/'+peticioId+'/'+btoa(mail) + '/' + btoa(window.location);	
	} else {
		window.alert('<fmt:message key="email.demanar.nodestinatari"/>');
	}
}

function reintentarArxivat(peticioId) {
	$('#spinnerModal').modal(
			{
				backdrop : "static",
				keyboard: false
		    });
	var base = '<%=request.getContextPath()%>${contexte}/reintentararxivat/';
	window.location = base +peticioId + '/' + btoa(window.location);
}

function reintentarTancamentExpedient(peticioId) {
    $('#spinnerModal').modal(
            {
                backdrop : "static",
                keyboard: false
            });
    window.location = '<%=request.getContextPath()%>
	${contexte}/reintentartancamentexpedient/'
				+ peticioId + "/" + btoa(window.location);
}

function reintentarArxivarTotes() {
    $('#spinnerModal').modal(
            {
                backdrop : "static",
                keyboard: false
            });
    var base = '<%=request.getContextPath()%>${contexte}/reintentarArxivarTotes/';
    window.location = base + btoa(window.location);
}




</script>


<div class="modal fade" style="display: none" id="spinnerModal"
	tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">
					<fmt:message key="arxiu.reintent.loading" />
				</h5>
				<button type="button" class="close" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" style="text-align: center;">
				<span class="fa fa-spinner fa-pulse fa-3x"></span>
			</div>
		</div>
	</div>
</div>




<!-- PANTALLA DE FILTRES MODERNA -->


<div id="page-header" class="float-right">
	<div id="botoneraContainer">
		<a class="float-right" style="margin-left: 10px" href="#"> <i
			title="<fmt:message key="genapp.form.hidefilter"/>"
			onclick="document.getElementById('FilterDiv').style.display='none'; document.getElementById('FilterButton').style.display='inline';"
			class="far fa-window-close"></i>
		</a> <input id="btn-clean" class="btn btn-filtre float-right" type="button" onclick="clear_form_elements(this.form)"
			value="<fmt:message key="genapp.form.clean"/>" />
		<input id="btn-submit" class="btn btn-filtre btn-secondary float-right" type="submit" value="<fmt:message key="genapp.form.search"/>" />
	</div>
</div>

	<c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.NOM)}">
		<div id="page-body" class="form-inline">
			<%-- FILTRE STRING - NOM --%>
			<div class="filtre-div input-prepend">
				<fmt:message key="peticio.nom" var="nom" />
				<form:input cssClass="form-control search-query input-medium"
					placeholder="${nom}" path="nom" />
			</div>
	</c:if>

<c:if test="${gen:contains(__theFilterForm.filterByFields, PeticioFields.ESTAT)}">
	<%-- FILTRE NUMERO --%>
	<div class="filtre-div input-group">
		<div id="estatContainer" class="">
			<div class="form-group group-item float-left">
				<div class="input-group" id="estatDesde" data-target-input="nearest">
					<fmt:message key="estat.from" var="estatFrom" />
					<form:input cssClass="form-control search-query input-medium"
						data-target="estatDesde" path="estatDesde"
						placeholder="${estatFrom}..." />
				</div>
			</div>
			<div class="form-group group-item float-right">
				<div class="input-group" id="estatFins" data-target-input="nearest">
					<fmt:message key="estat.to" var="estatTo" />

					<form:input cssClass="form-control search-query input-medium"
						data-target="#estatFins" path="estatFins"
						placeholder="${estatTo}..." />
				</div>
			</div>
		</div>
	</div>
</c:if>

<%-- FILTRE DATE-TIME --%>
            <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.DATACREACIO)}">
	<div class="filtre-div input-group">
		<div id="dataCreacioContainer" class="">
			<div class="form-group group-item float-left">
				<div class="input-group date" id="dataCreacioDesde"
					data-target-input="nearest">
					<fmt:message key="datacreacio.from" var="dateFrom" />

					<form:input cssClass="form-control datetimepicker-input"
						data-target="#dataCreacioDesde" path="dataCreacioDesde"
						placeholder="${dateFrom}..." />

					<div class="input-group-append" data-target="#dataCreacioDesde"
						data-toggle="datetimepicker">
						<div class="input-group-text">
							<i class="fa fa-calendar"></i>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#dataCreacioDesde').datetimepicker({
							format : '${gen:getJSDateTimePattern()}',
							locale : '${lang}',
							icons : {
								time : 'far fa-clock'
							}
						});
					});
				</script>
			</div>
			<div class="form-group group-item float-right">
				<div class="input-group date" id="dataCreacioFins"
					data-target-input="nearest">
					<fmt:message key="datacreacio.to" var="dateTo" />

					<form:input cssClass="form-control datetimepicker-input"
						data-target="#dataCreacioFins" path="dataCreacioFins"
						placeholder="${dateTo}..." />

					<div class="input-group-append" data-target="#dataCreacioFins"
						data-toggle="datetimepicker">
						<div class="input-group-text">
							<i class="fa fa-calendar"></i>
						</div>
					</div>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#dataCreacioFins').datetimepicker({
							format : '${gen:getJSDateTimePattern()}',
							locale : '${lang}',
							icons : {
								time : 'far fa-clock'
							}
						});
					});
				</script>
			</div>
		</div>
	</div>
	      </c:if>
	      
	       <c:if test="${gen:contains(__theFilterForm.filterByFields ,PeticioFields.DATAFINAL)}">
<%-- FILTRE DATE-TIME --%>
<div class="filtre-div input-group">
        <div id="dataCreacioContainer" class="">
            <div class="form-group group-item float-left">
                <div class="input-group date" id="dataFinalDesde"
                    data-target-input="nearest">
                    <fmt:message key="datafinal.from" var="dateFrom" />

                    <form:input cssClass="form-control datetimepicker-input"
                        data-target="dataFinalDesde" path="dataFinalDesde"
                        placeholder="${dateFrom}..." />

                    <div class="input-group-append" data-target="dataFinalDesde"
                        data-toggle="datetimepicker">
                        <div class="input-group-text">
                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function() {
                        $('#dataFinalDesde').datetimepicker({
                            format : '${gen:getJSDateTimePattern()}',
                            locale : '${lang}',
                            icons : {
                                time : 'far fa-clock'
                            }
                        });
                    });
                </script>
            </div>
            <div class="form-group group-item float-right">
                <div class="input-group date" id="dataFinalFins"
                    data-target-input="nearest">
                    <fmt:message key="datafinal.to" var="dateTo" />

                    <form:input cssClass="form-control datetimepicker-input"
                        data-target="dataFinalFins" path="dataFinalFins"
                        placeholder="${dateTo}..." />

                    <div class="input-group-append" data-target="dataFinalFins"
                        data-toggle="datetimepicker">
                        <div class="input-group-text">
                            <i class="fa fa-calendar"></i>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    $(function() {
                        $('#dataFinalFins').datetimepicker({
                            format : '${gen:getJSDateTimePattern()}',
                            locale : '${lang}',
                            icons : {
                                time : 'far fa-clock'
                            }
                        });
                    });
                </script>
            </div>
        </div>
    </div>
          </c:if>
</div>


<script type="text/javascript">
	$("#FilterDiv").html("");
	$("#FilterDiv").append($("#page-header"));
	$("#FilterDiv").append($("#page-body"));
</script>

<style>
#FilterDiv {
    padding: 0.5rem !important;
	margin-bottom: 1.25rem !important;
	background-image:
        url(<c:url value="/img/background-pattern.png"></c:url>);
		!important;
    min-width: 41rem;
}

#page-header {
	padding-top: 0.5rem;
}

.btn-filtre {
	margin-left: 3px;
	padding: 0.25rem 0.75rem;
}

.filtre-div {
	min-width: 28rem;
	max-width: 45rem;
	margin: 0.5rem 2rem 0.5rem 0.5rem;
	width: 40% !important;
}

.group-item {
	width: 48%;
}

.form-control {
	padding: 0.25rem 0.75rem !important;
}

.form-group {
	margin-left: 0rem;
}

#btn-clean {
	background: #FDFFFC;
	border-color: #888888;
}

#btn-reset {
	background: #FF9523;
	border-color: #FF9523;
}

/* #btn-submit {
	background: #246EB9;
	border-color: #246EB9;
	color: #FDFFFC;
}
 */
.search-query {
	width: 100% !important;
}
</style>



<!-- LLISTAT MODERN -->
<script type="text/javascript">
	$("table").parent().removeClass("row");

	$('th:first-child').css('vertical-align', 'middle');

	$('td:first-child').each(function() {
		var str = $(this).html();
		str = str.replace("&nbsp;", "");
		$(this).html(str);
	});

	$('#peticio_listheader label').addClass('titol-llistat');
</script>

<style>

table {
	width: 100% !important;
	text-align: center;
}

th {
	border-bottom-width: 3px !important;
	background-image: none !important;
}

th span {
	cursor: pointer !important;
}

td:nth-child(1), td:nth-child(5) {
	vertical-align: middle !important;
}
td:nth-child(2) {
    text-align: left;
    padding-left: 1rem;
}


td:last-child {
	width: 0px;
	text-align: left;
}

.titol-llistat {
	font-weight: 400 !important;
	margin: 0px;
}

.a_item {
	margin: 0 !important;
	color: black !important;
	background-color: transparent !important;
	border: none;
}

.a_item:hover {
	color: white !important;
}

.a_item svg {
	min-width: 1rem;
}

.dropdown .btn-secondary {
	margin: 0 5px;
}
.dropdown-menu {
    margin-top: 0px;
}

</style>



<div class="modal fade" style="display: none" id="fluxInfoModal"
    tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    <fmt:message key="peticio.portafib.flux.info" />
                </h5>
                <button type="button" class="close" aria-label="Close" onclick="$('#fluxInfoModal').modal('hide')">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
			<div class="modal-body" style="text-align: center;">
				<iframe width="450px" height="400px" id="iFramefluxInfo"></iframe>
			</div>
		</div>
    </div>
</div>


<script type="text/javascript">

function openModalFluxInfo(peticioID) {
	var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
        	    var urlFluxInfo = this.responseText;
        	    $('#fluxInfoModal').on('shown.bs.modal',function(){
        	        $(this).find('iframe').attr('src',urlFluxInfo);
        	    });
        	        
        	    $("#fluxInfoModal").modal('show');
         }
    };
    
    var urlEnviaFIB = '<%=request.getContextPath()%>${contexte}/geturlflow/' + peticioID;
    xhttp.open("GET", urlEnviaFIB, true);

    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send('');
}


</script>