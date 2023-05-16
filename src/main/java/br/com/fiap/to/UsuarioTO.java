package br.com.fiap.to;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioTO {

	private Integer id;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public UsuarioTO() {
		
	}
	
	public  UsuarioTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
}
