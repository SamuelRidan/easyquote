package scada.modelo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import scada.hibernate.Entidade;

@Entity
public class Comprador implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	private BigDecimal cpf;
	private String telefone;
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Operador operador;

	public Comprador(Integer id) {

		this.id = id;
	}

	public Comprador() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getCpf() {
		return cpf;
	}

	public void setCpf(BigDecimal cpf) {
		this.cpf = cpf;
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

	public void setEmail(String email) {
		this.email = email;
	}

	public Operador getOperador() {
		return operador;
	}

	public void setOperador(Operador operador) {
		this.operador = operador;
	}
}
