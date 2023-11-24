
<script>

function editarFlux(url) {
    window.location = url + '/' + btoa(window.location);
}

</script>




<script type="text/javascript">
    
    var parent = $("table").parent();

//    parent.removeClass("row");
    parent.css('width', '60%');
    parent.css('margin', '2rem auto');
    
    
    $('th:first-child').css('vertical-align', 'middle');

    $('td:first-child').each(function() {
        var str = $(this).html();
        str = str.replace("&nbsp;", "");
        $(this).html(str);
    });

    $('#peticio_listheader label').addClass('titol-llistat');
    
</script>

<style>
table {
	width: 100% !important;
	text-align: center;
}

th {
	border-bottom-width: 3px !important;
	background-image: none !important;
}

th span {
	cursor: pointer !important;
}

td {
	vertical-align: middle !important;
}

/* td:nth-child(1), td:nth-child(3) {
    vertical-align: middle !important;
}
td:nth-child(2) {
    text-align: left;
    padding-left: 1rem;
}

 */
td:last-child {
	width: 0px;
	text-align: left;
}

.titol-llistat {
	font-weight: 400 !important;
	margin: 0px;
}

.a_item {
	margin: 0 !important;
	color: black !important;
	background-color: transparent !important;
	border: none;
}

.a_item:hover {
	color: white !important;
}

.a_item svg {
	min-width: 1rem;
}

.dropdown .btn-secondary {
	margin: 0 5px;
}

.dropdown-menu {
	margin-top: 0px;
}

#usuari_listheader a {
	margin-right: 1rem;
	padding: 0.3rem 0.7rem;
	font-size: initial;
}
</style>
