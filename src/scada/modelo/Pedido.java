package scada.modelo;

import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class Pedido implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private GregorianCalendar dataEntrega;

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
}
