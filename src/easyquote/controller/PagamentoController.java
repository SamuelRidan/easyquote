package easyquote.controller;

import java.util.List;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Pagamento;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;



import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PagamentoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public PagamentoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarPagamento")
	public void criarPagamento() {

		sessaoGeral.adicionar("idPagamento", null);
		result.forwardTo(this).criarEditarPagamento();
	}

	@Path("/pagamento/editarPagamento/{pagamento.id}")
	@Funcionalidade(filhaDe = "criarEditarPagamento")
	public void editarPagamento(Pagamento pagamento) {

		pagamento = hibernateUtil.selecionar(pagamento);

		sessaoGeral.adicionar("idPagamento", pagamento.getId());
		result.include(pagamento);
		result.forwardTo(this).criarEditarPagamento();
	}

	@Funcionalidade(nome = "Criar e editar pagamentos")
	public void criarEditarPagamento() {
	}

	@Path("/pagamento/excluirPagamento/{pagamento.id}")
	@Funcionalidade(nome = "Excluir pagamento")
	public void excluirPagamento(Pagamento pagamento) {

		hibernateUtil.deletar(pagamento);
		result.include("sucesso", "Pagamento excluído(a) com sucesso");
		result.forwardTo(this).listarPagamentos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarPagamento")
	public void salvarPagamento(Pagamento pagamento) {

		if (Util.preenchido(sessaoGeral.getValor("idPagamento"))) {

			pagamento.setId((Integer) sessaoGeral.getValor("idPagamento"));
		}

		hibernateUtil.salvarOuAtualizar(pagamento);
		result.include("sucesso", "Pagamento salvo(a) com sucesso");
		result.redirectTo(this).listarPagamentos(new Pagamento(), null);
	}

	@Funcionalidade(nome = "Formas de pagamento", modulo = "Informações")
	public void listarPagamentos(Pagamento pagamento, Integer pagina) {

		pagamento = (Pagamento) UtilController.preencherFiltros(pagamento, "pagamento", sessaoGeral);
		if (Util.vazio(pagamento)) {
			pagamento = new Pagamento();
		}

		List<Pagamento> pagamentos = hibernateUtil.buscar(pagamento, pagina);
		result.include("pagamentos", pagamentos);

	}
}
