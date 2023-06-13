package br.com.fiap.resource;

import br.com.fiap.dao.EmpresaDAO;
import br.com.fiap.to.EmpresaTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/empresas")
public class EmpresaResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EmpresaTO> listarEmpresas() throws SQLException {
		EmpresaDAO dao = new EmpresaDAO();
		return dao.listarEmpresas();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EmpresaTO buscarEmpresa(@PathParam("id") int id) throws SQLException {
		EmpresaDAO dao = new EmpresaDAO();
		return dao.buscarEmpresa(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarEmpresa(EmpresaTO empresa) {
		try {
			EmpresaDAO dao = new EmpresaDAO();
			dao.cadastrarEmpresa(empresa);
			return Response.status(Response.Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
