package easyquote.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Contrato;
import easyquote.modelo.Fornecedor;
import easyquote.modelo.Indices;
import easyquote.modelo.ListaCotacao;
import easyquote.modelo.Produto;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;

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
	}
	
	@SuppressWarnings("rawtypes")
	@Path("/indices/reajustarIndices/{contrato.id}")
	@Funcionalidade(nome = "Índices de reajuste dos produtos para Fornecedores")
	public void reajustarIndices(Contrato contrato){
		
		List indices = null;
		
		contrato = (Contrato) UtilController.preencherFiltros(contrato, "contrato.id", sessaoGeral);
		List<Contrato> contratos = hibernateUtil.buscar(contrato);
		result.include("contrato", contratos);		
		
		List lista = hibernateUtil.buscaPorHQL("from ListaCotacao where cotacao.id = "+ contratos.get(0).getCotacao().getCotacao().getId());
		result.include("listaCotacaos", lista);
		for (Object obj: lista){
			ListaCotacao lc = (ListaCotacao)obj;
			indices = hibernateUtil.buscaPorHQL("from Indices where produto.id = "+ lc.getId());
		}
		result.include("indices", indices);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
		List<Fornecedor> fornecedor = hibernateUtil.buscar(new Fornecedor());
		result.include("tipoFornecedor", fornecedor);
	
	}
	
	@SuppressWarnings("rawtypes")
	@Path("/indice/salvarListaIndice")
	@Funcionalidade(filhaDe = "reajustarIndices")
	public void salvarListaIndice(Integer forn, Integer produto, Double indice){
		Indices i = new Indices();
		i.setIndice(indice);
		List listaCotacao = hibernateUtil.buscaPorHQL("from ListaCotacao where id = "+produto);
		for(Object obj: listaCotacao){
			ListaCotacao lc = (ListaCotacao)obj;
			i.setProduto(lc);
		}
		
		List fornecedor = hibernateUtil.buscaPorHQL("from Fornecedor where id = "+forn);
		for (Object obj: fornecedor){
			Fornecedor f = (Fornecedor)obj;
			i.setFornecedor(f);
		}
		
		hibernateUtil.salvarOuAtualizar(i);
		result.use(Results.json()).from("ok").serialize();
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
