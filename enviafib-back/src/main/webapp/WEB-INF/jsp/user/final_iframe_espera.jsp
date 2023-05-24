<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
    file="/WEB-INF/jsp/moduls/includes.jsp"%>

<%-- ============= Modal per quan autofirma esta arxivant ===========  --%>

<div class="modal fade show d-block" id="spinnerModal" tabindex="-1"
	role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">
					<fmt:message key="peticio.autofirma.arxivant" />
				</h5>
			</div>
			<div class="modal-body" style="text-align: center;">
				<span id="spinner" class="fa fa-spinner fa-pulse fa-3x"></span>
			</div>
		</div>
	</div>
</div>

<style>
#spinnerModal {
	background-color: rgba(137, 129, 121, 0.3);
}
#spinner{
	margin: auto;
}
.modal-body{
    display: flex;
    height: 150px;
}
</style>

<script>
	$(document).ready(function() {
		//sleep(4000);//Mentre arxiu no funciona...
		window.location.href = '${URL_FINAL}';
	});

	function sleep(miliseconds) {
		var currentTime = new Date().getTime();

		while (currentTime + miliseconds >= new Date().getTime()) {
		}
	}
</script>
