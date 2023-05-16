package br.com.fiap.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

@Path("/login")
public class UsuarioResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String buscar() {
		return "Vasco Gigante";
	}

	@POST
	@Consumes(MediaType.TEXT_PLAIN)

	public Response auth(String nome) throws ClassNotFoundException, SQLException {
		UsuarioDAO dao = new UsuarioDAO();
		UsuarioTO user = new UsuarioTO();

		user.setId(1509);
		user.setNome(nome);

		dao.insert(user);

		return Response.status(Response.Status.CREATED).build();
	}

}
