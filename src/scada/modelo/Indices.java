package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

@Entity
public class Indices implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	private Double indice;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private ListaCotacao produto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getIndice() {
		return indice;
	}

	public void setIndice(Double indice) {
		this.indice = indice;
	}

	public ListaCotacao getProduto() {
		return produto;
	}

	public void setProduto(ListaCotacao produto) {
		this.produto = produto;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}