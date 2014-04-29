package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Aditivo;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class AditivoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public AditivoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarAditivo")
	public void criarAditivo() {

		sessaoGeral.adicionar("idAditivo", null);
		result.forwardTo(this).criarEditarAditivo();
	}

	@Path("/aditivo/editarAditivo/{aditivo.id}")
	@Funcionalidade(filhaDe = "criarEditarAditivo")
	public void editarAditivo(Aditivo aditivo) {

		aditivo = hibernateUtil.selecionar(aditivo);

		sessaoGeral.adicionar("idAditivo", aditivo.getId());
		result.include(aditivo);
		result.forwardTo(this).criarEditarAditivo();
	}

	@Funcionalidade(nome = "Criar e editar aditivos")
	public void criarEditarAditivo() {
	}

	@Path("/aditivo/excluirAditivo/{aditivo.id}")
	@Funcionalidade(nome = "Excluir aditivo")
	public void excluirAditivo(Aditivo aditivo) {

		hibernateUtil.deletar(aditivo);
		result.include("sucesso", "Aditivo excluído(a) com sucesso");
		result.forwardTo(this).listarAditivos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarAditivo")
	public void salvarAditivo(Aditivo aditivo) {

		if (Util.preenchido(sessaoGeral.getValor("idAditivo"))) {

			aditivo.setId((Integer) sessaoGeral.getValor("idAditivo"));
		}

		hibernateUtil.salvarOuAtualizar(aditivo);
		result.include("sucesso", "Aditivo salvo(a) com sucesso");
		result.redirectTo(this).listarAditivos(new Aditivo(), null);
	}

	@Funcionalidade(nome = "Contrato Aditivo", modulo = "Contratos")
	public void listarAditivos(Aditivo aditivo, Integer pagina) {

		aditivo = (Aditivo) UtilController.preencherFiltros(aditivo, "aditivo", sessaoGeral);
		if (Util.vazio(aditivo)) {
			aditivo = new Aditivo();
		}

		List<Aditivo> aditivos = hibernateUtil.buscar(aditivo, pagina);
		result.include("aditivos", aditivos);

	}
}
