package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.ListaCotacao;
import scada.modelo.Produto;
import scada.modelo.Seguimento;
import scada.modelo.Setor;
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
public class ProdutoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public ProdutoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void criarProduto() {

		sessaoGeral.adicionar("idProduto", null);
		result.forwardTo(this).criarEditarProduto();
	}

	@Path("/produto/editarProduto/{produto.id}")
	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void editarProduto(Produto produto) {

		produto = hibernateUtil.selecionar(produto);

		sessaoGeral.adicionar("idProduto", produto.getId());
		result.include(produto);
		result.forwardTo(this).criarEditarProduto();
	}

	@Funcionalidade(nome = "Criar e editar produtos")
	public void criarEditarProduto() {
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);
	}

	@Path("/produto/excluirProduto/{produto.id}")
	@Funcionalidade(nome = "Excluir produto")
	public void excluirProduto(Produto produto) {

		hibernateUtil.deletar(produto);
		result.include("sucesso", "Produto excluído(a) com sucesso");
		result.forwardTo(this).listarProdutos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void salvarProduto(Produto produto) {

		if (Util.preenchido(sessaoGeral.getValor("idProduto"))) {

			produto.setId((Integer) sessaoGeral.getValor("idProduto"));
		}

		hibernateUtil.salvarOuAtualizar(produto);
		result.include("sucesso", "Produto salvo(a) com sucesso");
		result.redirectTo(this).listarProdutos(new Produto(), null);
	}

	@Funcionalidade(nome = "Produtos", modulo = "Informações")
	public void listarProdutos(Produto produto, Integer pagina) {

		produto = (Produto) UtilController.preencherFiltros(produto, "produto", sessaoGeral);
		if (Util.vazio(produto)) {
			produto = new Produto();
		}

		List<Produto> produtos = hibernateUtil.buscar(produto, pagina);
		result.include("produtos", produtos);
		
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);

	}
	
	@Funcionalidade(nome = "Produtos Solicitados", modulo = "Relatórios")
	public void produtoSolicitados(Produto produto, Integer pagina) {

		produto = (Produto) UtilController.preencherFiltros(produto, "produto", sessaoGeral);
		if (Util.vazio(produto)) {
			produto = new Produto();
		}

		List<Produto> produtos = hibernateUtil.buscar(produto, pagina);
		result.include("produtos", produtos);
		
		List<Setor> setor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", setor);		
	}
	
	@Get
	@Path("/produto/produtosRealizados")
	public void produtosRealizados() {
 	    String str ="";	
 	    Integer n, i;
 	    i = 0;
		List prod = HibernateUtilTest.executarConsultaHQL("from ListaCotacao");
		n = prod.size();
		
		
		for (Object obj: prod){
			ListaCotacao p = (ListaCotacao)obj;	
			 i++;
			 System.out.println(i +" "+ n);
			if( n <= 1){	
				str = str + "{" +'"' +"cod" + '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()
			 +" }";
			
			}else if( n==i){
				str = str + "{" +'"' +"cod" + '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()
			 +" }";				
				
				str = "["+str+"]";
			}
			else{
				str = str + "{" +'"' +"cod" +  '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()
			 +" },";				
			}
		}
		System.out.println(str);
		result.use(Results.http()).body(str);
	}	
	
}
