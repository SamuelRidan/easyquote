package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Operador;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class OperadorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public OperadorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void criarOperador() {

		sessaoGeral.adicionar("idOperador", null);
		result.forwardTo(this).criarEditarOperador();
	}

	@Path("/operador/editarOperador/{operador.id}")
	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void editarOperador(Operador operador) {

		operador = hibernateUtil.selecionar(operador);

		sessaoGeral.adicionar("idOperador", operador.getId());
		result.include(operador);
		result.forwardTo(this).criarEditarOperador();
	}

	@Funcionalidade(nome = "Criar e editar operadors")
	public void criarEditarOperador() {
	}

	@Path("/operador/excluirOperador/{operador.id}")
	@Funcionalidade(nome = "Excluir operador")
	public void excluirOperador(Operador operador) {

		hibernateUtil.deletar(operador);
		result.include("sucesso", "Operador excluído(a) com sucesso");
		result.forwardTo(this).listarOperadors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void salvarOperador(Operador operador) {

		if (Util.preenchido(sessaoGeral.getValor("idOperador"))) {

			operador.setId((Integer) sessaoGeral.getValor("idOperador"));
		}

		hibernateUtil.salvarOuAtualizar(operador);
		result.include("sucesso", "Operador salvo(a) com sucesso");
		result.redirectTo(this).listarOperadors(new Operador(), null);
	}

	@Funcionalidade(nome = "Operadores", modulo = "Acesso")
	public void listarOperadors(Operador operador, Integer pagina) {

		operador = (Operador) UtilController.preencherFiltros(operador, "operador", sessaoGeral);
		if (Util.vazio(operador)) {
			operador = new Operador();
		}

		List<Operador> operadors = hibernateUtil.buscar(operador, pagina);
		result.include("operadors", operadors);

	}
}
