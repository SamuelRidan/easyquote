package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

@Entity
public class Fornecedor implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String cnpj;
	private String razao_social;
	private String telefone;
	private String email;
	private String segmento;
	private String reputacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Operador operador;

	public Fornecedor() {
	}

	public Fornecedor(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao_soacial() {
		return razao_social;
	}

	public void setRazao_soacial(String razao_social) {
		this.razao_social = razao_social;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}

	public void getEmail(String email) {
		this.email = email;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getReputacao() {
		return reputacao;
	}

	public void setReputacao(String reputacao) {
		this.reputacao = reputacao;
	}

	public Operador getPessoa() {
		return operador;
	}

	public void setPessoa(Operador operador) {
		this.operador = operador;
	}

}
