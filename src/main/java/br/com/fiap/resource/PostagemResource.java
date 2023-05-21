package br.com.fiap.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;

import org.json.JSONArray;
import org.json.JSONObject;

import br.com.fiap.dao.PostagemDAO;
import br.com.fiap.to.PostagemTO;

@Path("/postagem")
public class PostagemResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String exibir() throws SQLException {
	    PostagemDAO dao = new PostagemDAO();
	    List<PostagemTO> postagens = dao.exibir();

	    JSONArray jsonPostagens = new JSONArray();

	    for (PostagemTO postagem : postagens) {
	        JSONObject jsonPostagem = new JSONObject();
	        jsonPostagem.put("id", postagem.getIdPostagem());
	        jsonPostagem.put("date_time", postagem.getDataPostagem());
	        jsonPostagem.put("legenda", postagem.getLegenda());
	        jsonPostagem.put("midia", postagem.getMidia());
	        jsonPostagem.put("curtidas", postagem.getCurtida());
	        jsonPostagem.put("empresaId", postagem.getIdEmpresa());
	        jsonPostagem.put("empresaName", postagem.getNomeEmpresa());

	        jsonPostagens.put(jsonPostagem);
	    }

	    return jsonPostagens.toString();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	public String auth(PostagemTO postagem) throws ClassCastException, SQLException {
		
		PostagemDAO dao = new PostagemDAO();
		
		dao.postar(postagem);
		
		return "Postado!";
		
	}

	
}
