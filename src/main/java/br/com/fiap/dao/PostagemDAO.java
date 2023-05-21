package br.com.fiap.dao;

import br.com.fiap.to.PostagemTO;
import br.com.fiap.main.S3Uploader;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import br.com.fiap.conexoes.ConexaoFactory;

public class PostagemDAO {

	public void postar(PostagemTO postagem) throws SQLException {

		Connection conn = ConexaoFactory.getConnection();
		PreparedStatement statement = null;

		try {
			String querySeq = "SELECT sequencia_postagem.NEXTVAL FROM DUAL";
			Statement seqStatement = conn.createStatement();
			ResultSet seqResult = seqStatement.executeQuery(querySeq);
			seqResult.next();
			int idPostagem = seqResult.getInt(1);

			String query = "INSERT INTO postagem (ID_POSTAGEM, LEGENDA, MIDIA, DATA_POSTAGEM) VALUES (?, ?, ?, ?)";
			statement = conn.prepareStatement(query);
			statement.setInt(1, idPostagem);
			statement.setString(2, postagem.getLegenda());

			String objectKey = idPostagem + ".jpg";

			String imgURL = S3Uploader.uploadImageToS3(postagem.getMidia(), objectKey);

			statement.setString(3, imgURL);

			LocalDateTime dataHoraAtual = LocalDateTime.now();
			Timestamp timestamp = Timestamp.valueOf(dataHoraAtual);
			statement.setTimestamp(4, timestamp);

			statement.executeUpdate();

		} catch (Exception e) {
			System.out.print("Erro ao postar! - " + e);

		} finally {
			conn.close();
		}
	}

	public List<PostagemTO> exibir() throws SQLException {
		List<PostagemTO> postagens = new ArrayList<>();
		Connection conn = ConexaoFactory.getConnection();
		Statement statement;

		try {
			String query = "SELECT * FROM postagem";
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				PostagemTO postagem = new PostagemTO();
				postagem.setIdPostagem(rs.getString("ID_POSTAGEM"));
				postagem.setDataPostagem(rs.getTimestamp("DATA_POSTAGEM"));
				postagem.setLegenda(rs.getString("LEGENDA"));
				postagem.setCurtida(rs.getInt("CURTIDA"));
				postagem.setEmpresaCnpj(rs.getString("FK_EMPRESA_CNPJ2"));

				Blob blob = rs.getBlob("MIDIA");
				if (blob != null) {
					byte[] bytes = blob.getBytes(1, (int) blob.length());

					String base64 = Base64.getEncoder().encodeToString(bytes);
					postagem.setMidia(base64);
				} else {
					postagem.setMidia("");
				}

				Timestamp dataPostagem = rs.getTimestamp("DATA_POSTAGEM");
				postagem.setDataPostagem(dataPostagem);

				postagens.add(postagem);
			}
		} catch (Exception e) {
			System.out.print("Erro ao exibir! - " + e);
		} finally {
			conn.close();
		}

		return postagens;

	}
}
