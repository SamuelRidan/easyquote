package scada.modelo;

import java.util.GregorianCalendar;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;



@Entity
public class Cotacao implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private GregorianCalendar dataAbertura;
    private GregorianCalendar dataLimiteResposta;
    private Integer formaPgto;
    private Integer status;
    private Integer setor;
    private String obs;
    private Double valorTotal;
    
    @ManyToOne(fetch = FetchType.LAZY)
	private Operador responsavel;

	public Cotacao() {
	}

	public Cotacao(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public GregorianCalendar getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(GregorianCalendar dataAbertura){
        this.dataAbertura = dataAbertura;
    }

    public GregorianCalendar getDataLimiteResposta() {
        return dataLimiteResposta;
    }

    public void setDataLimiteResposta(GregorianCalendar dataLimiteResposta){
        this.dataLimiteResposta = dataLimiteResposta;
    }

    public Integer getFormaPgto() {
        return formaPgto;
    }

    public void setFormaPgto(Integer formaPgto){
        this.formaPgto = formaPgto;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status){
        this.status = status;
    }
    
    public Integer getSetor() {
        return setor;
    }

    public void setSetor(Integer setor){
        this.setor = setor;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs){
        this.obs = obs;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal){
        this.valorTotal = valorTotal;
    }
    
    public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Operador responsavel) {
		this.responsavel = responsavel;
	}
}
