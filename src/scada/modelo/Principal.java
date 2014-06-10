package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;



@Entity
public class Principal implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private Double precoTotal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Contrato contrato;

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Principal() {
	}

	public Principal(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public Double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(Double precoTotal){
        this.precoTotal = precoTotal;
    }
}
