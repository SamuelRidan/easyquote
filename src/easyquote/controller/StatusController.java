package easyquote.controller;

import java.util.List;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Status;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;



import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class StatusController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public StatusController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarStatus")
	public void criarStatus() {

		sessaoGeral.adicionar("idStatus", null);
		result.forwardTo(this).criarEditarStatus();
	}

	@Path("/status/editarStatus/{status.id}")
	@Funcionalidade(filhaDe = "criarEditarStatus")
	public void editarStatus(Status status) {

		status = hibernateUtil.selecionar(status);

		sessaoGeral.adicionar("idStatus", status.getId());
		result.include(status);
		result.forwardTo(this).criarEditarStatus();
	}

	@Funcionalidade(nome = "Criar e editar statuss")
	public void criarEditarStatus() {
	}

	@Path("/status/excluirStatus/{status.id}")
	@Funcionalidade(nome = "Excluir status")
	public void excluirStatus(Status status) {

		hibernateUtil.deletar(status);
		result.include("sucesso", "Status excluído(a) com sucesso");
		result.forwardTo(this).listarStatuss(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarStatus")
	public void salvarStatus(Status status) {

		if (Util.preenchido(sessaoGeral.getValor("idStatus"))) {

			status.setId((Integer) sessaoGeral.getValor("idStatus"));
		}

		hibernateUtil.salvarOuAtualizar(status);
		result.include("sucesso", "Status salvo(a) com sucesso");
		result.redirectTo(this).listarStatuss(new Status(), null);
	}

	@Funcionalidade(nome = "Status", modulo = "Informações")
	public void listarStatuss(Status status, Integer pagina) {

		status = (Status) UtilController.preencherFiltros(status, "status", sessaoGeral);
		if (Util.vazio(status)) {
			status = new Status();
		}

		List<Status> statuss = hibernateUtil.buscar(status, pagina);
		result.include("statuss", statuss);

	}
}
