




<div id="frameContainer">
	<c:forEach var="annex" items="${annexes}" varStatus="loop">
		<div id="myFrame_${loop.index}" class="myFrame" style="display: none;">
			<c:choose>
				<c:when test="${annex.mime == 'application/pdf'}">
					<object type="application/pdf" data="${annex.descripcio}"></object>
				</c:when>
                <c:when test="${fn:startsWith(annex.mime, 'image/')}">
                    <img src="${annex.descripcio}"></img>
                </c:when>
				<c:when test="${fn:startsWith(annex.mime, 'text/')}">
                    <object type="${annex.mime}" data="${annex.descripcio}"></object>
				</c:when>
				<c:otherwise>
					<a href="${annex.descripcio}">${annex.nom}</a>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forEach>
</div>

<script type="text/javascript">
	var table = document.getElementsByTagName("table")[0];
	var row = table.parentElement;

	var tableContainer = document.createElement("div");
	tableContainer.id = "tableContainer";

	$(tableContainer).append($(table));
	$(row).append($(tableContainer));
	$(row).append($("#frameContainer"));

	$(".myFrame a").parent().addClass("toDownload");
	
	//	$("#frameContainer").insertAfter($("table"));
    mostrarDiv(0);
	function mostrarDiv(id) {
        $(".myFrame").hide();
		$("#myFrame_" + id).show();
	}
</script>

<style>
.myFrame {
	width: 100%;
	height: 100%;
	min-height: 500px;
	border: black 1px solid;
}

.myFrame img {
	width: 100%;
}

.myFrame object {
	width: 100%;
	height: 100%;
}

.myFrame.toDownload {
	min-height: 0px;
	display: flex;
	align-items: center;
	justify-content: center;
	text-align: center;
	height: 10rem;
}

#frameContainer {
	flex: 1 1;
	margin-bottom: 1rem;
	resize: both;
	overflow: auto;
	padding: 0 3rem;
}

#tableContainer {
	flex: 0 0 auto;
	max-width: 30rem;
}
</style>

