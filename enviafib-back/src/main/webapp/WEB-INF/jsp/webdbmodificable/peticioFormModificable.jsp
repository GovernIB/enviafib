<%@page import="es.caib.enviafib.model.fields.PeticioFields"%>

<c:if test="${not empty dragdrop}">

    <div class="droparea">

        <img id="res" src="<c:url value="/img/dragndrop.png"/>" />

        <p class="dragdrop_text">
            <fmt:message key="dragdrop.text" />
        </p>

        <input type="hidden" name="myHiddenInput" id="myHiddenInput">
        <input type="hidden" name="fileInfoFlag" id="fileInfoFlag">

        <div id="progress_bar" class="progress" style="display: none;">
            <div class="progress-bar progress-bar-striped active"
                role="progressbar" aria-valuenow="0" aria-valuemin="0"
                aria-valuemax="100" style="width: 0%;">0%</div>
        </div>

    </div>
    <div class="loader" style="display: none; list-style-type: none;"></div>

    <div id="btn-clear-files" class="btn-danger" onclick="clearAllFiles()"><fmt:message key="btn.clear.all.files" /></div>
    <ul id="ul_files"></ul>


	<script type="text/javascript">    

    var ALL_FILES = [];
    var FLAG = "";
    
    const initApp = () => {
        const droparea = document.querySelector('.droparea');
        
        const active = () => droparea.classList.add("draging");

        const inactive = () => droparea.classList.remove("draging");

        const prevents = (e) => e.preventDefault();

        ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(evtName => {
            droparea.addEventListener(evtName, prevents);
        });

        ['dragenter', 'dragover'].forEach(evtName => {
            droparea.addEventListener(evtName, active);
        });

        ['dragleave', 'drop'].forEach(evtName => {
            droparea.addEventListener(evtName, inactive);
        });

        droparea.addEventListener("drop", handleDrop);
        
//        $(".tab_container").append('<h5><fmt:message key="dragdrop.title"/><h5>');   
        $("#peticio_tableid").append($("#peticio_idiomaDoc_rowid"));   
        $("#peticio_tableid").append($("#peticio_fitxerID_rowid"));   
        
        $(".tab_container").append($("#btn-clear-files"));   
        $(".tab_container").append($("#ul_files"));   
        $(".tab_container").append($(".droparea"));  
        
        $("#btn-clear-files").hide();
        
        var pdfFilesInput = document.getElementById('fitxerID');
        pdfFilesInput.setAttribute('multiple', true);
        pdfFilesInput.setAttribute('accept', '.pdf');
    }

    document.addEventListener("DOMContentLoaded", initApp);

    const handleDrop = (e) => {
    	$('.loader').show(); // show loader
        
        var files = e.dataTransfer.files;
        
        for (var i = 0; i < files.length; i++) {
        	var myFile = files.item(i);
        	myFile.tipo = "fitxer";
        	if (myFile.type ===  "application/pdf") {
        	    ALL_FILES.push(myFile);
                FLAG += "F";
			}
        }
        
        refreshFileList(ALL_FILES);

         setTimeout(function(){
            $('.loader').hide(); // show loader
        }, 500);
    }
    
    var classList = document.getElementById("peticio_tableid").classList;
    while (classList.length > 0) {
       classList.remove(classList.item(0));
    }
    
    
    function refreshFileList(ALL_FILES){
    	
        var autofirma = 1;
        var tipus = document.getElementById("peticio.tipus").value;

        if(tipus == autofirma){
        	if (ALL_FILES.length > 1) {
				alert("Nomes un fitxer");
			}
        	var removed = ALL_FILES.splice(1); //, ALL_FILES.length);
        }
        
        let fullList = new DataTransfer();

    	var hiddenInput = document.getElementById("myHiddenInput");
        hiddenInput.value = ALL_FILES.length;

    	var ul_files = document.getElementById("ul_files");
        ul_files.innerHTML = "";
        
        if (ALL_FILES.length > 0) {
            $("#btn-clear-files").show();
		}else{
            $("#btn-clear-files").hide();
		}
        
        var i = 0;
        //UTILIZAR EL FLAG PARA TRATAR COMO FICHERO O ADJUNTO.
        while (i < ALL_FILES.length){
        	
            var li_input = document.createElement("li");
            li_input.setAttribute("id", "li_file" + i );
            li_input.setAttribute("class", "li_file" );

            if (FLAG.charAt(i) === "F") {
                var div_file = document.createElement("div");
                div_file.setAttribute("id", "div_file" + i );
                div_file.setAttribute("class", "div_file" );
                
                var input = document.createElement("input");
                input.setAttribute("type", "file");
                input.setAttribute("id", "hiddenFile" + i );
                input.setAttribute("name", "hiddenFile" );
                input.setAttribute("value", "hiddenFile" + i );
                input.setAttribute("style", "display:none");
                input.setAttribute("oninput", "");

                var anex_aux= document.createElement("input");
                anex_aux.setAttribute("type", "file");
                anex_aux.setAttribute("id", "anex_aux" + i );
                anex_aux.setAttribute("name", "anex_aux" );
                anex_aux.setAttribute("value", "anex_aux" + i );
                anex_aux.setAttribute("style", "display:none");
                anex_aux.setAttribute("oninput", "afegirAnex(this, " + i + " )");
                anex_aux.setAttribute('multiple', true);
                
                var div_botonera = document.createElement("div");
                div_botonera.setAttribute("id", "botonera" + i );
                div_botonera.setAttribute("class", "botonera");
                
                var btn_delete = document.createElement("a");
                btn_delete.setAttribute("id", "eliminarFitxer" + i );
                btn_delete.setAttribute("class", "eliminarFitxer bg-aplicacio");
                btn_delete.setAttribute("onclick", "deleteFitxer(ALL_FILES, " + i + " )");
                btn_delete.innerHTML = '<span class="label label-success"><b><i class="fas fa-times"></i></b></span>';
                
                var btn_afegir_anex = document.createElement("label");
                btn_afegir_anex.setAttribute("id", "afegirAnex" + i );
                btn_afegir_anex.setAttribute("for", "anex_aux" + i );
                btn_afegir_anex.setAttribute("class", "afegirAnex bg-aplicacio");
                btn_afegir_anex.innerHTML = '<span class="label label-success"><b><i class="fas fa-plus"></i></b></span>';

                div_botonera.appendChild(btn_afegir_anex);
                div_botonera.appendChild(btn_delete);
                
                var titolFitxer = document.createElement("span");
                titolFitxer.setAttribute("style", "width: fit-content");
                titolFitxer.innerHTML = ALL_FILES[i].name;
                
                div_file.appendChild(input);
                div_file.appendChild(anex_aux);
                div_file.appendChild(titolFitxer);
                div_file.appendChild(div_botonera);
                
                
                //Afegim el fitxer al input amb un fileList
                let list = new DataTransfer();
                let file = ALL_FILES[i];
                list.items.add(file);
                
                //També l'afegim al llistat total de fitxers, per enviar-los tots
                fullList.items.add(file);

                let myFileList = list.files;
                input.files = myFileList;
                
                li_input.appendChild(div_file);
                
                //Cuando hemos añadido el fichero, comprovamos si el siguiente es su anexo...
                
                var div_attached = document.createElement("div");
                div_attached.setAttribute("id", "div_attached" + i );
                div_attached.setAttribute("class", "div_attached" );

                var ul_attached = document.createElement("ul");
                ul_attached.setAttribute("id", "ul_attached" + i );
                ul_attached.setAttribute("class", "ul_attached" );
                
                i++;
                //Si el siguiente es anexo, añadelo. Si no, añadimos otro fichero
                while (FLAG.charAt(i) === "A"){
                	console.log("fichero: " + i);
                	
                    var li_anex = document.createElement("li");
                    li_anex.setAttribute("id", "li_anex" + i );
                    li_anex.setAttribute("class", "li_anex" );
                    
                    var input_anex = document.createElement("input");
                    input_anex.setAttribute("type", "file");
                    input_anex.setAttribute("id", "input_anex" + i );
                    input_anex.setAttribute("name", "hiddenFile" );
                    input_anex.setAttribute("value", "input_anex" + i );
                    input_anex.setAttribute("style", "display:none");
                    input_anex.setAttribute("oninput", "");

                    var btn_delete_anex = document.createElement("a");
                    btn_delete_anex.setAttribute("id", "eliminarAnex" + i );
                    btn_delete_anex.setAttribute("class", "eliminarAnex btn-primary");
                    btn_delete_anex.setAttribute("style", "cursor:pointer");
                    btn_delete_anex.setAttribute("onclick", "deleteFitxer(ALL_FILES, " + i + " )");
                    btn_delete_anex.innerHTML = '<span class="label label-success"><b><i class="fas fa-times"></i></b></span>';
                    
                    li_anex.innerHTML = ALL_FILES[i].name;
                    li_anex.appendChild(input_anex);
                    li_anex.appendChild(btn_delete_anex);
                    
                    ul_attached.appendChild(li_anex);
                    
                    //Afegim el fitxer al input amb un fileList
                    let list = new DataTransfer();
                    let file = ALL_FILES[i];
                    list.items.add(file);
                    
                    //També l'afegim al llistat total de fitxers, per enviar-los tots
                    fullList.items.add(file);

                    let myFileList = list.files;
                    input_anex.files = myFileList;
                    
                    i++;
                }
                div_attached.appendChild(ul_attached);
                
                //Cuando hemos rellenado el div con el ul con anexos. Lo añadimos al li. Puede estar vacio      
                li_input.appendChild(div_attached);
                ul_files.appendChild(li_input);
                
            }else{
            	//No puede empezar por anex.
            	i++;
            }
        }
        
        document.getElementById("fitxerID").files = fullList.files;
        document.getElementById("fileInfoFlag").value = FLAG;
        
        var fileLabel= $("#fitxerID-custom-file-label");
        if (ALL_FILES.length == 0) {
            fileLabel.hide();
		}else{
			fileLabel.show();
			fileLabel.children("small").html(ALL_FILES.length + " fitxers");
        }
    }
    
    
    document.getElementById("fitxerID").onchange = function() {
    	//ALL_FILES = [];
    	
    	var files = this.files;

        for (var i = 0; i < files.length; i++) {
            var myFile = files.item(i);
            myFile.tipo = "fitxer";
            if (myFile.type ===  "application/pdf") {
                ALL_FILES.push(myFile);
                FLAG += "F";
            }
        }
        refreshFileList(ALL_FILES);
    };
    
    function deleteFitxer(ALL_FILES, id){
    	var tmp = FLAG.split('');

    	if(tmp[id] === "F"){
          ALL_FILES.splice(id, 1);
    	  tmp.splice(id, 1);
    	  
    	  while(id < tmp.length && tmp[id] === "A"){
            ALL_FILES.splice(id, 1);
            tmp.splice(id, 1);
    	  }
    	  
    	}else if(tmp[id] === "A"){
          ALL_FILES.splice(id, 1);
          tmp.splice(id, 1);
    	}
    	
        FLAG = tmp.join('');
        console.log(FLAG);
        refreshFileList(ALL_FILES);
    }

    function afegirAnex(elem, id){
    	
        var files = elem.files;
        id++;
        for (var i = 0; i < files.length; i++) {
            var myFile = files.item(i);
            myFile.tipo = "anex";
//            if (myFile.type ===  "application/pdf") {
                ALL_FILES.splice(id, 0, myFile);
                var output = [FLAG.slice(0, id), "A", FLAG.slice(id)].join('');
                FLAG = output;
                console.log(FLAG);
  //          }
        }
        
        refreshFileList(ALL_FILES);
     }

    
    function clearAllFiles(){
    	ALL_FILES = [];
        FLAG = "";

        refreshFileList(ALL_FILES);
    }
