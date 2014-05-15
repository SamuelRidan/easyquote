package scada.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import scada.hibernate.Entidade;



@Entity
public class Fornecedor implements Entidade {

	@Id
	@GeneratedValue
	private Integer id;

    private String cnpj;
    private String razao_social;
    private String telefone;
    private String segmento;
    private String reputacao;
    private Integer pessoa;

	public Fornecedor() {
	}


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
}
