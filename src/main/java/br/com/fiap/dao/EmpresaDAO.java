package br.com.fiap.dao;

import br.com.fiap.to.EmpresaTO;
import br.com.fiap.conexoes.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpresaDAO {

	public List<EmpresaTO> listarEmpresas() throws SQLException {
		List<EmpresaTO> empresas = new ArrayList<>();
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String query = "SELECT * FROM empresas";
			statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				EmpresaTO empresa = new EmpresaTO();
				empresa.setId_empresa(rs.getInt("id_empresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setEmail(rs.getString("email"));
				empresa.setCnpj(rs.getString("cnpj"));

				empresas.add(empresa);
			}
		} catch (SQLException e) {
			System.out.print("Erro ao listar empresas! - " + e);
		} finally {
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return empresas;
	}

	public EmpresaTO buscarEmpresa(int id) throws SQLException {
		EmpresaTO empresa = null;
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String query = "SELECT * FROM empresas WHERE id_empresa = ?";
			statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				empresa = new EmpresaTO();
				empresa.setId_empresa(rs.getInt("id_empresa"));
				empresa.setNome(rs.getString("nome"));
				empresa.setEmail(rs.getString("email"));
				empresa.setCnpj(rs.getString("cnpj"));
			}
		} catch (SQLException e) {
			System.out.print("Erro ao buscar empresa! - " + e);
		} finally {
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}

		return empresa;
	}

	public void cadastrarEmpresa(EmpresaTO empresa) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String query = "INSERT INTO empresas (id_empresa, cnpj, nome, email, senha, segmento) VALUES (seq_empresas.NEXTVAL, ?, ?, ?, ?, 2)";
			statement = conn.prepareStatement(query);
			statement.setString(1, empresa.getCnpj());
			statement.setString(2, empresa.getNome());
			statement.setString(3, empresa.getEmail());
			statement.setString(4, empresa.getSenha());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar empresa: " + e);
		} finally {
			if (statement != null) {
				statement.close();
			}
			conn.close();
		}
	}
}
