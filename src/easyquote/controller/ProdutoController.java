package easyquote.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.ListaCotacao;
import easyquote.modelo.Produto;
import easyquote.modelo.Seguimento;
import easyquote.modelo.Setor;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;

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
 
	@SuppressWarnings("rawtypes")
	@Get
	@Path("/produto/produtosRealizados")
	public void produtosRealizados() {
 	   // Lista Cotacao
		String str ="";	
 	    Integer n, i;
 	    i = 0;	    
 	    
 	    List prod = hibernateUtil.buscaPorHQL("from ListaCotacao order by  quantidade desc");
		n = prod.size();
		
 	    String pattern1 = "dd/MM/yyyy"; 
 	    DateFormat df = new SimpleDateFormat(pattern1); 
		
 	    
		for (Object obj: prod){
			ListaCotacao p = (ListaCotacao)obj;	
			 Date date = p.getCotacao().getDataAbertura().getTime();
			 i++;
			if( n <= 1){	
				str = str + "{" +'"' +"cod" + '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()+ "," 
				+'"' +"Setor" +  '"' + ":"+  '"' +p.getCotacao().getSetor().getDescricao()+'"'+ "," 
				+'"' +"Data" +  '"' + ":"+  '"'+df.format(date)+'"'
			 +" }";
			
			}else if( n==i){
				str = str + "{" +'"' +"cod" + '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()+ "," 
				+'"' +"Setor" +  '"' + ":"+  '"' +p.getCotacao().getSetor().getDescricao()+'"'+ "," 	
				+'"' +"Data" +  '"' + ":"+  '"'+df.format(date)+'"'
			 +" }";				
				
				str = "["+str+"]";
			}
			else{
				str = str + "{" +'"' +"cod" +  '"' + ":"+ p.getProduto().getId()+" , "
				+'"'+"produto"+'"'+ ":" + '"'+p.getProduto().getDescricao().trim()+ '"' + "," 
				+'"' +"Quantidade" +  '"' + ":"+ p.getQuantidade()+"," 
				+'"' +"Setor" +  '"' + ":"+'"'+p.getCotacao().getSetor().getDescricao()+'"'+ "," 				
			    +'"' +"Data" +  '"' + ":"+  '"'+df.format(date)+'"'
				+" },";				
			}
		}
		
		result.use(Results.http()).body(str);
	}	
	
	@SuppressWarnings("rawtypes")
	@Get
	@Path("/produto/produtosRealizados1")
	public void produtosRealizados1(String dataInicio, String dataFim) throws ParseException {
 	    
 	    String pattern = "yyyy-MM-dd";   	  
 	   	DateFormat df = new SimpleDateFormat(pattern);  
 	   	Date date = df.parse(dataInicio);  
 	   	
		List prod = hibernateUtil.buscaPorHQL("from ListaCotacao");
		prod.size();
		
		System.out.println(date.getTime());
			
	}	
	
	@SuppressWarnings("rawtypes")
	@Get
	@Path("/produto/produtosSolicitadosData")
	public void produtoSolicitadoData() throws ParseException {
		
		String str = ""; 
		int i = 0;
 	    String pattern = "yyyy-MM-dd";
 	   	DateFormat df = new SimpleDateFormat(pattern);  	  
		   
 	   	List s = hibernateUtil.buscaPorHQL("from ListaCotacao group by cotacao.dataAbertura");
 	    int n = s.size();  
 	   	for (Object obj: s){
 	   		 i++;
 	   		
			   ListaCotacao p = (ListaCotacao)obj;
			   
			   Date date = p.getCotacao().getDataAbertura().getTime(); 
			      
			   long cont = hibernateUtil.executarCountHQL("SELECT count(p) FROM "+ ListaCotacao.class.getName() + " as p where cotacao.dataAbertura LIKE '"+df.format(date)+"%'");
			   //str = str +"data:" +df.format(date)+ "cont:"+cont;
			   
			   if( n <= 1){
			    str =  str + "{ "+'"' +"d"+'"'+":"+'"'+df.format(date)+'"'+","+'"'+ "cont"+'"'+":"+cont+"}";
			   
 	           }else if( n==i){			   
 	        	  str = str +  "{ "+'"' +"d"+'"'+":"+'"'+df.format(date)+'"'+","+'"'+ "cont"+'"'+":"+cont+"}";
 	        	  str = "["+str+"]";
 	           }else{
 	        	  str = str + "{ "+'"' +"d"+'"'+":"+'"'+df.format(date)+'"'+","+'"'+ "cont"+'"'+":"+cont+"},";
 	           }

 	   	}
		   result.use(Results.http()).body(str);
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@Get
	@Path("/produto/produtosSegmento")
	public void produtoSegmento() {
 	    String strSetor="";	
 	    Integer nSetor, j, tl;
 	    j = 0;
		
 	   List total = hibernateUtil.buscaPorHQL("from ListaCotacao");
 	   tl = total.size();
 			   
	   List seg = hibernateUtil.buscaPorHQL("from ListaCotacao group by cotacao.setor.id");
	   nSetor = seg.size();
		

		for (Object obj: seg){
			ListaCotacao s = (ListaCotacao)obj;	
			j++;  
			long cont = hibernateUtil.executarCountHQL("SELECT count(p) FROM " + ListaCotacao.class.getName() + " as p where cotacao.setor.id="+s.getCotacao().getSetor().getId());
			
			if( nSetor <= 1){
		     strSetor = strSetor + "{" +'"' +"label" + '"' + ":"+ '"' + s.getCotacao().getSetor().getDescricao()+ '"' + " , "
			+'"'+"value"+'"'+ ":" + ((cont*100)/tl) 
		    +" }";		
			
		   }else if( nSetor ==j){
			   strSetor = strSetor + "{" +'"' +"label" + '"' + ":"+ '"' +s.getCotacao().getSetor().getDescricao()+ '"' + " , "
						+'"'+"value"+'"'+ ":" + ((cont*100)/tl) 
					    +" }";	
			   strSetor = "["+strSetor+"]";
		   }else{
			   strSetor = strSetor + "{" +'"' +"label" + '"' + ":"+ '"' +s.getCotacao().getSetor().getDescricao()+ '"' + " , "
						+'"'+"value"+'"'+ ":" + ((cont*100)/tl) 
					    +" },";	 
		       }
		   }
		
		result.use(Results.http()).body(strSetor);

		
		//result.use(Results.json()).from(str).serialize();
	}		
}
