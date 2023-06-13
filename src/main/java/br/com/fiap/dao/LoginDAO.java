package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.to.EmpresaTO;
import br.com.fiap.to.ClienteTO;

public class LoginDAO {
	
	public boolean authEmpresa(String cnpj, String senha) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String query = "SELECT COUNT(*) FROM empresas WHERE cnpj = ? AND senha = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, cnpj);
			statement.setString(2, senha);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao autenticar empresa: " + e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return false;
	}

	public EmpresaTO buscarEmpresa(String cnpj) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		EmpresaTO empresa = null;

		try {
			String query = "SELECT * FROM empresas WHERE cnpj = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, cnpj);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				 empresa = new EmpresaTO();
				 empresa.setId_empresa(resultSet.getInt("id_empresa"));
				 empresa.setCnpj(resultSet.getString("cnpj"));
				 empresa.setEmail(resultSet.getString("email"));
				 empresa.setNome(resultSet.getString("nome"));
				 empresa.setSegmento(resultSet.getInt("segmento"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar empresa por CNPJ: " + e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return empresa;
	}

	public boolean authCliente(String email, String senha) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			String query = "SELECT COUNT(*) FROM cliente WHERE email = ? AND senha = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, email);
			statement.setString(2, senha);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao autenticar cliente: " + e);
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return false;
	}

	public ClienteTO buscarCliente(String email) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ClienteTO cliente = null;

		try {
			String query = "SELECT * FROM cliente WHERE email = ?";
			statement = conn.prepareStatement(query);
			statement.setString(1, email);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				cliente = new ClienteTO();
				cliente.setId_cliente(resultSet.getInt("id_cliente"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setSenha(resultSet.getString("senha"));
				cliente.setNome(resultSet.getString("nome"));
			}
		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente por email: " + e);
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
}
