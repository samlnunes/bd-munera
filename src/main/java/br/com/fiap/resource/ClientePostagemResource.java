package br.com.fiap.resource;

import java.sql.SQLException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.ClientePostagemDAO;
import br.com.fiap.to.ClientePostagemTO;

@Path("/interagirPostagem")
public class ClientePostagemResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response salvarClientePostagem(ClientePostagemTO clientePostagem) throws SQLException {
		ClientePostagemDAO dao = new ClientePostagemDAO();
		dao.curtir(clientePostagem);
		
		return Response.status(Response.Status.CREATED).build();
	}
}
