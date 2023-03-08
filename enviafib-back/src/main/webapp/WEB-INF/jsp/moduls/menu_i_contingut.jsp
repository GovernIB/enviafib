<%@ page contentType="text/html;charset=UTF-8" language="java"%><%@ include
	file="/WEB-INF/jsp/moduls/includes.jsp"%>

<tiles:importAttribute name="menu" />
<tiles:importAttribute name="contingut" />

        <div id="mostrarMenu" class="upper-left-corner no-disponible" ">
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

	<div class="clearfix"></div>
</div>

<script>
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

#ocultarMenu {
    padding: 0.5rem;
}

#mostrarMenu{
	padding-left: 1.25rem;
	padding-bottom: 0.5rem;
	padding-top: 0.5rem;	
}

#principal {
/*     width: 30%; */
    min-width: 250px;
    max-width: 300px;

    padding: 0px 0.75rem;
}

#contingut {
    width: 100%;
}
</style>

<<script type="text/javascript">
$("#GroupDiv").after($("#infoNumRegistres"));
</script>

<style>
    #infoNumRegistres{
        margin-bottom: 5px;
    }
</style>

