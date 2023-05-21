package br.com.fiap.to;

import java.sql.Timestamp;

public class PostagemTO {
	private String legenda;
	private String midia;
	private Timestamp dataPostagem;
	private String idPostagem;
	private Number curtida;
	private Number idEmpresa;
	private String nomeEmpresa;
	
	public String getIdPostagem() {
		return idPostagem;
	}
	public void setIdPostagem(String idPostagem) {
		this.idPostagem = idPostagem;
	}
	public Number getCurtida() {
		return curtida;
	}
	public void setCurtida(Number curtida) {
		this.curtida = curtida;
	}
	public Number getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Number idEmpresa) {
		this.idEmpresa = idEmpresa;
	}	
	public Timestamp getDataPostagem() {
		return dataPostagem;
	}
	public void setDataPostagem(Timestamp dataPostagem) {
		this.dataPostagem = dataPostagem;
	}	
	public String getLegenda() {
		return legenda;
	}
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
	public String getMidia() {
		return midia;
	}
	public void setMidia(String midia) {
		this.midia = midia;
	}
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public PostagemTO() {
		
	}
	
	public PostagemTO(String legenda, String midia, Number idEmpresa) {
		this.legenda = legenda;
		this.midia = midia;
		this.idEmpresa = idEmpresa;
	}
	
}
