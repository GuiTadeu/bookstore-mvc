<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate titulo="Pedidos">

	<jsp:body>
		<div class="container">
			<h1>Lista de Pedidos Atuais</h1>
			<table class="table table-bordered table-striped table-hover">
				<tr>
					<th><fmt:message key="pedidos.Id"/></th>
					<th><fmt:message key="pedidos.valor"/></th>
					<th><fmt:message key="pedidos.dataPedido"/></th> 
					<th><fmt:message key="pedidos.titulos"/></th>
				</tr>
				<c:forEach items="${pedidos }" var="pedido">
					<tr>
						<td>${pedido.id }</td>
						<td>${pedido.valor }</td>
						<td>${pedido.data }</td>
						<td>${pedido.produtos }</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</jsp:body>

</tags:pageTemplate>