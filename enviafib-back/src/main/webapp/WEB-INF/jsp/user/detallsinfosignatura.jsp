<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
    <%@include file="/WEB-INF/jsp/moduls/includes.jsp" %>

<!DOCTYPE html>
<html>

   <head>
       <meta charset="ISO-8859-1">
       <title>Insert title here</title>
   </head>

   <body>
       <table id="mytable" class="tdformlabel table-sm table table-bordered table-striped marTop10 table-genapp">
           <tbody>
               <!-- 			<tr><th>InfoSignaturaID</th><th>${is.infosignaturaid}</th></tr>
			<tr><td>Sign Operation</td><td>${is.signoperation}</td></tr>
			<tr><td>Sign Type</td><td>${is.signtype}</td></tr>
			<tr><td>Sign Algorithm</td><td>${is.signalgorithm}</td></tr>
			<tr><td>Sign Mode</td><td>${is.signmode}</td></tr>
			<tr><td>Signatures Table Location</td><td>${is.signaturestablelocation}</td></tr>
			<tr><td>Timestamp Included</td><td>${is.timestampincluded}</td></tr>
			<tr><td>Policy Included</td><td>${is.policyincluded}</td></tr>
			<tr><td>Eni Tipo Firma</td><td>${is.enitipofirma}</td></tr>
			<tr><td>Eni Perfil Firma</td><td>${is.eniperfilfirma}</td></tr>
			<tr><td>Eni Rol Firma</td><td>${is.enirolfirma}</td></tr>
			<tr><td>Eni Signer Name</td><td>${is.enisignername}</td></tr>
			<tr><td>Eni Signer AdministrationID</td><td>${is.enisigneradministrationid}</td></tr>
			<tr><td>Eni Sign Level</td><td>${is.enisignlevel}</td></tr>
			<tr><td>Check AdministrationID of Signer</td><td>${is.checkadministrationidofsigner}</td></tr>
			<tr><td>Check Document Modifications</td><td>${is.checkdocumentmodifications}</td></tr>
			<tr><td>Check Validation Signature</td><td>${is.checkvalidationsignature}</td></tr> 
-->
           </tbody>
       </table>

       <script type="text/javascript">
           var secciones = [
               { title: "Seccion 0", elems: [] },
               { title: "Info Signatura", elems: [] },
               { title: "Datos adicionales", elems: [] },
               { title: "ENI", elems: [] },
               { title: "Checks", elems: [] }
           ];

           var atributs = [
               { title: "InfoSignaturaID", name: "infoSignaturaID", value: "${is.infosignaturaid}", sec: 0 },
               { title: "Sign Operation", name: "signOperation", value: "${is.signoperation}", sec: 1 },
               { title: "Sign Type", name: "signType", value: "${is.signtype}", sec: 1 },
               { title: "Sign Algorithm", name: "signAlgorithm", value: "${is.signalgorithm}", sec: 1 },
               { title: "Sign Mode", name: "signMode", value: "${is.signmode}", sec: 1 },
               { title: "Signatures Table Location", name: "signaturesTableLocation", value: "${is.signaturestablelocation}", sec: 2 },
               { title: "Timestamp Included", name: "timestampIncluded", value: "${is.timestampincluded}", sec: 2 },
               { title: "Policy Included", name: "policyIncluded", value: "${is.policyincluded}", sec: 2 },
               { title: "Eni Tipo Firma", name: "eniTipoFirma", value: "${is.enitipofirma}", sec: 3 },
               { title: "Eni Perfil Firma", name: "eniPerfilFirma", value: "${is.eniperfilfirma}", sec: 3 },
               { title: "Eni Rol Firma", name: "eniRolFirma", value: "${is.enirolfirma}", sec: 3 },
               { title: "Eni Signer Name", name: "eniSignerName", value: "${is.enisignername}", sec: 3 },
               { title: "Eni Signer AdministrationID", name: "eniSignerAdministrationId", value: "${is.enisigneradministrationid}", sec: 3 },
               { title: "Eni Sign Level", name: "eniSignLevel", value: "${is.enisignlevel}", sec: 3 },
               { title: "Check AdministrationID of Signer", name: "checkAdministrationIdOfSigner", value: "${is.checkadministrationidofsigner}", sec: 4 },
               { title: "Check Document Modifications", name: "checkDocumentModifications", value: "${is.checkdocumentmodifications}", sec: 4 },
               { title: "Check Validation Signature", name: "checkValidationSignature", value: "${is.checkvalidationsignature}", sec: 4 }
           ];

           var html = "";
           for (var i = 0; i < atributs.length; i++) {
               var id = atributs[i].name;
               var valor = atributs[i].value;

               var mostrarTodo = true;
               if (valor == "") {
                   valor = "-";
               }
               html += "<tr id='id_" + id + "'>";
               html += "<td class='item' id='" + id + "_label'>"
                   + atributs[i].title + "</td>";
               html += "<td class='item' id='" + id + "_value'>" + valor + "</td>";
               html += "</tr>";

               //Incluir cada campo en su seccion
               secciones[atributs[i].sec].elems.push(id);
           }
           $("tbody").prepend(html);
           $(".item").css("width", "20rem");

           splitTable(secciones, "h4", "id_XXXXX");
           
	       contextualiza("signOperation", [ "Operació 0", "Operació 1" ]);
	       contextualiza("signMode", [ "Mode 0", "Mode 1" ]);

	       
           function splitTable(secciones, tag, rowformat) {

               console.log(secciones);
               for (var j = 0; j < secciones.length; j++) {

                   var seccion = secciones[j];
                   console.log(seccion);
                   var rows = seccion.elems;

                   var div = document.createElement("div");
                   var tbody = document.createElement("tbody");

                   var table = document.getElementsByTagName("table")[0];
                   var newtable = table.cloneNode(false);

                   var idx = 0;
                   for (var i = 0; i < rows.length; i++) {
                       var rowid = rowformat.replace("XXXXX", rows[i]);
                       rows[i] = document.getElementById(rowid);

                       if (rows[i]) {
                           tbody.appendChild(rows[i]);
                           idx++;
                       }
                   }

                   if (idx > 0) {
                       div.setAttribute("id", "seccio_" + j);

                       newtable.appendChild(tbody);

                       var p = document.createElement(tag);
                       p.innerHTML = seccion.title;

                       div.appendChild(p);
                       div.appendChild(newtable);

                       table.parentNode.appendChild(div);
                   }
               }
           }
           
   		function contextualiza(idcampo, datos) {
			var campo = $("#" + idcampo + "_value").html();
			for (var i = 0; i < datos.length; i++) {
				if (campo == i) {
					$("#" + idcampo + "_value").html(datos[i]);
					console.log(datos[i] + "(" + campo + ")");
					break;
				}
			}
		}

       </script>

   </body>

   </html>