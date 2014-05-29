package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Cotacao;
import scada.modelo.ListaCotacao;
import scada.modelo.Produto;
import scada.modelo.Setor;
import scada.modelo.Status;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;
import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

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
		
		ListaCotacaoController lc = new ListaCotacaoController(result, sessaoGeral, hibernateUtil);
		result.redirectTo(lc).criarListaCotacao(cotacao);
		
		
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
	
	@Funcionalidade(nome = "Cotação para Fornecedores", modulo = "Cotações em aberto")
	public void listarCotFornecedor(Cotacao cotacao, Integer pagina) {

		cotacao = (Cotacao) UtilController.preencherFiltros(cotacao, "cotacao", sessaoGeral);
		if (Util.vazio(cotacao)) {
			cotacao = new Cotacao();
		}

		List cotacaos = HibernateUtilTest.executarConsultaHQL("from Cotacao where status.id = :idStatus", "idStatus", 1);
		result.include("cotacaos", cotacaos);
		
		List<Status> tipoStatus = hibernateUtil.buscar(new Status());
		result.include("tipoStatus", tipoStatus);
		
		List<Setor> tipoSetor = hibernateUtil.buscar(new Setor());
		result.include("tipoSetor", tipoSetor);

	}
}
