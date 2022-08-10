<script type="text/javascript">

function provarPlugin(pluginID){
//	var mail = window.prompt('<fmt:message key="email.demanar.destinatari"/>','');
	var username = window.prompt("Introdueix el nom d'usuari del que vulguis informació",'');
	if(!username){
		username = '${loginInfo.username}';
	}
	window.location = '<%= request.getContextPath()%>${contexte}/provar/'+ pluginID + "/" + username;	
}
</script>