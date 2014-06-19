package easyquote.modelo;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import easyquote.hibernate.Entidade;




@Entity
public class Contrato implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private GregorianCalendar inicioVigencia;
    private GregorianCalendar fimVigencia;
    private Integer periodicidadeReajuste;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoContrato tipoContrato;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private ListaCotacao cotacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor fornecedor;

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ListaCotacao getCotacao() {
		return cotacao;
	}

	public void setCotacao(ListaCotacao cotacao) {
		this.cotacao = cotacao;
	}

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

	public TipoContrato getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(TipoContrato tipoContrato) {
		this.tipoContrato = tipoContrato;
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
