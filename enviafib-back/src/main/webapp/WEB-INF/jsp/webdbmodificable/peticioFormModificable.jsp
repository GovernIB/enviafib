<%@page import="es.caib.enviafib.model.fields.PeticioFields"%>
<c:if test="${not empty dragdrop}">

	<%-- 	<input type="hidden" name="dragndrop" value="true"/> --%>

	<div class="droparea">

		<img id="res" src="<c:url value="/img/dragndrop.png"/>" />

		<p class="dragdrop_text">
			<fmt:message key="dragdrop.text" />
		</p>

		<input type="hidden" name="myHiddenInput" id="myHiddenInput">
		<input type="hidden" name="nFitxers" id="nFitxers">

		<div id="progress_bar" class="progress" style="display: none;">
			<div class="progress-bar progress-bar-striped active"
				role="progressbar" aria-valuenow="0" aria-valuemin="0"
				aria-valuemax="100" style="width: 0%;">0%</div>
		</div>

	</div>
	<div class="loader" style="display: none;"></div>

	<script type="text/javascript">	
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
	    
//		$(".tab_container").append('<h5><fmt:message key="dragdrop.title"/><h5>');   
		$("#peticio_tableid").append($("#peticio_idiomaDoc_rowid"));   
		$("#peticio_tableid").append($("#peticio_fitxerID_rowid"));   
		$(".tab_container").append($(".droparea"));   
		
		
	}

	document.addEventListener("DOMContentLoaded", initApp);

	const handleDrop = (e) => {
		
		$('.loader').show(); // show loader

/*         Input = document.getElementById("fitxerID");
		fileInput.files = e.dataTransfer.files;
 */		
		document.getElementById("fitxerID").files = e.dataTransfer.files;
 
        var files = e.dataTransfer.files;

 		console.log(files);
 
 		var hiddenInput = document.getElementById("myHiddenInput");
		hiddenInput.value = files.length;
		
		var fitxers = [];
		
		for (var i = 0; i < files.length; i++) {

			var input = document.createElement("input");
	
			input.setAttribute("type", "file");
	
			input.setAttribute("id", "hiddenFile" + i );
			input.setAttribute("name", "hiddenFile" );
			input.setAttribute("style", "display:none");
			input.setAttribute("value", "hiddenFile" + i );
	
			document.getElementById("peticioForm").appendChild(input);

			var fileInput = document.getElementById("hiddenFile" + i);
			
			let list = new DataTransfer();
			let file = files.item(i);
			list.items.add(file);

			let myFileList = list.files;
			fileInput.files = myFileList;

			fitxers.push(file.name);
		}
		
		
/* 		var fitxers = [];
		var fileName = "";

		var transferencia = [];
		
		for (const file of fileInput.files) {

			
			fileName += file.name + ", ";
			fitxers.push(file.name);

//			console.log(file);

			setTimeout(function(){
				var fr = new FileReader();
				fr.onload = function() {
					var data = fr.result;
					var array = new Int8Array(data);
//					console.log(array);
//					console.log(JSON.stringify(array, null, '  '));
					var fileObject = {
						name : file.name,
						data : array,
						mime : file.type,
						size : file.size
					};
					console.log(fileObject);
					transferencia.push(fileObject);
				};

				 fr.readAsArrayBuffer(file);
			}, 200);
		}
		
	    console.log("transferencia: " + transferencia);
 */
		
	    setTimeout(function(){
			var fileLabel= $("#fitxerID-custom-file-label");
		    fileLabel.show();
		    fileLabel.children("small").html(fitxers)
		    
			console.log(fitxers); 
		    
		    $('.loader').hide(); // show loader
	    }, 500);
	}
	
 	var classList = document.getElementById("peticio_tableid").classList;
	while (classList.length > 0) {
	   classList.remove(classList.item(0));
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

#showHideButton {
	
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


<div id="navbar-showHideButton" class="navbar-form"
	style="text-align: right;">
	<button type="button" id="showHideButton" value="0"
		onclick="showMore()">Click me</button>
</div>

<script type="text/javascript">	


$("#navbar-showHideButton").insertBefore("#peticioForm");
$("#showHideButton").addClass("btn btn-info");
showMore();

function showMore(){

	$("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMINTERESSATS.getJavaName()%>_rowid").toggle();
	$("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName()%>_rowid").toggle();
	$("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName()%>_rowid").toggle();

	var mode = $("#showHideButton").val();

	if(mode == 0){
 		$("#showHideButton").html('<fmt:message key="advanced.show"/>');
		mode = 1;
	}else{
 		$("#showHideButton").html('<fmt:message key="advanced.hide"/>');
		mode = 0;
	}
 	$("#showHideButton").val(mode);
 }
 
 
 
 

</script>
