package br.com.fiap.to;

public class EmpresaTO {
	private Integer id_empresa;
	private String cnpj;
	private String nome;
	private String email;
	private String senha;
	private Integer segmento;
	
	public Integer getId_empresa() {
		return id_empresa;
	}
	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getSegmento() {
		return segmento;
	}
	public void setSegmento(Integer segmento) {
		this.segmento = segmento;
	}

	public EmpresaTO() {
		
	}
	
}