</script>

    <style>
.droparea {
	margin: 1rem auto;
	padding: 1rem;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	max-width: 100%;
	border: 3.5px dashed darkslategray;
	border-radius: 15px;
	width: 100%;
}

.draging {
	background-color: rgba(0, 195, 255, 0.1);
	border: 3.5px solid black;
}

.dragdrop_text {
	color: orange;
	font-size: 1.5rem;
}

.loader {
	height: 100%;
	position: absolute;
	width: 100%;
	z-index: 1000;
	background: #fcfcfc
		url("https://cdn.dribbble.com/users/765253/screenshots/2540865/loader.gif")
		no-repeat scroll center center;
	top: 0;
	left: 0;
	opacity: 0.8;
}

#peticio_tableid {
	margin: auto;
}

#peticio_tableid>tbody>tr>td:first-child {
	font-weight: bold;
	text-align: right;
	padding: 1rem;
}

td label {
	font-size: 0.9rem;
	margin: 0;
}

.eliminarAnex, .afegirAnex, .eliminarFitxer {
	border-radius: 4px;
	padding-left: 6px;
	margin-left: 6px;
	margin-right: 0px;
	padding-right: 6px;
	cursor: pointer;
	display: inline;
}

.afegirAnex:hover {
	background-color: #b96f24 !important
}
.eliminarFitxer:hover {
	background-color: #c82333 !important
}
.eliminarAnex:hover {
	background-color: #e04958 !important;
}

