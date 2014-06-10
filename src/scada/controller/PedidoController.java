package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Pedido;
import scada.modelo.StatusPedido;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PedidoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public PedidoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarPedido")
	public void criarPedido() {

		sessaoGeral.adicionar("idPedido", null);
		result.forwardTo(this).criarEditarPedido();
	}

	@Path("/pedido/editarPedido/{pedido.id}")
	@Funcionalidade(filhaDe = "criarEditarPedido")
	public void editarPedido(Pedido pedido) {

		pedido = hibernateUtil.selecionar(pedido);

		sessaoGeral.adicionar("idPedido", pedido.getId());
		result.include(pedido);
		result.forwardTo(this).criarEditarPedido();
	}

	@Funcionalidade(nome = "Criar e editar pedidos")
	public void criarEditarPedido() {
		
		List<StatusPedido> status = hibernateUtil.buscar(new StatusPedido());
		result.include("status", status);
		
	}

	@Path("/pedido/excluirPedido/{pedido.id}")
	@Funcionalidade(nome = "Excluir pedido")
	public void excluirPedido(Pedido pedido) {

		hibernateUtil.deletar(pedido);
		result.include("sucesso", "Pedido excluído(a) com sucesso");
		result.forwardTo(this).listarPedidos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarPedido")
	public void salvarPedido(Pedido pedido) {

		if (Util.preenchido(sessaoGeral.getValor("idPedido"))) {

			pedido.setId((Integer) sessaoGeral.getValor("idPedido"));
		}

		hibernateUtil.salvarOuAtualizar(pedido);
		result.include("sucesso", "Pedido salvo(a) com sucesso");
		result.redirectTo(this).listarPedidos(new Pedido(), null);
	}

	@Funcionalidade(nome = "Pedido", modulo = "Informações")
	public void listarPedidos(Pedido pedido, Integer pagina) {

		pedido = (Pedido) UtilController.preencherFiltros(pedido, "pedido", sessaoGeral);
		if (Util.vazio(pedido)) {
			pedido = new Pedido();
		}

		List<Pedido> pedidos = hibernateUtil.buscar(pedido, pagina);
		result.include("pedidos", pedidos);
		
		List<StatusPedido> status = hibernateUtil.buscar(new StatusPedido());
		result.include("tipoStatus", status);

	}
}
