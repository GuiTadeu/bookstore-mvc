<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<tags:pageTemplate titulo="Usuários">

	<jsp:body>
		<div class="container">
			<a href="${s:mvcUrl('UC#formCadastro').build()}"><h3>Novo Usuário</h3></a>
			<h1>Lista de Usuários</h1>
			<table class="table table-bordered table-striped table-hover">
				<tr>
					<th>Nome</th>
					<th>Email</th>
					<th>Roles</th>
					<th></th>
				</tr>
				<c:forEach items="${usuarios }" var="usuario">
					<tr>
						<td>${usuario.nome }</td>
						<td>${usuario.email }</td>
						<td>${usuario.roles }</td>
						<td>
							<a href="${s:mvcUrl('UC#listarRoles').arg(0,usuario.email).build()}" class="btn btn-default">
								<button type="submit"><span class="glyphicon glyphicon-plus-sign"></span></button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</jsp:body>

</tags:pageTemplate>