package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.TipoContrato;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class TipoContratoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public TipoContratoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarTipoContrato")
	public void criarTipoContrato() {

		sessaoGeral.adicionar("idTipoContrato", null);
		result.forwardTo(this).criarEditarTipoContrato();
	}

	@Path("/tipoContrato/editarTipoContrato/{tipoContrato.id}")
	@Funcionalidade(filhaDe = "criarEditarTipoContrato")
	public void editarTipoContrato(TipoContrato tipoContrato) {

		tipoContrato = hibernateUtil.selecionar(tipoContrato);

		sessaoGeral.adicionar("idTipoContrato", tipoContrato.getId());
		result.include(tipoContrato);
		result.forwardTo(this).criarEditarTipoContrato();
	}

	@Funcionalidade(nome = "Criar e editar tipoContratos")
	public void criarEditarTipoContrato() {
	}

	@Path("/tipoContrato/excluirTipoContrato/{tipoContrato.id}")
	@Funcionalidade(nome = "Excluir tipoContrato")
	public void excluirTipoContrato(TipoContrato tipoContrato) {

		hibernateUtil.deletar(tipoContrato);
		result.include("sucesso", "TipoContrato excluído(a) com sucesso");
		result.forwardTo(this).listarTipoContratos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarTipoContrato")
	public void salvarTipoContrato(TipoContrato tipoContrato) {

		if (Util.preenchido(sessaoGeral.getValor("idTipoContrato"))) {

			tipoContrato.setId((Integer) sessaoGeral.getValor("idTipoContrato"));
		}

		hibernateUtil.salvarOuAtualizar(tipoContrato);
		result.include("sucesso", "TipoContrato salvo(a) com sucesso");
		result.redirectTo(this).listarTipoContratos(new TipoContrato(), null);
	}

	@Funcionalidade(nome = "Tipos de Contrato", modulo = "Informações")
	public void listarTipoContratos(TipoContrato tipoContrato, Integer pagina) {

		tipoContrato = (TipoContrato) UtilController.preencherFiltros(tipoContrato, "tipoContrato", sessaoGeral);
		if (Util.vazio(tipoContrato)) {
			tipoContrato = new TipoContrato();
		}

		List<TipoContrato> tipoContratos = hibernateUtil.buscar(tipoContrato, pagina);
		result.include("tipoContratos", tipoContratos);

	}
}
