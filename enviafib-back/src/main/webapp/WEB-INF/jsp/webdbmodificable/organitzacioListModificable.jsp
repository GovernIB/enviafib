

<script>

function regenerar() {
   var dir3 = prompt("Introdueix el codi arrel DIR3 de l'organització:", "A04003003");
   if (dir3 != null) {
       var context = "<%=request.getContextPath()%>";
       document.location.href =  context + "${contexte}/regenerar/" + dir3;
   }
}

</script>