.afegirAnex {
	font-size: 1rem;
}

.div_file {
	background-color: rgba(255, 149, 35, 0.5);
	border-radius: 5px;
	padding: 3px 8px;
	margin: 5px 10px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.li_file {
	float: left;
}

#ul_files {
	list-style: none;
	margin: 1rem 3rem 0rem;
	display: inline-block;
	display: flex;
	flex-wrap: wrap;
}

#btn-clear-files {
	float: right;
	margin-right: 3rem;
	color: white;
	padding: 0.3rem 0.7rem;
	border-radius: 5px;
	cursor: pointer;
}

.li_anex {
	background-color: rgba(36, 110, 185, 0.5);
	border-radius: 5px;
	padding: 2px 8px;
	margin: 5px 10px;
	width: fit-content;
}

.ul_attached {
	list-style: disclosure-closed;
	margin-left: 1rem;
}

.botonera {
	margin-left: 0.5rem;
}
</style>

</c:if>





<c:if test="${not empty plantillaflux}">
    <script type="text/javascript">

        var portafibDIV = "<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.PETICIOPORTAFIRMES.getJavaName()%>";
        var portafibIDLabel = "<%=PeticioFields.PETICIOPORTAFIRMES.getCodeLabel()%>";

        $("#peticio_nom_rowid").after($("#" + portafibDIV + "_rowid"));
        
        var html = "";     
        html += '<select '; 
        html += 'id= "' + portafibIDLabel  + '" '; 
        html += 'name= "' + portafibIDLabel  + '" '; 
        html += 'class="form-control col-md-9-optional"> ';
        
        <c:forEach var="item" items="${plantillesUsuari}">
            html += '<option value="<c:out value="${item.nif}"/>"><c:out value="${item.nom}"/></option>';
        </c:forEach>
        
        // Si no hi ha plantilles, tornar a llistat amb un missatge warn
        
        html += '</select>';
        
        document.getElementById(portafibDIV + "_columnvalueid").innerHTML = html;
 
    </script>
