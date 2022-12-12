<%@ page contentType="text/html;charset=UTF-8" language="java" 
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head></head>
<body>
<script>
var loc = window.location;

var port;

if (loc.port == '' || loc.port == 0) {
    port = '';
} else {
    port = ":" + loc.port;
}

var redirect = loc.protocol + "//" + loc.hostname + port + "/enviafibback"

window.location.href = redirect;
</script>
</body>
</html>