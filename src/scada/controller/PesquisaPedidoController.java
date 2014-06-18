package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.PesquisaPedido;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PesquisaPedidoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public PesquisaPedidoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarPesquisaPedido")
	public void criarPesquisaPedido() {

		sessaoGeral.adicionar("idPesquisaPedido", null);
		result.forwardTo(this).criarEditarPesquisaPedido();
	}

	@Path("/pesquisaPedido/editarPesquisaPedido/{pesquisaPedido.id}")
	@Funcionalidade(filhaDe = "criarEditarPesquisaPedido")
	public void editarPesquisaPedido(PesquisaPedido pesquisaPedido) {

		pesquisaPedido = hibernateUtil.selecionar(pesquisaPedido);

		sessaoGeral.adicionar("idPesquisaPedido", pesquisaPedido.getId());
		result.include(pesquisaPedido);
		result.forwardTo(this).criarEditarPesquisaPedido();
	}

	@Funcionalidade(nome = "Criar e editar pesquisaPedidos")
	public void criarEditarPesquisaPedido() {
	}

	@Path("/pesquisaPedido/excluirPesquisaPedido/{pesquisaPedido.id}")
	@Funcionalidade(nome = "Excluir pesquisaPedido")
	public void excluirPesquisaPedido(PesquisaPedido pesquisaPedido) {

		hibernateUtil.deletar(pesquisaPedido);
		result.include("sucesso", "PesquisaPedido excluído(a) com sucesso");
		result.forwardTo(this).listarPesquisaPedidos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarPesquisaPedido")
	public void salvarPesquisaPedido(PesquisaPedido pesquisaPedido) {

		if (Util.preenchido(sessaoGeral.getValor("idPesquisaPedido"))) {

			pesquisaPedido.setId((Integer) sessaoGeral.getValor("idPesquisaPedido"));
		}

		hibernateUtil.salvarOuAtualizar(pesquisaPedido);
		result.include("sucesso", "PesquisaPedido salvo(a) com sucesso");
		result.redirectTo(this).listarPesquisaPedidos(new PesquisaPedido(), null);
	}

//	@Funcionalidade(nome = "PesquisaPedidos", modulo = "New")
	public void listarPesquisaPedidos(PesquisaPedido pesquisaPedido, Integer pagina) {

		pesquisaPedido = (PesquisaPedido) UtilController.preencherFiltros(pesquisaPedido, "pesquisaPedido", sessaoGeral);
		if (Util.vazio(pesquisaPedido)) {
			pesquisaPedido = new PesquisaPedido();
		}

		List<PesquisaPedido> pesquisaPedidos = hibernateUtil.buscar(pesquisaPedido, pagina);
		result.include("pesquisaPedidos", pesquisaPedidos);

	}
}
