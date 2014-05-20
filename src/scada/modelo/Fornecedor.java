package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;import java.math.BigDecimal;

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
    private String razao_social;
    private String telefone;
    private String segmento;
    private String reputacao;
	private BigDecimal cnpj;
<<<<<<< HEAD
=======
	private String razao_social;
	private String telefone;
>>>>>>> 0d43bc937057fa412b9f7a90fdd55a3969517de9
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
    private Operador operador;

	public Fornecedor() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRazao_social() {
		return razao_social;
	}

<<<<<<< HEAD
	public void setRazao_social(String razao_social) {
=======
	public String getRazao_soacial() {
		return razao_social;
	}

	public void setRazao_soacial(String razao_social) {
>>>>>>> 0d43bc937057fa412b9f7a90fdd55a3969517de9
		this.razao_social = razao_social;
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

<<<<<<< HEAD
	public BigDecimal getCnpj() {
		return cnpj;
	}

	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Pessoa getPessoa() {
		return pessoa;
=======
	public Operador getPessoa() {
		return operador;
>>>>>>> 0d43bc937057fa412b9f7a90fdd55a3969517de9
	}

	public void setPessoa(Operador operador) {
		this.operador = operador;
	}



}
