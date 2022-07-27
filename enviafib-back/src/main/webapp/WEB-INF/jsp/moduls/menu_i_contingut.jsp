<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="menu" />
<tiles:importAttribute name="contingut" />


<div class="row">

	<!--  INICI MENU col-3 -->
	<div id="principal" class="mainMenu col-3">
        <div id="mostrarMenu" class="upper-left-corner no-disponible" style="padding-left:17px;">
            <a id="mostrar" href="#" data-toggle="tooltip"
                title="Mostrar Menu"> <i class="fas fa-expand-alt"></i>
            </a>
        </div>
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

	<!--  CONTINGUT col-9 -->
    <div id="contingut" class="col-9">

		<!--  Missatges  -->
		<jsp:include page="/WEB-INF/jsp/moduls/missatges.jsp" />

		<!-- Contingut de la pagina -->
		<tiles:insertAttribute name="contingut">
		</tiles:insertAttribute>

		<!-- FINAL DIV CONTINGUT -->
	</div>

	<div class="clearfix"></div>

</div>

<script>
	$('#ocultar').click(function() {
		$('#principal').removeClass('col-3');
		$('#contingut').removeClass('col-9');
		$('#contingut').addClass('col-12');
		show('#mostrarMenu');
		hide('#ocultarMenu');
		hide('#thumbnailmenu');
		return false;
	});

	$('#mostrar').click(function() {
		$('#principal').addClass('col-3');
		$('#contingut').removeClass('col-12');
		$('#contingut').addClass('col-9');
		hide('#mostrarMenu');
		show('#ocultarMenu');
		show('#thumbnailmenu');
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
<style>
.no-disponible {
  display: none;
  visibility: hidden;
}

.disponible {
  display: block;
  visibility: visible;
}

.mainMenu .upper-right-corner {
  z-index: 9;
  float: right;
  padding-right: 9px;
}

.mainMenu .upper-left-corner {
  z-index: 9;
  float: left;
}
.thumbnail {
  /* display: block; */
  line-height: 20px;
  border: 1px solid #ddd;
  border-radius: 4px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.055);
  transition: all 0.2s ease-in-out;
}
</style>

