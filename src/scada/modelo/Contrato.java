package scada.modelo;

import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class Contrato implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private GregorianCalendar inicioVigencia;
    private GregorianCalendar fimVigencia;
    private Integer tipo;
    private Integer periodicidadeReajuste;

	public Contrato() {
	}

	public Contrato(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public GregorianCalendar getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(GregorianCalendar inicioVigencia){
        this.inicioVigencia = inicioVigencia;
    }

    public GregorianCalendar getFimVigencia() {
        return fimVigencia;
    }

    public Integer getTipo() {
    	return tipo;
    }
    
    public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
    
    public void setFimVigencia(GregorianCalendar fimVigencia){
        this.fimVigencia = fimVigencia;
    }

    public Integer getPeriodicidadeReajuste() {
        return periodicidadeReajuste;
    }

    public void setPeriodicidadeReajuste(Integer periodicidadeReajuste){
        this.periodicidadeReajuste = periodicidadeReajuste;
    }
}
