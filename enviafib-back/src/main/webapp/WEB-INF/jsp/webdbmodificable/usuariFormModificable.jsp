


<!-- 	<tr id="usuari_dir3_rowid">
		<td id="usuari_dir3_columnlabelid"><label> Codi DIR3 &nbsp;</label></td>
		<td id="usuari_dir3_columnvalueid"><input id="usuari.dir3"
			name="usuari.dir3" class="w-75 form-control   uneditable-input"
			readonly="readonly" type="text" value="A01312321" maxlength="50"></td>
	</tr>
 -->

<script type="text/javascript">
	$(document).ready(function() {
		var table = $('#usuari_tableid');

		var tr = document.createElement("tr");
		tr.id = "usuari_dir3_rowid";

		var tdLabel = document.createElement("td");
		tdLabel.id = "usuari_dir3_columnlabelid";

		var label = document.createElement("label");
		label.innerHTML = "Codi DIR3 &nbsp;";
		tdLabel.appendChild(label);

		var tdValue = document.createElement("td");
		tdValue.id = "usuari_dir3_columnvalueid";

		var input = document.createElement("input");
		input.id = "usuari.dir3";
		input.name = "usuari.dir3";
		input.readOnly = true;
		input.type = "text";
		input.maxlength = "50";
		input.className = "w-75 form-control uneditable-input";

		input.value = "${elCodiDir3}";

		tdLabel.appendChild(label);
		tdValue.appendChild(input);

		tr.appendChild(tdLabel);
		tr.appendChild(tdValue);

		table[0].children[0].appendChild(tr);

	});
</script>