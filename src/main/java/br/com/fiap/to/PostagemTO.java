package br.com.fiap.to;

import java.sql.Timestamp;

public class PostagemTO {
	private String legenda;
	private String midia;
	private Timestamp dataPostagem;
	private String idPostagem;
	private Number curtida;
	private String empresaCnpj;
	
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
	public String getEmpresaCnpj() {
		return empresaCnpj;
	}
	public void setEmpresaCnpj(String empresaCnpj) {
		this.empresaCnpj = empresaCnpj;
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
	public PostagemTO() {
		
	}
	
	public PostagemTO(String legenda, String midia) {
		this.legenda = legenda;
		this.midia = midia;
	}
	
}
