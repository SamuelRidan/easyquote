package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.Seguimento;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class SeguimentoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public SeguimentoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarSeguimento")
	public void criarSeguimento() {

		sessaoGeral.adicionar("idSeguimento", null);
		result.forwardTo(this).criarEditarSeguimento();
	}

	@Path("/seguimento/editarSeguimento/{seguimento.id}")
	@Funcionalidade(filhaDe = "criarEditarSeguimento")
	public void editarSeguimento(Seguimento seguimento) {

		seguimento = hibernateUtil.selecionar(seguimento);

		sessaoGeral.adicionar("idSeguimento", seguimento.getId());
		result.include(seguimento);
		result.forwardTo(this).criarEditarSeguimento();
	}

	@Funcionalidade(nome = "Criar e editar seguimentos")
	public void criarEditarSeguimento() {
	}

	@Path("/seguimento/excluirSeguimento/{seguimento.id}")
	@Funcionalidade(nome = "Excluir seguimento")
	public void excluirSeguimento(Seguimento seguimento) {

		hibernateUtil.deletar(seguimento);
		result.include("sucesso", "Seguimento excluído(a) com sucesso");
		result.forwardTo(this).listarSeguimentos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarSeguimento")
	public void salvarSeguimento(Seguimento seguimento) {

		if (Util.preenchido(sessaoGeral.getValor("idSeguimento"))) {

			seguimento.setId((Integer) sessaoGeral.getValor("idSeguimento"));
		}

		hibernateUtil.salvarOuAtualizar(seguimento);
		result.include("sucesso", "Seguimento salvo(a) com sucesso");
		result.redirectTo(this).listarSeguimentos(new Seguimento(), null);
	}

	@Funcionalidade(nome = "Seguimentos de Compra/Aluguel", modulo = "SAP")
	public void listarSeguimentos(Seguimento seguimento, Integer pagina) {

		seguimento = (Seguimento) UtilController.preencherFiltros(seguimento, "seguimento", sessaoGeral);
		if (Util.vazio(seguimento)) {
			seguimento = new Seguimento();
		}

		List<Seguimento> seguimentos = hibernateUtil.buscar(seguimento, pagina);
		result.include("seguimentos", seguimentos);

	}
}
