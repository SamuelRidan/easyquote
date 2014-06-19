package easyquote.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import easyquote.hibernate.Entidade;




@Entity
public class StatusPedido implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private String descricao;

	public StatusPedido() {
	}

	public StatusPedido(Integer id) {
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
}
