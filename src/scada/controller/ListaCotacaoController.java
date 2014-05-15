package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Cotacao;
import scada.modelo.ListaCotacao;
import scada.modelo.Produto;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;
import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ListaCotacaoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public ListaCotacaoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void criarListaCotacao(Cotacao cotacao) {

		sessaoGeral.adicionar("idListaCotacao", null);		
	//	sessaoGeral.adicionar("cotacao", cotacao.getId());
		result.forwardTo(this).criarEditarListaCotacao();
		
	}

	@Path("/listaCotacao/editarListaCotacao/{listaCotacao.id}")
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void editarListaCotacao(ListaCotacao listaCotacao) {

		listaCotacao = hibernateUtil.selecionar(listaCotacao);

		sessaoGeral.adicionar("idListaCotacao", listaCotacao.getId());
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
		result.include(listaCotacao);
		result.forwardTo(this).criarEditarListaCotacao();
	}

	@Funcionalidade(nome = "Criar e editar lista de produtos para cotação")
	public void criarEditarListaCotacao() {            		
	}
	
	@Path("/listaCotacao/excluirListaCotacao/{listaCotacao.id}")
	@Funcionalidade(nome = "Excluir listaCotacao")
	public void excluirListaCotacao(ListaCotacao listaCotacao) {

		hibernateUtil.deletar(listaCotacao);
		result.include("sucesso", "ListaCotacao excluído(a) com sucesso");
		result.forwardTo(this).listarListaCotacaos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void salvarListaCotacao(ListaCotacao listaCotacao) {

		if (Util.preenchido(sessaoGeral.getValor("idListaCotacao"))) {

			listaCotacao.setId((Integer) sessaoGeral.getValor("idListaCotacao"));
		}

		hibernateUtil.salvarOuAtualizar(listaCotacao);
		result.include("sucesso", "Cotação salva com sucesso");
		result.redirectTo(this).listarListaCotacaos(null, null);
		
	}

	@Funcionalidade(nome = "ListaCotacaos", modulo = "New")
	public void listarListaCotacaos(ListaCotacao listaCotacao, Integer pagina) {

		listaCotacao = (ListaCotacao) UtilController.preencherFiltros(listaCotacao, "listaCotacao", sessaoGeral);
		if (Util.vazio(listaCotacao)) {
			listaCotacao = new ListaCotacao();
		}

		List<ListaCotacao> listaCotacaos = hibernateUtil.buscar(listaCotacao, pagina);
		result.include("listaCotacaos", listaCotacaos);
		
		List<Cotacao> cotacao = hibernateUtil.buscar(new Cotacao());
		result.include("tipoCotacao", cotacao);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);

	}
	
	@Path("/listaCotacao/listarListaCotacaos/{listaCotacao.Cotacao.id}")
	public void listarListaCotacaos(ListaCotacao listaCotacao, Integer pagina, Integer i) {

		listaCotacao = (ListaCotacao) UtilController.preencherFiltros(listaCotacao, "listaCotacao.Cotacao.id", sessaoGeral);
		
		if (Util.vazio(listaCotacao)) {
			listaCotacao = new ListaCotacao();
		}

		List<ListaCotacao> listaCotacaos = hibernateUtil.buscar(listaCotacao, pagina);
		result.include("listaCotacaos", listaCotacaos);
		
		List<Cotacao> cotacao = hibernateUtil.buscar(new Cotacao());
		result.include("tipoCotacao", cotacao);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);

	}
	
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void salvarProdutoLista(Integer prod, Integer quantidade, Integer idCotacao) {

		ListaCotacao lista = new ListaCotacao();

		List produto = hibernateUtil.buscar(new Produto());		
		for (Object obj: produto){
			Produto p = (Produto)obj;			
			if (p.getId() == prod){
				lista.setProduto(p);
			}			
		}
		
		lista.setQuantidade(quantidade);
		
		List cotacao = hibernateUtil.buscar(new Cotacao());		
		for (Object obj: cotacao) {
            Cotacao c = (Cotacao)obj;            
            if (c.getId() == idCotacao){
            	lista.setCotacao(c);
            }            
		}		
		
		hibernateUtil.salvarOuAtualizar(lista);
		result.use(Results.json()).from("ok").serialize();

	}
	
	@Funcionalidade(nome = "Excluir Produto da Lista")
	public void excluirProdutoLista(Integer idProduto, Integer idCotacao) {
		
		ListaCotacao listaCotacao = new ListaCotacao();
		
		List produto = hibernateUtil.buscar(new Produto());		
		for (Object obj: produto){
			Produto p = (Produto)obj;			
			if (p.getId() == idProduto){
				listaCotacao.setProduto(p);
			}			
		}
		
		List cotacao = hibernateUtil.buscar(new Cotacao());		
		for (Object obj: cotacao) {
            Cotacao c = (Cotacao)obj;            
            if (c.getId() == idCotacao){
            	listaCotacao.setCotacao(c);
            }            
		}
		
		List produtoCotacao = HibernateUtilTest.executarConsultaHQL("from ListaCotacao");
		
		for (Object obj: produtoCotacao) {
			ListaCotacao pc = (ListaCotacao)obj;            
            if ((pc.getProduto().getId() == idProduto) && (pc.getCotacao().getId() == idCotacao) ){
            	listaCotacao.setId(pc.getId());
            }            
		}

		hibernateUtil.deletar(listaCotacao);
		result.use(Results.json()).from("ok").serialize();
	}
}
