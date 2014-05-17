package scada.modelo;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

public class Fornecedor implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;
	
	private BigDecimal cnpj;
	private String razao_soacial;
	private String telefone;
	private String segmento;
	private String reputacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;

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
	
	public BigDecimal getCnpj() {
		return cnpj;
	}

	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao_soacial() {
		return razao_soacial;
	}

	public void setRazao_soacial(String razao_soacial) {
		this.razao_soacial = razao_soacial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
