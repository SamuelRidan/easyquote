package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.Comprador;
import scada.modelo.Operador;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class CompradorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public CompradorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarComprador")
	public void criarComprador(Operador operador) {
		sessaoGeral.adicionar("idComprador", null);
		result.forwardTo(this).criarEditarComprador();
	}

	@Path("/comprador/editarComprador/{comprador.id}")
	@Funcionalidade(filhaDe = "criarEditarComprador")
	public void editarComprador(Comprador comprador) {

		comprador = hibernateUtil.selecionar(comprador);

		sessaoGeral.adicionar("idComprador", comprador.getId());
		result.include(comprador);
		result.forwardTo(this).criarEditarComprador();
	}

	@Funcionalidade(nome = "Criar e editar comprador")
	public void criarEditarComprador() {
	}

	@Path("/comprador/excluirComprador/{comprador.id}")
	@Funcionalidade(nome = "Excluir comprador")
	public void excluirComprador(Comprador comprador) {

		hibernateUtil.deletar(comprador);
		result.include("sucesso", "Comprador excluído(a) com sucesso");
		result.forwardTo(this).listarCompradors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarComprador")
	public void salvarComprador(Comprador comprador) {

		if (Util.preenchido(sessaoGeral.getValor("idComprador"))) {

			comprador.setId((Integer) sessaoGeral.getValor("idComprador"));
		}

		hibernateUtil.salvarOuAtualizar(comprador);
		result.include("sucesso", "Comprador salvo(a) com sucesso");
		
		OperadorController op = new OperadorController(result, sessaoGeral,hibernateUtil);
		result.redirectTo(op).criarOperador();
	}

	@Funcionalidade(nome = "Compradores", modulo = "Informações")
	public void listarCompradors(Comprador comprador, Integer pagina) {

		comprador = (Comprador) UtilController.preencherFiltros(comprador, "comprador", sessaoGeral);
		if (Util.vazio(comprador)) {
			comprador = new Comprador();
		}

		List<Comprador> compradors = hibernateUtil.buscar(comprador, pagina);
		result.include("compradors", compradors);

	}
}
