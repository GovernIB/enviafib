<%@page import="es.caib.enviafib.model.fields.PeticioFields"%>
<c:if test="${not empty dragdrop}">

	<%-- 	<input type="hidden" name="dragndrop" value="true"/> --%>

	<div class="droparea">

		<img id="res" src="<c:url value="/img/dragndrop.png"/>" />

		<p class="dragdrop_text">
			<fmt:message key="dragdrop.text" />
		</p>

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

        var fileInput = document.getElementById("fitxerID");
		fileInput.files = e.dataTransfer.files;

		var fileName = fileInput.files[0].name;

	    setTimeout(function(){
			var fileLabel= $("#fitxerID-custom-file-label");
		    fileLabel.show();
		    fileLabel.children("small").html(fileName)
		    
	 	    console.log(fileInput.files); 
		    $('.loader').hide(); // show loader
	    }, 3200);
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

#showHideButton{

}

</style>

</c:if>
<div id="navbar-showHideButton" class="navbar-form" style="text-align:right;">
 	<button type="button" id="showHideButton" value="0" onclick="showMore()">Click me</button>
</div>

<script type="text/javascript">	


$("#navbar-showHideButton").insertBefore("#peticioForm");

$("#showHideButton").addClass("btn btn-info");
showMore();

function showMore(){

	$("#<%= PeticioFields._TABLE_MODEL %>_<%=PeticioFields.ARXIUREQPARAMINTERESSATS.getJavaName() %>_rowid").toggle();
	$("#<%= PeticioFields._TABLE_MODEL %>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName() %>_rowid").toggle();
	$("#<%= PeticioFields._TABLE_MODEL %>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName() %>_rowid").toggle();

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