</c:if>


<%-- ===================== MOSTRAR CAMPS AVANÇATS ===========================  --%>

<script type="text/javascript"> 

var esMostrenCampsAvanzats = true;

function mostrarOcultarCampsAvanzats(boto) {
    
    esMostrenCampsAvanzats = !esMostrenCampsAvanzats;

    if (boto) {
        
        if (esMostrenCampsAvanzats) {
            boto.innerHTML='<i class="fas fa-info-circle"></i>&nbsp;&nbsp;<fmt:message key="advanced.hide"/>';
        } else {
            boto.innerHTML='<i class="fas fa-info-circle"></i>&nbsp;&nbsp;<fmt:message key="advanced.show"/>';
        }
    }
    
    if (esMostrenCampsAvanzats) {
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMINTERESSATS.getJavaName()%>_rowid").show();
<%--         $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName()%>_rowid").show(); --%>
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName()%>_rowid").show();
    } else {
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMINTERESSATS.getJavaName()%>_rowid").hide();
<%--         $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName()%>_rowid").hide(); --%>
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName()%>_rowid").hide();
    }
    
}

mostrarOcultarCampsAvanzats();

</script>

<%-- ==== FER QUE L'OPCIÓ NULL DE TIPUS DOCUMENTALS PAREXQUI UN PLACEHOLDER ==========  --%>
<script type="text/javascript"> 
    var select = document.getElementById("peticio_tipusDocumental");
	var options = select.childNodes;
	options.forEach( function(option, index, array) {
		if(option.value === ""){
			option.disabled = "true";
	        $(option).appendTo(select);
		}
	});
	
	testNull();
	
	function testNull(){
		if (select.selectedOptions[0].value == "") {
			console.log("vacio");
		}
		
	}
