package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.ClienteDAO;
import br.com.fiap.to.ClienteTO;

@Path("/clientes")
public class ClienteResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientes() {
        ClienteDAO dao = new ClienteDAO();
        try {
            List<ClienteTO> clientes = dao.buscarClientes();
            return Response.status(Response.Status.OK).entity(clientes).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{idCliente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientePorId(@PathParam("idCliente") int idCliente) {
        ClienteDAO dao = new ClienteDAO();
        try {
            ClienteTO cliente = dao.buscarClientePorId(idCliente);
            if (cliente != null) {
                return Response.status(Response.Status.OK).entity(cliente).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrarCliente(ClienteTO cliente) {
        try {
            ClienteDAO dao = new ClienteDAO();
            dao.cadastrarCliente(cliente);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
