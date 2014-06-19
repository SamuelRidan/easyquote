package easyquote.controller;

import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Cotacao;
import easyquote.modelo.Fornecedor;
import easyquote.modelo.ListaCotacao;
import easyquote.modelo.ListaCotacaoFornecedor;
import easyquote.modelo.Operador;
import easyquote.modelo.Pagamento;
import easyquote.modelo.PesquisaPedido;
import easyquote.modelo.Produto;
import easyquote.modelo.Setor;
import easyquote.modelo.Status;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;

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
	public void criarListaCotacao() {
		ListaCotacao lc = new ListaCotacao();
		CotacaoController cc = new CotacaoController(result, null, sessaoGeral, hibernateUtil);

		sessaoGeral.adicionar("idListaCotacao", null);	
		
		lc.setCotacao(cc.retornaUltimaCotacao());
		result.include("cotacao", lc);
		
		List<Produto> produtos = hibernateUtil.buscar(new Produto());
		result.include("produtos", produtos);
		
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
		//result.forwardTo(this).listarListaCotacaos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void salvarListaCotacao(ListaCotacao listaCotacao) {

		if (Util.preenchido(sessaoGeral.getValor("idListaCotacao"))) {

			listaCotacao.setId((Integer) sessaoGeral.getValor("idListaCotacao"));
		}

		hibernateUtil.salvarOuAtualizar(listaCotacao);
		result.include("sucesso", "Cotação salva com sucesso");
		//result.redirectTo(this).listarListaCotacaos(null, null);
		
	}

	@SuppressWarnings("rawtypes")
	@Path("/listaCotacao/listarListaCotacaos/{cotacao.id}")
	public void listarListaCotacaos(Cotacao cotacao, Integer pagina) {
		
		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao.id", sessaoGeral);
		List lista = hibernateUtil.buscaPorHQL("from ListaCotacao where cotacao.id = "+ cotacao.getId());
		result.include("listaCotacaos", lista);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Path("/listaCotacao/listarListaFornecedor/{cotacao.id}")
	@Funcionalidade(nome = "Lista de Produtos da Cotação para Fornecedores")
	public void listarListaCotFornecedor(Cotacao cotacao, Integer pagina) {
		
		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao.id", sessaoGeral);
		List lista = hibernateUtil.buscaPorHQL("from ListaCotacao where cotacao.id = "+ cotacao.getId());
		result.include("listaCotacaos", lista);
		
		Operador operador = new Operador();
		operador = LoginController.RetornaOperador();
		List lcf = null;
		List fornecedor = hibernateUtil.buscaPorHQL("from Fornecedor where operador.id = "+ operador.getId());
		for (Object obj: fornecedor){
			Fornecedor f = (Fornecedor)obj;
				lcf = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where cotacao.id = "+cotacao.getId()+"and fornecedor.id = "+f.getId());
		}
		result.include("tipoLCF", lcf);
		
		List<Cotacao> cot = hibernateUtil.buscar(cotacao);
		result.include("tipoCotacao", cot);
		
		List<Pagamento> pagamento = hibernateUtil.buscar(new Pagamento());
		result.include("tipoPagamento", pagamento);
		
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
	}
	
	@Get	
	@Path("/listaCotacao/propostaFornecedor1")		
	public void propostaFornecedor1(Integer cotacao) {			
			
			String str ="";
			Integer nl,i,idcot; 
			i=0;
			idcot = cotacao;
	        	
			@SuppressWarnings("rawtypes")
			List propFornecedor = this.hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where cotacao_id=" +idcot+ "group by fornecedor_id");
			nl = propFornecedor.size();
			
			for (Object obj: propFornecedor){
				ListaCotacaoFornecedor p = (ListaCotacaoFornecedor)obj;				
					i++;
				    if(nl == i){	
				    	str = str + "{ " +'"'+ "fornecedor"+'"'+ ":" + '"' +"Cod: "+ p.getFornecedor().getId() + '"' + "," +  '"' +"reputacao" +  '"' + ":"+ ( Integer.parseInt(p.getFornecedor().getReputacao())* 10) +" }";
				    }else{
				    	str = str + "{ " +'"'+ "fornecedor"+'"'+ ":" + '"' +"Cod: "+ p.getFornecedor().getId()+ '"' + "," +  '"' +"reputacao" +  '"' + ":"+  ( Integer.parseInt(p.getFornecedor().getReputacao())* 10)+" },";
				    }
			}
			
	        result.use(Results.http()).body(str);      
		}
	
	@Get
	@Path("/listaCotacao/propostaFornecedor")		
	@Funcionalidade(nome = "Melhor Cotação")
	public void propostaFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {
		
        List<Cotacao> cot = hibernateUtil.buscar(new Cotacao());
		result.include("tipoCotacao", cot);
			
		List<Produto> produto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", produto);
		
		List<Pagamento> pagamento= hibernateUtil.buscar(new Pagamento());
		result.include("tipoPagamento", pagamento);		
		
		List<Status> status = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", status);
		
		List<Setor> setor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", setor);		
		
		List<PesquisaPedido> pespedido = hibernateUtil.buscar(new PesquisaPedido());
		result.include("tipopedido", pespedido);	
		
}

	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void salvarProdutoLista(Integer prod, Integer quantidade, Integer idCot) {

		ListaCotacao listaCotacao = new ListaCotacao();
		List produto = hibernateUtil.buscaPorHQL("from Produto where id = "+ prod);		
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

		hibernateUtil.executarSQL("delete ListaCotacao where cotacao.id = "+idCot+" and produto.id "+prod);
		result.use(Results.json()).from("ok").serialize();
	
	}
	
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void excluirProduto(Integer id) {       

		hibernateUtil.executarSQL("delete ListaCotacao where id = "+id);
		result.use(Results.json()).from("ok").serialize();
	
	}
	
	@Funcionalidade(nome = "Produtos da Cotação", modulo = "Relatórios")	
	public void relatorioCotacao(ListaCotacao listaCotacao, Integer pagina) {

			listaCotacao = (ListaCotacao) UtilController.preencherFiltros(listaCotacao, "listaCotacao", sessaoGeral);
			if (Util.vazio(listaCotacao)) {
				listaCotacao = new ListaCotacao();
			}

			List<ListaCotacao> listaCotacaos = hibernateUtil.buscar(listaCotacao, pagina);
			result.include("listaCotacaos", listaCotacaos);
			
			List<Produto> produto = hibernateUtil.buscar(new Produto());
			result.include("tipoProduto", produto);
			
			List<Pagamento> pagamento= hibernateUtil.buscar(new Pagamento());
			result.include("tipoPagamento", pagamento);		
			
			List<Status> status = hibernateUtil.buscar(new Status());
			result.include("tipoStatus", status);
			
			List<Setor> setor = hibernateUtil.buscar(new Setor());
			result.include("tipoSetor", setor);

			List<Cotacao> cotacoes = hibernateUtil.buscar(new Cotacao());
			
			calcularQuantidadePropostas(cotacoes);			
			
			result.include("tipoCotacao", cotacoes);
		}

	private void calcularQuantidadePropostas(List<Cotacao> cotacoes) {
		for(Cotacao cotacao: cotacoes){
			cotacao.setQuantidadePropostas(hibernateUtil.buscaPorHQL("select count(fornecedor_id) from ListaCotacaoFornecedor where cotacao_id=" + cotacao.getId() + " group by fornecedor_id").size());				
		}
	}	
		
	@SuppressWarnings("rawtypes")
	@Get		
	@Path("/listaCotacao/contaReputacaoFornecedor")
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void contaReputacaoFornecedor( Integer idCot) {

		List propFornecedor = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor group by fornecedor_id");	
		//for (Object obj: propFornecedor) {
	    //   ListaCotacaoFornecedor c = (ListaCotacaoFornecedor)obj;            
	    //   System.out.println(c.getId());
		//}		
		result.use(Results.json()).from(propFornecedor).serialize();		
	}		
	
	@SuppressWarnings("rawtypes")
	@Path("/listaCotacao/salvarListaFornecedor")
	@Funcionalidade(filhaDe = "listarListaCotFornecedor")
	public void salvarListaFornecedor(Integer cotacao, Integer op, Integer formaPgto, Integer prod, Double preco) {
		
		System.out.println("Operador: " + op);
		
		ListaCotacaoFornecedor listaCotacaoFornecedor = new ListaCotacaoFornecedor();

		if (Util.preenchido(sessaoGeral.getValor("idListaCotacaoFornecedor"))) {

			listaCotacaoFornecedor.setId((Integer) sessaoGeral.getValor("idListaCotacaoFornecedor"));
		}
		
		List cot = hibernateUtil.buscar(new Cotacao());		
		for (Object obj: cot) {
            Cotacao c = (Cotacao)obj;            
            if (c.getId() == cotacao){
            	listaCotacaoFornecedor.setCotacao(c);
            }            
		}
		
		List forn = hibernateUtil.buscaPorHQL("from Fornecedor where operador.id = "+ op);
		for (Object obj: forn){
			Fornecedor f = (Fornecedor)obj;
			listaCotacaoFornecedor.setFornecedor(f);
		}
		
		List pagamento = hibernateUtil.buscar(new Pagamento());
		for (Object obj: pagamento){
			Pagamento pgto = (Pagamento)obj;
			if (pgto.getId()==formaPgto){
				listaCotacaoFornecedor.setFormPgto(pgto);
			}
		}
		
		List produto = hibernateUtil.buscar(new ListaCotacao());		
		for (Object obj: produto){
			ListaCotacao p = (ListaCotacao)obj;			
			if (p.getId() == prod){
				listaCotacaoFornecedor.setListaCotacao(p);
			}			
		}
		
		listaCotacaoFornecedor.setPreco(preco);

		hibernateUtil.salvarOuAtualizar(listaCotacaoFornecedor);
		result.include("sucesso", "Informações salvas com sucesso");
		result.use(Results.json()).from("ok").serialize();
	}

}