</script>




<%--===== FER UNA BARRA DE PROGRES PER VEURE LES PETICIONS ENVIADES ============--%>

<div class="modal fade" style="display: none" id="progresModal"
    tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
    aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalTitle">
                    <span id="textPujantFitxers">Pujant fitxers ...</span>
                </h5>
                <button type="button" class="close" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body" style="text-align: center;">
				<div id="myProgress"><div id="myBar"></div></div>
				<span id="mySpinner" class="fa fa-spinner fa-pulse fa-3x"></span>
			</div>
        </div>
    </div>
</div>

<style>
#myProgress {
  width: 100%;
  background-color: lightgray;
}

#myBar {
  width: 1%;
  height: 30px;
  background-color: orange;
  transition: all 0.25s ease-out;
}
</style>

<script type="text/javascript"> 

var peticionsTotals = -1;

function validacioFormulario() {
	//<span id="peticio.dataCreacio.errors" class="errorField alert alert-danger">El camp Creada és obligatori.</span>
	var validacio = true;
	$(".errorField").remove();
	
	
    var fitxerID = document.getElementById("fitxerID");
    if (fitxerID.value === "") {
        var span = document.createElement("span");
        span.id = "peticio.fitxerID.errors";
        span.classList = "errorField alert alert-danger";
        span.innerHTML = "No hi ha fitxers per enviar.";
        $("#peticioForm").prepend(span);
        validacio &= false;
    }
    var titol = document.getElementById("peticio.nom");
    if (titol.value === "") {
        var span = document.createElement("span");
        span.id = "peticio.nom.errors";
        span.classList = "errorField alert alert-danger";
        span.innerHTML = "El camp Titol és obligatori.";
        titol.parentElement.prepend(span);
        validacio &= false;
    }

    var nif = document.getElementById("peticio.destinatariNif");
    var regex = /^[0-9]{8,8}[A-Za-z]$/;
    if (!regex.test(nif.value)) {
        var span = document.createElement("span");
        span.id = "peticio.destinatariNif.errors";
        span.classList = "errorField alert alert-danger";
        span.innerHTML = "El camp NIF no té el format correcte.";
        nif.parentElement.prepend(span);
        validacio &= false;
    }

    var tipusDoc = document.getElementById("peticio_tipusDocumental");
    if (tipusDoc.value === "") {
        var span = document.createElement("span");
        span.id = "peticio_tipusDocumental.errors";
        span.classList = "errorField alert alert-danger";
        span.innerHTML = "El Tipus documental és obligatori.";
        tipusDoc.parentElement.prepend(span);
        validacio &= false;
    }
    return validacio;
}


function enviar(){

	if(validacioFormulario()){
		var intervalID = setInterval(myCallback, 10);
	    $("#progresModal").modal('show');
	    document.getElementById('peticioForm').submit();
	}
	

	function myCallback() {
		  var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {

		        if (this.readyState == 4 && this.status == 200) {
		            var myObj = this.response;
		            if (peticionsTotals == -1) {
		            	$("#myProgress").hide();
		            	peticionsTotals = myObj.total;
		            	console.log("Pujant fitxers ...");
					}else{
                        $("#myProgress").show();
                        $("#mySpinner").hide();
                        $("#textPujantFitxers").hide();
			            var width = (myObj.enviades / myObj.total)*100;
			            if (width == 0) width  = 1;

			            console.log("Enviades: " + myObj.enviades + " de " + myObj.total + ": " + width + "%" + " petsTotals: " +peticionsTotals );		
			            
			            if (myObj.enviades == -1) myObj.enviades = peticionsTotals;

			            var title = document.getElementById("modalTitle");
			            title.innerHTML = '<fmt:message key="peticions.procesades" />' + myObj.enviades + " de " + peticionsTotals;
	
			            var elem = document.getElementById("myBar");
			            elem.style.width = width + "%";
					}
		        }
		    };
		    
		     
		    var urlProgres = '<%=request.getContextPath()%>${contexte}/progres';
		    xhttp.open("GET", urlProgres, true);
		    xhttp.responseType = 'json';
		    xhttp.setRequestHeader("Content-type", "application/json");
		    xhttp.send('');
	}
}

</script>

<style>
.errorField{
      margin-bottom: 0.5rem;
      margin-top: 1rem;
}
</style>


