package easyquote.controller;

import java.util.List;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Setor;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;



import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class SetorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public SetorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarSetor")
	public void criarSetor() {

		sessaoGeral.adicionar("idSetor", null);
		result.forwardTo(this).criarEditarSetor();
	}

	@Path("/setor/editarSetor/{setor.id}")
	@Funcionalidade(filhaDe = "criarEditarSetor")
	public void editarSetor(Setor setor) {

		setor = hibernateUtil.selecionar(setor);

		sessaoGeral.adicionar("idSetor", setor.getId());
		result.include(setor);
		result.forwardTo(this).criarEditarSetor();
	}

	@Funcionalidade(nome = "Criar e editar setors")
	public void criarEditarSetor() {
	}

	@Path("/setor/excluirSetor/{setor.id}")
	@Funcionalidade(nome = "Excluir setor")
	public void excluirSetor(Setor setor) {

		hibernateUtil.deletar(setor);
		result.include("sucesso", "Setor excluído(a) com sucesso");
		result.forwardTo(this).listarSetors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarSetor")
	public void salvarSetor(Setor setor) {

		if (Util.preenchido(sessaoGeral.getValor("idSetor"))) {

			setor.setId((Integer) sessaoGeral.getValor("idSetor"));
		}

		hibernateUtil.salvarOuAtualizar(setor);
		result.include("sucesso", "Setor salvo(a) com sucesso");
		result.redirectTo(this).listarSetors(new Setor(), null);
	}

	@Funcionalidade(nome = "Setores", modulo = "Informações")
	public void listarSetors(Setor setor, Integer pagina) {

		setor = (Setor) UtilController.preencherFiltros(setor, "setor", sessaoGeral);
		if (Util.vazio(setor)) {
			setor = new Setor();
		}

		List<Setor> setors = hibernateUtil.buscar(setor, pagina);
		result.include("setors", setors);

	}
}
