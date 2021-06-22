package net.javaguides.pessoamanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.pessoamanagement.dao.PessoaDAO;
import net.javaguides.pessoamanagement.model.Pessoa;

@WebServlet("/")
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PessoaDAO pessoaDAO;

	public void init() {
		pessoaDAO = new PessoaDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertPessoa(request, response);
				break;
			case "/delete":
				deletePessoa(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updatePessoa(request, response);
				break;
			default:
				listPessoa(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listPessoa(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Pessoa> listPessoa = pessoaDAO.selectAllPessoas();
		request.setAttribute("listPessoa", listPessoa);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pessoa-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("pessoa-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Pessoa existingPessoa = pessoaDAO.selectPessoa(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pessoa-form.jsp");
		request.setAttribute("pessoa", existingPessoa);
		dispatcher.forward(request, response);

	}

	private void insertPessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cep = request.getParameter("cep");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String telefone = request.getParameter("telefone");
		Pessoa newPessoa = new Pessoa(nome, cpf, endereco, bairro, cep, cidade, estado, telefone);
		pessoaDAO.insertPessoa(newPessoa);
		response.sendRedirect("list");
	}

	private void updatePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String endereco = request.getParameter("endereco");
        String bairro = request.getParameter("bairro");
        String cep = request.getParameter("cep");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");
        String telefone = request.getParameter("telefone");

		Pessoa book = new Pessoa(id, nome, cpf, endereco, bairro, cep, cidade, estado, telefone);
		pessoaDAO.updatePessoa(book);
		response.sendRedirect("list");
	}

	private void deletePessoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		pessoaDAO.deletePessoa(id);
		response.sendRedirect("list");

	}
}