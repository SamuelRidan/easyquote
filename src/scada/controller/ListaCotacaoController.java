package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Cotacao;
import scada.modelo.Fornecedor;
import scada.modelo.ListaCotacao;
import scada.modelo.ListaCotacaoFornecedor;
import scada.modelo.Pagamento;
import scada.modelo.Produto;
import scada.modelo.Setor;
import scada.modelo.Status;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;
import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Get;
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
	public void listarListaCotacaos(ListaCotacao listaCotacao1, Integer pagina, Integer i) {

		listaCotacao1 = (ListaCotacao) UtilController.preencherFiltros(listaCotacao1, "listaCotacao.Cotacao.id", sessaoGeral);
		
		if (Util.vazio(listaCotacao1)) {
			listaCotacao1 = new ListaCotacao();
		}

		List<ListaCotacao> listaCotacaos = hibernateUtil.buscar(listaCotacao1, pagina);
		result.include("listaCotacaos", listaCotacaos);
		
		List<Cotacao> cotacao = hibernateUtil.buscar(new Cotacao());
		result.include("tipoCotacao", cotacao);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
		List<Pagamento> pagamento= hibernateUtil.buscar(new Pagamento());
		result.include("tipoPagamento", pagamento);		
		
		List<Status> status = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", status);
		
		List<Setor> setor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", setor);
		
	
	}	
	
	@Get
	@Path("/listaCotacao/propostaFornecedor1/{ListaCotacaoFornecedor.id}")
	public void propostaFornecedor1(ListaCotacaoFornecedor listaCotacaoFornecedor) {

		
		List propFornecedor = HibernateUtilTest.executarConsultaHQL("from ListaCotacaoFornecedor");	
        result.use(Results.json()).from(propFornecedor).serialize();
		
        List<Cotacao> cotacao = hibernateUtil.buscar(new Cotacao());
		result.include("tipoCotacao", cotacao);
	
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
		List<Fornecedor> fornecedor = hibernateUtil.buscar(new Fornecedor());
		result.include("tipoFornecedor", fornecedor);
		
	}
	
	public void propostaFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {
	
	}

	
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void salvarProdutoLista(Integer prod, Integer quantidade, Integer idCot) {

		ListaCotacao listaCotacao = new ListaCotacao();
		
		List produto = hibernateUtil.buscar(new Produto());		
		for (Object obj: produto){
			Produto p = (Produto)obj;			
			if (p.getId() == prod){
				listaCotacao.setProduto(p);
			}			
		}
		
		listaCotacao.setQuantidade(quantidade);
		
		List cotacao = hibernateUtil.buscar(new Cotacao());		
		for (Object obj: cotacao) {
            Cotacao c = (Cotacao)obj;            
            if (c.getId() == idCot){
            	listaCotacao.setCotacao(c);
            }            
		}
		
		hibernateUtil.salvarOuAtualizar(listaCotacao);
		result.use(Results.json()).from("ok").serialize();
		
	}
	
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void excluirProdutoLista(Integer prod, Integer idCot) {
		
		System.out.println("idProduto: " + prod);
		System.out.println("idCotação: " + idCot);
		
		/*
		ListaCotacao listaCotacao = new ListaCotacao();
		
		List produtoCotacao = HibernateUtilTest.executarConsultaHQL("from ListaCotacao");
		
		for (Object obj: produtoCotacao) {
			ListaCotacao pc = (ListaCotacao)obj;            
            if ((pc.getProduto().getId() == prod) && (pc.getCotacao().getId() == idCot) ){
            	listaCotacao.setId(pc.getId());
            }            
		}

		hibernateUtil.deletar(listaCotacao);
		result.use(Results.json()).from("ok").serialize();
		*/
	}
	
	@Funcionalidade(nome = "Cotação", modulo = "Relatórios")
	public void relatorioCotacao(ListaCotacao listaCotacao, Integer pagina) {


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
		
		List<Pagamento> pagamento= hibernateUtil.buscar(new Pagamento());
		result.include("tipoPagamento", pagamento);		
		
		List<Status> status = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", status);
		
		List<Setor> setor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", setor);


	


	}	

}
