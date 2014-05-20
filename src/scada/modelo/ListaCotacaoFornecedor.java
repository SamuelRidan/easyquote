package scada.modelo;

import java.math.BigDecimal;
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

    private BigDecimal preco;
    private BigDecimal precoTotal;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private ListaCotacao listaCotacao;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private Cotacao cotacao;
    
    @ManyToOne (fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

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


    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco){
        this.preco = preco;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal){
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

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}
    
}
