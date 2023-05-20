package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.fiap.conexoes.ConexaoFactory;
import br.com.fiap.to.ClientePostagemTO;

public class ClientePostagemDAO {
	public void curtir(ClientePostagemTO clientePostagem) throws SQLException {

		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String query = "UPDATE postagem SET curtida = NVL(curtida, 0) + 1 WHERE id_postagem = TO_NUMBER(?)";
			statement = conn.prepareStatement(query);
            statement.setString(1, clientePostagem.getIdPostagem());
            statement.executeUpdate();
		} catch (Exception e) {
			System.out.print("Erro ao curtir! - " + e);

		} finally {
			conn.close();
		}
	}
}
