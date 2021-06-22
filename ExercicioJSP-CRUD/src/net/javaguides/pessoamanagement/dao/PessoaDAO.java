package net.javaguides.pessoamanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguides.pessoamanagement.model.Pessoa;

public class PessoaDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/banco_crud?useSSL=false";
    private String jdbcpessoanome = "root";
    private String jdbcPassword = "";

    private static final String INSERT_PESSOAS_SQL = "INSERT INTO pessoa" + " (nome, cpf, endereco, bairro, cep, cidade, estado, telefone) VALUES " +
        "(?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_PESSOAS_BY_ID = "SELECT id, nome, cpf, endereco, bairro, cep, cidade, estado, telefone FROM pessoa WHERE id =?";
    private static final String SELECT_ALL_PESSOAS = "SELECT * FROM pessoa";
    private static final String DELETE_PESSOAS_SQL = "DELETE FROM pessoa WHERE id = ?;";
    private static final String UPDATE_PESSOAS_SQL = "UPDATE pessoa SET nome = ?,cpf= ?, endereco =?, bairro =?, cep =?, cidade =?, estado =?, telefone =?  WHERE id = ?;";

    public PessoaDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcpessoanome, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertPessoa(Pessoa pessoa) throws SQLException {
        System.out.println(INSERT_PESSOAS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PESSOAS_SQL)) {
            preparedStatement.setString(1, pessoa.getNome());
            preparedStatement.setString(2, pessoa.getCpf());
            preparedStatement.setString(3, pessoa.getEndereco());
            preparedStatement.setString(4, pessoa.getBairro());
            preparedStatement.setString(5, pessoa.getCep());
            preparedStatement.setString(6, pessoa.getCidade());
            preparedStatement.setString(7, pessoa.getEstado());
            preparedStatement.setString(8, pessoa.getTelefone());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Pessoa selectPessoa(int id) {
        Pessoa pessoa = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PESSOAS_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String telefone = rs.getString("telefone");
                pessoa = new Pessoa(id, nome, cpf, endereco, bairro, cep, cidade, estado, telefone);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return pessoa;
    }

    public List < Pessoa > selectAllPessoas() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Pessoa > pessoas = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PESSOAS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
            	int id = rs.getInt("id");
            	String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String endereco = rs.getString("endereco");
                String bairro = rs.getString("bairro");
                String cep = rs.getString("cep");
                String cidade = rs.getString("cidade");
                String estado = rs.getString("estado");
                String telefone = rs.getString("telefone");
                pessoas.add(new Pessoa(id, nome, cpf, endereco, bairro, cep, cidade, estado, telefone));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return pessoas;
    }

    public boolean deletePessoa(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PESSOAS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatePessoa(Pessoa pessoa) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PESSOAS_SQL);) {
        	statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getCpf());
            statement.setString(3, pessoa.getEndereco());
            statement.setString(4, pessoa.getBairro());
            statement.setString(5, pessoa.getCep());
            statement.setString(6, pessoa.getCidade());
            statement.setString(7, pessoa.getEstado());
            statement.setString(8, pessoa.getTelefone());
            statement.setInt(9, pessoa.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
