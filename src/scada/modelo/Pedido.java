package scada.modelo;

import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;



@Entity
public class Pedido implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private GregorianCalendar dataEntrega;
    private String status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor fornecedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Cotacao cotacao;

	public Pedido() {
	}

	public Pedido(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public GregorianCalendar getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(GregorianCalendar dataEntrega){
        this.dataEntrega = dataEntrega;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
}
