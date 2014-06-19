package easyquote.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import easyquote.hibernate.Entidade;




@Entity
public class Pessoa implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private String nome;
    private Integer codSAP;
    private String postoGraduacao;

	public Pessoa() {
	}

	public Pessoa(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public Integer getCodSAP() {
        return codSAP;
    }

    public void setCodSAP(Integer codSAP){
        this.codSAP = codSAP;
    }
    
    public String getPostoGraduacao() {
		return postoGraduacao;
	}

	public void setPostoGraduacao(String postoGraduacao) {
		this.postoGraduacao = postoGraduacao;
	}


}
