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
	private Integer idCotacao;
	private Integer idFornecedor;
	private  Integer id_pesquisa;
	private String    status;	

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

	public Integer getIdCotacao() {
		return idCotacao;
	}

	public void setIdCotacao(Integer idCotacao) {
		this.idCotacao = idCotacao;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getId_pesquisa() {
		return id_pesquisa;
	}

	public void setId_pesquisa(Integer id_pesquisa) {
		this.id_pesquisa = id_pesquisa;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
