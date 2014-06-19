package easyquote.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Operador extends Pessoa {

    private String login;
    private String senha;
    private Boolean ativo;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private GrupoOperador grupoOperador;

	public Operador() {
	}

	public Operador(Integer id) {
		this.setId(id);
	}

	public String getLogin() {
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo){
        this.ativo = ativo;
    }
    
	public GrupoOperador getGrupoOperador() {
		return grupoOperador;
	}

	public void setGrupoOperador(GrupoOperador grupoOperador) {
		this.grupoOperador = grupoOperador;
	}
}

