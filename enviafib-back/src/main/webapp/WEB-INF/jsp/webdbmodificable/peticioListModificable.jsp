<script type="text/javascript">

function cridaEmail(peticioId){
	var mail = window.prompt('<fmt:message key="email.demanar.destinatari"/>','');
	if(mail){
		window.location = '<%= request.getContextPath()%>${contexte}/enviaremail/'+peticioId+'/'+btoa(mail) + '/' + btoa(window.location);	
	}else{
		var alertVar = window.alert('<fmt:message key="email.demanar.nodestinatari"/>');
	}
}
</script>