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
	var base = '<%= request.getContextPath()%>${contexte}/reintentararxivat/';
	window.location = base +peticioId + '/' + btoa(window.location);
}

function reintentarTancamentExpedient(peticioId) {
    $('#spinnerModal').modal(
            {
                backdrop : "static",
                keyboard: false
            });
    window.location = '<%=request.getContextPath()%>${contexte}/reintentartancamentexpedient/' + peticioId + "/" + btoa(window.location);
}

</script>


<div class="modal fade" style="display:none" id="spinnerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="arxiu.reintent.loading"/></h5>
        <button type="button" class="close" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" style="text-align: center;">
         <span class="fa fa-spinner fa-pulse fa-3x"></span
      </div>      
    </div>
  </div>
</div>