package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class Produto implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private String descricao;
	private Double preco;
	private Integer quantidade;
    private Integer codSAP;
    
	public Produto() {
	}

	public Produto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }
	
    public void setPreco(Double preco){
        this.preco = preco;
    }

    public Integer getCodSAP() {
        return codSAP;
    }

    public void setCodSAP(Integer codSAP){
        this.codSAP = codSAP;
    }

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
    
}
