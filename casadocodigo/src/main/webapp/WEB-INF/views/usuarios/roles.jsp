<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<tags:pageTemplate titulo="Cadastro">

	<jsp:body>
		<div class="container">
			
			<h1>Cadastro de Permissões para ${usuario.nome }</h1>
			<form:form action="${s:mvcUrl('UC#atualizarRoles').build() }" method="POST">
			
				<c:set var="usuarioRoles" value="${usuario.roles }"/>
				
				Permissões: 
				
				<c:forEach items="${roles }" var="role"> 
					<input type="checkbox" id="${role.nome }" value="${role.nome}" name="checkRoles"
					${fn:contains(usuarioRoles, role.nome) ? "checked" : ""}>
					<label for="${role.nome }"> ${role.nome } </label>
				</c:forEach>
				
				<input type="hidden" name="email" value="${usuario.email }">
				
				<button type="submit" class="btn btn-primary">Atualizar</button>
				
			</form:form>
		</div>
	</jsp:body>

</tags:pageTemplate>