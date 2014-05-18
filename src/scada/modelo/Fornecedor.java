package scada.modelo;

<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class Fornecedor implements Entidade {
=======
import java.math.BigDecimal;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import scada.hibernate.Entidade;

public class Fornecedor extends Pessoa implements Entidade {
>>>>>>> e94adacabe1998ff8a6abdbbf02deda203db1bea

	@Id
	@GeneratedValue
	private Integer id;
<<<<<<< HEAD

    private String cnpj;
    private String razao_social;
    private String telefone;
    private String segmento;
    private String reputacao;
    private Integer pessoa;
=======
	
	private BigDecimal cnpj;
	private String razao_soacial;
	private String telefone;
	private String email;
	private String segmento;
	private String reputacao;
	
	@ManyToOne(fetch = FetchType.LAZY)
    private Pessoa pessoa;
>>>>>>> e94adacabe1998ff8a6abdbbf02deda203db1bea

	public Fornecedor() {
	}

<<<<<<< HEAD

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social){
        this.razao_social = razao_social;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento){
        this.segmento = segmento;
    }

    public String getReputacao() {
        return reputacao;
    }

    public void setReputacao(String reputacao){
        this.reputacao = reputacao;
    }

    public Integer getPessoa() {
        return pessoa;
    }

    public void setPessoa(Integer pessoa){
        this.pessoa = pessoa;
    }
=======
	public Fornecedor(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public BigDecimal getCnpj() {
		return cnpj;
	}

	public void setCnpj(BigDecimal cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao_soacial() {
		return razao_soacial;
	}

	public void setRazao_soacial(String razao_soacial) {
		this.razao_soacial = razao_soacial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEmail() {
		return email;
	}

	public void getEmail(String email) {
		this.email = email;
	}

	public String getSegmento() {
		return segmento;
	}

	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}

	public String getReputacao() {
		return reputacao;
	}

	public void setReputacao(String reputacao) {
		this.reputacao = reputacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

>>>>>>> e94adacabe1998ff8a6abdbbf02deda203db1bea
}
