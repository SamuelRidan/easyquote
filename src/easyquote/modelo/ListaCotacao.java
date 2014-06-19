package easyquote.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import easyquote.hibernate.Entidade;




@Entity
public class ListaCotacao implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;	
	
    private Integer quantidade;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Cotacao cotacao;

	public ListaCotacao() {
	}

	public ListaCotacao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade){
        this.quantidade = quantidade;
    }

	public Cotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(Cotacao cotacao) {
		this.cotacao = cotacao;
	}

}
