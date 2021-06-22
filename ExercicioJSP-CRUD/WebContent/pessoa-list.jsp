<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">

<head>
<title>Aplicativo de gerenciamento de Pessoas</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>Exercicio JSP + Boostrap</h1>
		<p>Cadastro CRUD (Create, Read, Update e Delete ) para a classe
			Pessoa utilizando a tecnologia JSP e a arquitetura MVC.</p>
	</div>

	<header>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Listar Pessoas</a></li>
				<li><a href="<%=request.getContextPath()%>/new"
					class="nav-link">Adicionar Pessoas</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">

		<div class="container">
			<h3 class="text-center">Lista de Pessoas</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Adicionar
					Nova Pessoas</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>CPF</th>
						<th>Endereço</th>
						<th>Bairro</th>
						<th>CEP</th>
						<th>Cidade</th>
						<th>Estado</th>
						<th>Telefone</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="pessoa" items="${listPessoa}">

						<tr>
							<td><c:out value="${pessoa.id}" /></td>
							<td><c:out value="${pessoa.nome}" /></td>
							<td><c:out value="${pessoa.cpf}" /></td>
							<td><c:out value="${pessoa.endereco}" /></td>
							<td><c:out value="${pessoa.bairro}" /></td>
							<td><c:out value="${pessoa.cep}" /></td>
							<td><c:out value="${pessoa.cidade}" /></td>
							<td><c:out value="${pessoa.estado}" /></td>
							<td><c:out value="${pessoa.telefone}" /></td>
							<td style="white-space: nowrap;"><a
								href="edit?id=<c:out value='${pessoa.id}' />"
								class="btn btn-info">Editar</a> <a
								href="delete?id=<c:out value='${pessoa.id}' />"
								class="btn btn-danger" >Deletar</a></td>
						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>

	<br>
	<br>
	<br>
	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>
			Universidade salgado Filho (Universo) - POO IV<br> Desenvolvidor
			por <i>Gustavo Alves</i>&reg;<br>
		</p>
	</div>
</body>

</html>