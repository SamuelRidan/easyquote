package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

@Entity
public class ListaCotacaoFornecedor implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private Double preco;
    private Double precoTotal;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private ListaCotacao listaCotacao;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private Cotacao cotacao;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private Fornecedor fornecedor;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private Pagamento formPgto;

	public ListaCotacaoFornecedor() {
	}

	public ListaCotacaoFornecedor(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco){
        this.preco = preco;
    }

    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal){
        this.precoTotal = precoTotal;
    }

    public ListaCotacao getListaCotacao() {
		return listaCotacao;
	}

	public void setListaCotacao(ListaCotacao listaCotacao) {
		this.listaCotacao = listaCotacao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
    
	public Pagamento getFormPgto() {
		return formPgto;
	}

	public void setFormPgto(Pagamento formPgto) {
		this.formPgto = formPgto;
	}
}
