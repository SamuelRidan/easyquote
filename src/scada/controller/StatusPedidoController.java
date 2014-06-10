package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.StatusPedido;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class StatusPedidoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public StatusPedidoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarStatusPedido")
	public void criarStatusPedido() {

		sessaoGeral.adicionar("idStatusPedido", null);
		result.forwardTo(this).criarEditarStatusPedido();
	}

	@Path("/statusPedido/editarStatusPedido/{statusPedido.id}")
	@Funcionalidade(filhaDe = "criarEditarStatusPedido")
	public void editarStatusPedido(StatusPedido statusPedido) {

		statusPedido = hibernateUtil.selecionar(statusPedido);

		sessaoGeral.adicionar("idStatusPedido", statusPedido.getId());
		result.include(statusPedido);
		result.forwardTo(this).criarEditarStatusPedido();
	}

	@Funcionalidade(nome = "Criar e editar statusPedidos")
	public void criarEditarStatusPedido() {
	}

	@Path("/statusPedido/excluirStatusPedido/{statusPedido.id}")
	@Funcionalidade(nome = "Excluir statusPedido")
	public void excluirStatusPedido(StatusPedido statusPedido) {

		hibernateUtil.deletar(statusPedido);
		result.include("sucesso", "StatusPedido excluído(a) com sucesso");
		result.forwardTo(this).listarStatusPedidos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarStatusPedido")
	public void salvarStatusPedido(StatusPedido statusPedido) {

		if (Util.preenchido(sessaoGeral.getValor("idStatusPedido"))) {

			statusPedido.setId((Integer) sessaoGeral.getValor("idStatusPedido"));
		}

		hibernateUtil.salvarOuAtualizar(statusPedido);
		result.include("sucesso", "StatusPedido salvo(a) com sucesso");
		result.redirectTo(this).listarStatusPedidos(new StatusPedido(), null);
	}

	@Funcionalidade(nome = "StatusPedidos", modulo = "Informações")
	public void listarStatusPedidos(StatusPedido statusPedido, Integer pagina) {

		statusPedido = (StatusPedido) UtilController.preencherFiltros(statusPedido, "statusPedido", sessaoGeral);
		if (Util.vazio(statusPedido)) {
			statusPedido = new StatusPedido();
		}

		List<StatusPedido> statusPedidos = hibernateUtil.buscar(statusPedido, pagina);
		result.include("statusPedidos", statusPedidos);

	}
}
