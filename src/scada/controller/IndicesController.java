package scada.controller;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Contrato;
import scada.modelo.Indices;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class IndicesController {
	
	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;
	
	public IndicesController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}
	
	@Funcionalidade(nome = "Criar e editar contratos")
	public void criarEditarIndices(){
		
	}
	
	@Funcionalidade(filhaDe = "criarEditarIndices")
	public void criarIndices() {		
		sessaoGeral.adicionar("idIndices", null);
		result.forwardTo(this).criarEditarIndices();
	}
	
	@Path("/indices/editarIndices/{indices.id}")
	@Funcionalidade(filhaDe = "criarEditarIndices")
	public void editarIndices(Indices indices) {

		indices = hibernateUtil.selecionar(indices);

		sessaoGeral.adicionar("idIndices", indices.getId());
		result.include(indices);
		result.forwardTo(this).criarEditarIndices();
	}
	
	@Funcionalidade(filhaDe = "criarEditarIndices")
	public void salvarIndices(Indices indices) {
		if (Util.preenchido(sessaoGeral.getValor("idIndices"))) {
			indices.setId((Integer) sessaoGeral.getValor("idIndices"));
		}
		
		hibernateUtil.salvarOuAtualizar(indices);
		result.include("sucesso", "Contrato salvo(a) com sucesso");
		result.redirectTo(ContratoController.class).listarContratos(new Contrato(), null);
	}
	
	@Funcionalidade(nome = "Listar Índices")
	public void listarIndices(){
	}


}
