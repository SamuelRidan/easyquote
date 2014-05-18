package scada.modelo;

import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

public class Comprador extends Operador implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;
	
	private BigDecimal cpf;
	private String telefone;
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Operador pessoa;

	public Comprador() {
	}

	public Comprador(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public BigDecimal getCpf() {
		return cpf;
	}

	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Operador getPessoa() {
		return pessoa;
	}

	public void setPessoa(Operador pessoa) {
		this.pessoa = pessoa;
	}

}
