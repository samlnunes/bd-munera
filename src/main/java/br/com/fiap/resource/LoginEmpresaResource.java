package br.com.fiap.resource;

import java.sql.SQLException;
import java.util.Date;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

import br.com.fiap.dao.LoginEmpresaDAO;
import br.com.fiap.to.EmpresaTO;
import br.com.fiap.to.LoginEmpresaTO;

@Path("/login-empresa")
public class LoginEmpresaResource {
	SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authEmpresa(LoginEmpresaTO request) throws SQLException {
		LoginEmpresaDAO dao = new LoginEmpresaDAO();
		boolean autenticado;
		try {
			autenticado = dao.authEmpresa(request.getCnpj(), request.getSenha());
		} catch (SQLException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (autenticado) {
			EmpresaTO empresa = dao.buscarEmpresa(request.getCnpj());
			String jwt = buildJWT(empresa);
			return Response.status(Response.Status.OK).entity(new JwtResponse(jwt)).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}

	private String buildJWT(EmpresaTO empresa) {
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(empresa.getId_empresa())).setSubject(empresa.getCnpj())
				.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.claim("nome", empresa.getNome()).claim("email", empresa.getEmail()).claim("cnpj", empresa.getCnpj())
				.claim("id", empresa.getId_empresa()).claim("segmento", empresa.getSegmento());

		return builder.signWith(SignatureAlgorithm.HS256, key).compact();
	}

	public static class JwtResponse {
		private String jwt;

		public JwtResponse(String jwt) {
			this.jwt = jwt;
		}

		public String getJwt() {
			return jwt;
		}

		public void setJwt(String jwt) {
			this.jwt = jwt;
		}
	}
}
