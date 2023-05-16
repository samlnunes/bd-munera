package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.to.UsuarioTO;

public class UsuarioDAO {

	public void insert(UsuarioTO usuario) throws SQLException {
		Connection conn = ConexaoFactory.getConnection();
		Statement statement;

		try {
			String query = String.format("insert into tb_usuario (id,nome) values(%s,'%s')", usuario.getId(),
					usuario.getNome());

			statement = conn.createStatement();
			statement.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Erro ao inserir o usuário! - " + e);
		} finally {
			conn.close();
		}
	}
}
