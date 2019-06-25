<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />

<tags:pageTemplate titulo="Cadastro">

	<jsp:body>
		<div class="container">
			<h1>Cadastro de Usuários</h1>
			<form:form action="${s:mvcUrl('UC#cadastrarUsuario').build() }" method="post" commandName="usuario" class="form">
				<div class="form-group">
					<label for="nome">Nome</label>
					<input type="text" name="nome" id="nome" class="form-control">
					<form:errors path="nome" />
				</div>
				
				<div class="form-group">
					<label for="email">Email</label>
					<input type="email" name="email" id="email" class="form-control">
					<form:errors path="email" />
				</div>
				
				<div class="form-group">
					<label for="senha">Senha</label>
					<input type="password" name="senha" id="senha" class="form-control">
					<form:errors path="senha" />
				</div>
					
				<div class="form-group">
					<label for="senhaConfirmada">Senha Repetida</label>
					<input type="password" name="senhaConfirmada" id="Confirmada" class="form-control">
					<form:errors path="senhaConfirmada" />
				</div>

				<button type="submit" class="btn btn-primary">Cadastrar</button>
				
			</form:form>
		</div>
	</jsp:body>

</tags:pageTemplate>