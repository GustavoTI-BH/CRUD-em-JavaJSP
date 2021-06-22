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
		<p>Cadastro CRUD (Create, Read, Update e Delete )
			para a classe Pessoa utilizando a tecnologia JSP e a arquitetura
			MVC.</p>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${pessoa != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${pessoa == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${pessoa != null}">
                                    Editar Pessoas
                                </c:if>
						<c:if test="${pessoa == null}">
                                   Adicionar Nova Pessoas
                                </c:if>
					</h2>
				</caption>

				<c:if test="${pessoa != null}">
					<input type="hidden" name="id" value="<c:out value='${pessoa.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Nome: </label> <input type="text"
						value="<c:out value='${pessoa.nome}' />" class="form-control"
						name="nome" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>CPF: </label> <input type="text"
						value="<c:out value='${pessoa.cpf}' />" class="form-control"
						name="cpf" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Endereço: </label> <input type="text"
						value="<c:out value='${pessoa.endereco}' />" class="form-control"
						name="endereco" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Bairro:</label> <input type="text"
						value="<c:out value='${pessoa.bairro}' />" class="form-control"
						name="bairro" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>CEP:</label> <input type="text"
						value="<c:out value='${pessoa.cep}' />" class="form-control"
						name="cep" required="required">
				</fieldset>
				
				
				<fieldset class="form-group">
					<label>Cidade:</label> <input type="text"
						value="<c:out value='${pessoa.cidade}' />" class="form-control"
						name="cidade" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Estado:</label> <input type="text"
						value="<c:out value='${pessoa.estado}' />" class="form-control"
						name="estado" required="required">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Telefone:</label> <input type="text"
						value="<c:out value='${pessoa.telefone}' />" class="form-control"
						name="telefone" required="required">
				</fieldset>

				<button type="submit" class="btn btn-success">Salvar</button>
				</form>
			</div>
		</div>
	</div>
	<br>
	<br>
	
	<div class="jumbotron text-center" style="margin-bottom: 0">
		<p>
			Universidade salgado Filho (Universo) - POO IV<br>
			Desenvolvidor por <i>Gustavo Alves</i>&reg;<br>
		</p>
	</div>
</body>

</html>