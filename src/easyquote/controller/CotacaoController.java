package easyquote.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Cotacao;
import easyquote.modelo.ListaCotacao;
import easyquote.modelo.Produto;
import easyquote.modelo.Setor;
import easyquote.modelo.Status;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;

@Resource
public class CotacaoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private Validator validator;
	private HibernateUtil hibernateUtil;

	public CotacaoController(Result result, Validator validator, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.validator = validator;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}
	
	@Funcionalidade(filhaDe = "criarEditarCotacao")
	public void criarCotacao() {

		sessaoGeral.adicionar("idCotacao", null);			
		result.forwardTo(this).criarEditarCotacao();
		
	}

	@Path("/cotacao/editarCotacao/{cotacao.id}")
	@Funcionalidade(filhaDe = "criarEditarCotacao")
	public void editarCotacao(Cotacao cotacao) {

		cotacao = hibernateUtil.selecionar(cotacao);

		sessaoGeral.adicionar("idCotacao", cotacao.getId());
		result.include(cotacao);
		result.forwardTo(this).criarEditarCotacao();
	}

	@Funcionalidade(nome = "Criar e editar cotação")
	public void criarEditarCotacao() {
		
		List<Status> tipoStatus = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", tipoStatus);
		
		List<Setor> tipoSetor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", tipoSetor);
		
		List<Produto> tipoProduto = hibernateUtil.buscar(new Produto());
		result.include("tipoProduto", tipoProduto);
		
		List<ListaCotacao> tipoLista = hibernateUtil.buscar(new ListaCotacao());
		result.include("tipoLista", tipoLista);		
		
	}

	@Path("/cotacao/excluirCotacao/{cotacao.id}")
	@Funcionalidade(nome = "Excluir cotacao")
	public void excluirCotacao(Cotacao cotacao) {
		
		ListaCotacao listaCotacao = new ListaCotacao();
		listaCotacao.setCotacao(cotacao);
		
		if (hibernateUtil.contar(listaCotacao) > 0) {
			
			validator.add(new ValidationMessage("Não é possível excluir cotação que esteja vinculado a uma movimentação.", "Erro"));
			validator.onErrorForwardTo(this).listarCotacaos(null, null);
		
		} 
			
			hibernateUtil.deletar(cotacao);
			result.include("sucesso", "Cotação excluída com sucesso");
			result.forwardTo(this).listarCotacaos(null, null);
		
	}

	@Funcionalidade(filhaDe = "criarEditarCotacao")
	public void salvarCotacao(Cotacao cotacao) {

		if (Util.preenchido(sessaoGeral.getValor("idCotacao"))) {

			cotacao.setId((Integer) sessaoGeral.getValor("idCotacao"));
		}

		cotacao.setResponsavel(LoginController.RetornaOperador());
		
		hibernateUtil.salvarOuAtualizar(cotacao);
		result.include("sucesso", "Cotação salva com sucesso");
	//  result.redirectTo(this).listarCotacaos(new Cotacao(), null);
		
		result.redirectTo(ListaCotacaoController.class).criarListaCotacao();
		
		
	}

	@Funcionalidade(nome = "Cotação", modulo = "Novo")
	public void listarCotacaos(Cotacao cotacao, Integer pagina) {

		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao", sessaoGeral);
		if (Util.vazio(cotacao)) {
			cotacao = new Cotacao();
		}

		List<Cotacao> cotacaos = hibernateUtil.buscar(cotacao, pagina);
		result.include("cotacaos", cotacaos);
		
		List<Status> tipoStatus = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", tipoStatus);
		
		List<Setor> tipoSetor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", tipoSetor);

	}
	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(nome = "Cotação Pendentes", modulo = "Relatórios")
	public void listarCotacaoPendentes() {
		Date date = new Date();
		GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
		data.setTime(date);	
		data.add(Calendar.DAY_OF_MONTH, 7);	
		List<Cotacao> cotacao = new ArrayList<Cotacao>();
		
		List cotacaos = hibernateUtil.buscaPorHQL("from Cotacao where status.id = 1 order by dataLimiteResposta desc");
		for (Object obj: cotacaos) {
            Cotacao c = (Cotacao)obj;
            if (c.getDataLimiteResposta().getTime().before(data.getTime())) {
            	cotacao.add(c);
            }
		}
		
		List<Status> tipoStatus = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", tipoStatus);
		
		List<Setor> tipoSetor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", tipoSetor);
		
		result.include("cotacoes", cotacao);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(nome = "Cotação para Fornecedores", modulo = "Cotações em aberto")
	public void listarCotFornecedor(Cotacao cotacao, Integer pagina) {

		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao", sessaoGeral);
		if (Util.vazio(cotacao)) {
			cotacao = new Cotacao();
		}

		List cotacaos = hibernateUtil.buscaPorHQL("from Cotacao where status.id = 1");
		result.include("cotacaos", cotacaos);
		
		List<Status> tipoStatus = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", tipoStatus);
		
		List<Setor> tipoSetor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", tipoSetor);

	}
	
	public Cotacao retornaUltimaCotacao(){
		Cotacao cotacao = null;
		@SuppressWarnings("rawtypes")
		List cotacoes = hibernateUtil.retorna1HQL("from Cotacao order by id desc");
		for (Object obj: cotacoes){
			Cotacao c = (Cotacao)obj;
			cotacao = hibernateUtil.selecionar(c);
		}
		
		return cotacao;

	}
	
}
