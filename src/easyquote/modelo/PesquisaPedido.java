package easyquote.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import easyquote.hibernate.Entidade;


@Entity
public class PesquisaPedido implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

	private String obs;
    private String pergunta1;
    private String pergunta2;
    private String pergunta3;
    private String pergunta4;
    private Integer pontuacao1;
    private Integer pontuacao2;
    private Integer pontuacao3;
    private Integer pontuacao4;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Fornecedor fornecedor;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

	public PesquisaPedido() {
	}

	public PesquisaPedido(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public String getPergunta1() {
        return pergunta1;
    }

    public void setPergunta1(String pergunta1){
        this.pergunta1 = pergunta1;
    }

    public String getPergunta2() {
        return pergunta2;
    }

    public void setPergunta2(String pergunta2){
        this.pergunta2 = pergunta2;
    }

    public String getPergunta3() {
        return pergunta3;
    }

    public void setPergunta3(String pergunta3){
        this.pergunta3 = pergunta3;
    }

    public String getPergunta4() {
        return pergunta4;
    }

    public void setPergunta4(String pergunta4){
        this.pergunta4 = pergunta4;
    }

    public Integer getPontuacao1() {
        return pontuacao1;
    }

    public void setPontuacao1(Integer pontuacao1){
        this.pontuacao1 = pontuacao1;
    }

    public Integer getPontuacao2() {
        return pontuacao2;
    }

    public void setPontuacao2(Integer pontuacao2){
        this.pontuacao2 = pontuacao2;
    }

    public Integer getPontuacao3() {
        return pontuacao3;
    }

    public void setPontuacao3(Integer pontuacao3){
        this.pontuacao3 = pontuacao3;
    }

    public Integer getPontuacao4() {
        return pontuacao4;
    }

    public void setPontuacao4(Integer pontuacao4){
        this.pontuacao4 = pontuacao4;
    }

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
    
}
