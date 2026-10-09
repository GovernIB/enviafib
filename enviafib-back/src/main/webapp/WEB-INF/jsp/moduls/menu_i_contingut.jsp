<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="menu" />
<tiles:importAttribute name="contingut" />

        <div id="mostrarMenu" class="upper-left-corner no-disponible">
            <a id="mostrar" href="#" data-toggle="tooltip"
                title="Mostrar Menu"> <i class="fas fa-expand-alt"></i>
            </a>
        </div>

<div class="" style="display: flex;">

	<!--  INICI MENU col-2 -->
	<div id="principal" class="mainMenu">
        
        <div id="ocultarMenu" class="upper-right-corner disponible">
            <a id="ocultar" href="#" data-toggle="tooltip"
                title="Ocultar Menu"> <i class="fas fa-compress-alt"></i>
            </a>
        </div>
        
		<div id="thumbnailmenu" class="thumbnail disponible">
			<tiles:insertAttribute name="menu">
			</tiles:insertAttribute>
		</div>
	</div>

	<!--  CONTINGUT col-10 -->
    <div id="contingut">

		<!--  Missatges  -->
		<jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

		<!-- Contingut de la pagina -->
		<tiles:insertAttribute name="contingut">
		</tiles:insertAttribute>

		<!-- FINAL DIV CONTINGUT -->
	</div>
</div>

<script type="text/javascript">
    $('#ocultar').click(function() {
        show('#mostrarMenu');
		hide('#ocultarMenu');

        $("#principal").css("display","none");        
        return false;
	});

	$('#mostrar').click(function() {
        hide('#mostrarMenu');
		show('#ocultarMenu');
		
        $("#principal").css("display","block");				
		return false;
	});

	function hide(item) {
		$(item).removeClass('disponible');
		$(item).addClass('no-disponible');
	}

	function show(item) {
		$(item).removeClass('no-disponible');
		$(item).addClass('disponible');
	}
	   
</script>


<script type="text/javascript">
$("#GroupDiv").after($("#infoNumRegistres"));
</script>

<c:if test="${pipella eq 'user'}">
    <script type="text/javascript">
/* 		show('#mostrarMenu');
		hide('#ocultarMenu');
 */		$("#principal").css("display", "none");
	</script>
</c:if>