<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>


<div id="faq-index-title" onclick="gototop()">Index de continguts</div>
<div id="faq-index-container">
	<c:forEach var="faq" items="${faqItems}">
		<div class="faq-index-item" id="faq_index_rowid_${faq.faqID}">
			<div class="faq-index-pregunta" onclick="gotofaq(${faq.faqID});">
				<c:if test="${lang == 'ca'}">${faq.enunciat_ca}</c:if>
				<c:if test="${lang == 'es'}">${faq.enunciat_es}</c:if>
			</div>
		</div>
	</c:forEach>
</div>

<style>
#faq-index-container {
	border-left: 2px solid black;
	padding-left: 6px;
    margin-top: 8px;
    margin-left: 2px;
}

#faq-index-title {
	font-size: 21px;
	color: rgb(36, 110, 185);
}

.faq-index-item {
	
}

.faq-index-pregunta {
	padding: 4px 8px;
}

#principal {
	width: 100%;
}

#thumbnailmenu {
	padding-left: 4px;
	
	position: static;
	top: 0;
	max-width: 320px;
	z-index: 100;
	top: 0;
	width: 100%
}
</style>



<script type="text/javascript">
            document.getElementById("thumbnailmenu").classList = "";
            $("#ocultarMenu").css("display", "none");

            $(window).scroll(function (e) {
                var $el = $('#thumbnailmenu');
                var isPositionFixed = ($el.css('position') == 'fixed');
                if ($(this).scrollTop() > 150 && !isPositionFixed) {
                    $el.css({
                        'position': 'fixed',
                        'top': '25px'
                    });
                }
                if ($(this).scrollTop() < 150 && isPositionFixed) {
                    $el.css({
                        'position': 'static',
                        'top': '0px'
                    });
                }
            });


            function gotofaq(faqid) {
                var rowid = "faq_rowid_" + faqid;
                console.log(rowid);
                const element = document.getElementById(rowid);
                console.log(element);

                var bodyRect = document.body.getBoundingClientRect(),
                    elemRect = element.getBoundingClientRect(),
                    offset = elemRect.top - bodyRect.top;

                $('html,body').animate({
                    scrollTop: offset
                },
                    'slow');
            }
            
            function gototop(){
            	
            	 $('html,body').animate({
                     scrollTop: 0
                 },
                     'slow');
            	
            }

        </script>