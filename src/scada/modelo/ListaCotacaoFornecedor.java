package scada.modelo;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class ListaCotacaoFornecedor implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private BigDecimal preco;
    private BigDecimal precoTotal;
    private Integer idListaCotacao;
    private Integer cotacao;
    private Integer idFornecedor;

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

    public Integer getIdListaCotacao() {
        return idListaCotacao;
    }

    public void setIdListaCotacao(Integer idListaCotacao){
        this.idListaCotacao = idListaCotacao;
    }

    public Integer getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(Integer idFornecedor){
        this.idFornecedor = idFornecedor;
    }

	public Integer getCotacao() {
		return cotacao;
	}

	public void setCotacao(Integer cotacao) {
		this.cotacao = cotacao;
	}
    
}
