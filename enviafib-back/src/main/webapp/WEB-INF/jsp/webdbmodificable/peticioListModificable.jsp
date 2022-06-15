<input id="id_urlnavegador" type="hidden" name="urlnavegador" />

<fmt:message key="${__theFilterForm.subTitleCode}" />

<script type="text/javascript">

function cridaEmail(peticioId){
	var mail = window.prompt('<fmt:message key="email.demanar.destinatari"/>','Default');
	//submitTo('enviafib', );
	window.location = '<%= request.getContextPath()%>${contexte}/enviaremail/'+peticioId+'/'+btoa(mail) + '/' + btoa(window.location);
}
</script>