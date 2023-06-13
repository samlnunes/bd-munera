package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.to.ClienteTO;

public class ClienteDAO {
	public List<ClienteTO> buscarClientes() throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ClienteTO> clientes = new ArrayList<>();

		try {
			String query = "SELECT * FROM cliente";
			statement = conn.prepareStatement(query);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				ClienteTO cliente = new ClienteTO();
				cliente.setId_cliente(resultSet.getInt("id_cliente"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEmail(resultSet.getString("email"));

				clientes.add(cliente);
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar clientes: " + e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return clientes;
	}

	public ClienteTO buscarClientePorId(int idCliente) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ClienteTO cliente = null;

		try {
			String query = "SELECT * FROM cliente WHERE id_cliente = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				cliente = new ClienteTO();
				cliente.setId_cliente(resultSet.getInt("id_cliente"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setEmail(resultSet.getString("email"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente por id: " + e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return cliente;
	}

	public void cadastrarCliente(ClienteTO cliente) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String query = "INSERT INTO cliente (id_cliente, nome, email, senha) VALUES (seq_cliente.NEXTVAL, ?, ?, ?)";
			statement = conn.prepareStatement(query);
			statement.setString(1, cliente.getNome());
			statement.setString(2, cliente.getEmail());
			statement.setString(3, cliente.getSenha());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar cliente: " + e);
		} finally {
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}
	}
}
