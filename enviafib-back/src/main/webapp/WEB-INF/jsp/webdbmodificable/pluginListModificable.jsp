<script type="text/javascript">
function provarPlugin(pluginID){
	var username = window.prompt("Introdueix el nom d'usuari del que vulguis informació",'${loginInfo.username}');
	if(username) {
	    window.location = "<%=request.getContextPath()%>${contexte}/provar/" + pluginID + "/" + username;
    }
}
</script>