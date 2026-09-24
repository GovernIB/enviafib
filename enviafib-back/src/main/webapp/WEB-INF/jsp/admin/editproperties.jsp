<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/moduls/includes.jsp"%>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.5/codemirror.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.5/codemirror.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.65.5/mode/properties/properties.min.js"></script>

<h2>Editar fitxer de digitaib.properties </h2>

<c:if test="${not empty success}">
    <div class="msg success">${success}</div>
</c:if>
<c:if test="${not empty error}">
    <div class="msg error">${error}</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/admin/editproperties">
<div style="resize: both; overflow: auto; border:1px solid #ccc; margin:20px;height:60vh;">
    <textarea id="editor" name="fileContent" >${fileContent}</textarea>
</div>
    <br />
    <small>L´acció de guardar no recarrega les propietats. Ha de recarregar les propietats manualment després de guardar.</small>
    <br /> <input class="btn btn-warning" type="submit" value="Guardar" />
</form>

<script>
  var editor = CodeMirror.fromTextArea(document.getElementById("editor"), {
    mode: "properties",
    lineNumbers: true,
    theme: "default"
  });
  editor.setSize("100%", "100%");
  editor.setW
</script>