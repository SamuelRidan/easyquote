package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Cotacao;
import scada.modelo.ListaCotacao;
import scada.modelo.ListaCotacaoFornecedor;
import scada.modelo.Pagamento;
import scada.modelo.PesquisaPedido;
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

	@Path("/listaCotacao/listarListaCotacaos/{cotacao.id}")
	public void listarListaCotacaos(Cotacao cotacao, Integer pagina) {
		
		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao.id", sessaoGeral);
		List lista = HibernateUtilTest.executarConsultaHQL("from ListaCotacao where cotacao.id = :idCotacao", "idCotacao", cotacao.getId());
		result.include("listaCotacaos", lista);
		
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
	        		
			List propFornecedor = HibernateUtilTest.executarConsultaHQL("from ListaCotacaoFornecedor where cotacao_id=" +idcot+ "group by fornecedor_id");
			nl = propFornecedor.size();
			
			if( nl <= 1){
				for (Object obj: propFornecedor){
					ListaCotacaoFornecedor p = (ListaCotacaoFornecedor)obj;			
					
					str = str + "{ " +'"'+ "fornecedor"+'"'+ ":" + '"' +"Cod: "+p.getFornecedor().getRazao_soacial() + '"' + "," +  '"' +"reputacao" +  '"' + ":"+  ( Integer.parseInt(p.getFornecedor().getReputacao())* 10)+" }";
				}				
			}else{
				for (Object obj: propFornecedor){
					ListaCotacaoFornecedor p = (ListaCotacaoFornecedor)obj;				
						i++;
					    if(nl == i){	
					    	str = str + "{ " +'"'+ "fornecedor"+'"'+ ":" + '"' +"Cod: "+ p.getFornecedor().getId() + '"' + "," +  '"' +"reputacao" +  '"' + ":"+ ( Integer.parseInt(p.getFornecedor().getReputacao())* 10) +" }";
					    }else{
					    	str = str + "{ " +'"'+ "fornecedor"+'"'+ ":" + '"' +"Cod: "+ p.getFornecedor().getId()+ '"' + "," +  '"' +"reputacao" +  '"' + ":"+  ( Integer.parseInt(p.getFornecedor().getReputacao())* 10)+" },";
					    }
				}
			}
	        result.use(Results.http()).body(str);
	        

		}
	
	@Get
	@Path("/listaCotacao/propostaFornecedor")		
	public void propostaFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {
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
			
			List<PesquisaPedido> pespedido = hibernateUtil.buscar(new PesquisaPedido());
			result.include("tipopedido", pespedido);		
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

		HibernateUtilTest.executarHQL("delete ListaCotacao where cotacao.id = :idCotacao and produto.id = :idProduto","idCotacao",idCot,"idProduto",prod);
		result.use(Results.json()).from("ok").serialize();
	
	}
	
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void excluirProduto(Integer id) {       

		HibernateUtilTest.executarHQL("delete ListaCotacao where id = :idListaCotacao","idListaCotacao",id);
		result.use(Results.json()).from("ok").serialize();
	
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
		
	@Get		
	@Path("/listaCotacao/contaReputacaoFornecedor")
	@Funcionalidade(filhaDe = "criarEditarListaCotacao")
	public void contaReputacaoFornecedor( Integer idCot) {

		List propFornecedor = HibernateUtilTest.executarConsultaHQL("from ListaCotacaoFornecedor group by fornecedor_id");	
		//for (Object obj: propFornecedor) {
	    //   ListaCotacaoFornecedor c = (ListaCotacaoFornecedor)obj;            
	    //   System.out.println(c.getId());
		//}		
		result.use(Results.json()).from(propFornecedor).serialize();		
	}		
	
}
