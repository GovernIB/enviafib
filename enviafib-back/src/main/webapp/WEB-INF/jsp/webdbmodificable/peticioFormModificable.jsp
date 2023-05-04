<%@page import="es.caib.enviafib.model.fields.PeticioFields"%>

<c:if test="${not empty dragdrop}">

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
    <div class="loader" style="display: none; list-style-type: none;"></div>

    <ul id="ul_files"></ul>
    <script type="text/javascript">    

    var ALL_FILES = [];
    
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
        $(".tab_container").append($("#ul_files"));   
        $(".tab_container").append($(".droparea"));   
    }

    document.addEventListener("DOMContentLoaded", initApp);

    const handleDrop = (e) => {
    	$('.loader').show(); // show loader
        
        var files = e.dataTransfer.files;

        for (var i = 0; i < files.length; i++) {
            ALL_FILES.push(files.item(i));
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

        let fullList = new DataTransfer();

    	var hiddenInput = document.getElementById("myHiddenInput");
        hiddenInput.value = ALL_FILES.length;

    	var ul_files = document.getElementById("ul_files");
        ul_files.innerHTML = "";
        
        for (var i = 0; i < ALL_FILES.length; i++) {

            var li_input = document.createElement("li");
            li_input.setAttribute("id", "div_hiddenFile" + i );
            li_input.setAttribute("class", "div_hiddenFile" );

            var input = document.createElement("input");
            input.setAttribute("type", "file");
            input.setAttribute("id", "hiddenFile" + i );
            input.setAttribute("name", "hiddenFile" );
            input.setAttribute("value", "hiddenFile" + i );
            input.setAttribute("style", "display:none");
            input.setAttribute("oninput", "");

            var a = document.createElement("a");
            a.setAttribute("id", "eliminarFitxer" + i );
            a.setAttribute("class", "eliminarFitxer bg-aplicacio");
            a.setAttribute("style", "cursor:pointer");
            a.setAttribute("onclick", "deleteFitxer(ALL_FILES, " + i + " )");
            a.innerHTML = '<span class="label label-success"><b><i class="fas fa-times"></i></b></span>';
            
            li_input.innerHTML = ALL_FILES[i].name;
            li_input.appendChild(input);
            li_input.appendChild(a);
           
            ul_files.appendChild(li_input);

            //Afegim el fitxer al input amb un fileList
            let list = new DataTransfer();
            let file = ALL_FILES[i];
            list.items.add(file);
            //També l'afegim al llistat total de fitxers, per enviar-los tots
            fullList.items.add(file);

            let myFileList = list.files;
            input.files = myFileList;
        }
        
        document.getElementById("fitxerID").files = fullList.files;
        
        var fileLabel= $("#fitxerID-custom-file-label");
        if (ALL_FILES.length == 0) {
            fileLabel.hide();
		}else{
			fileLabel.show();
			fileLabel.children("small").html(ALL_FILES.length + " fitxers");
        }
    }
    
    
    document.getElementById("fitxerID").onchange = function() {
    	ALL_FILES = [];
    	
    	var files = this.files;

        for (var i = 0; i < files.length; i++) {
            ALL_FILES.push(files.item(i));
        }
        refreshFileList(ALL_FILES);
    };
    
    function deleteFitxer(ALL_FILES, id){
    	ALL_FILES.splice(id, 1);
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

.eliminarFitxer{
    border-radius: 4px;

	padding-left: 6px;
	margin-left: 10px;

	margin-right: 0px;
	padding-right: 6px;
}

.div_hiddenFile{
    background-color: rgba(255,149,35,0.5);
    
    float: left;
    border-radius: 5px;

    padding: 2px 8px;
    margin: 5px 10px;
}

#ul_files{
	list-style: none;
    margin: 1rem 3rem 0rem;
	display: inline-block;
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


<%-- ================================================  --%>


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
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName()%>_rowid").show();
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName()%>_rowid").show();
    } else {
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMINTERESSATS.getJavaName()%>_rowid").hide();
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORGANS.getJavaName()%>_rowid").hide();
        $("#<%=PeticioFields._TABLE_MODEL%>_<%=PeticioFields.ARXIUREQPARAMORIGEN.getJavaName()%>_rowid").hide();
    }
    
}

mostrarOcultarCampsAvanzats();

</script>

<!-- Canviar la paraula GUARDAR per una altra que sigui més explicativa #272 -->
<script type="text/javascript"> 
    var msg = '<fmt:message key="peticiodefirma.continuar"/>';
    document.querySelector('input[type="submit"]').value = msg; 
</script>




