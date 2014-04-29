package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Principal;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class PrincipalController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public PrincipalController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarPrincipal")
	public void criarPrincipal() {

		sessaoGeral.adicionar("idPrincipal", null);
		result.forwardTo(this).criarEditarPrincipal();
	}

	@Path("/principal/editarPrincipal/{principal.id}")
	@Funcionalidade(filhaDe = "criarEditarPrincipal")
	public void editarPrincipal(Principal principal) {

		principal = hibernateUtil.selecionar(principal);

		sessaoGeral.adicionar("idPrincipal", principal.getId());
		result.include(principal);
		result.forwardTo(this).criarEditarPrincipal();
	}

	@Funcionalidade(nome = "Criar e editar principals")
	public void criarEditarPrincipal() {
	}

	@Path("/principal/excluirPrincipal/{principal.id}")
	@Funcionalidade(nome = "Excluir principal")
	public void excluirPrincipal(Principal principal) {

		hibernateUtil.deletar(principal);
		result.include("sucesso", "Principal excluído(a) com sucesso");
		result.forwardTo(this).listarPrincipals(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarPrincipal")
	public void salvarPrincipal(Principal principal) {

		if (Util.preenchido(sessaoGeral.getValor("idPrincipal"))) {

			principal.setId((Integer) sessaoGeral.getValor("idPrincipal"));
		}

		hibernateUtil.salvarOuAtualizar(principal);
		result.include("sucesso", "Principal salvo(a) com sucesso");
		result.redirectTo(this).listarPrincipals(new Principal(), null);
	}

	@Funcionalidade(nome = "Contrato Principal", modulo = "Contratos")
	public void listarPrincipals(Principal principal, Integer pagina) {

		principal = (Principal) UtilController.preencherFiltros(principal, "principal", sessaoGeral);
		if (Util.vazio(principal)) {
			principal = new Principal();
		}

		List<Principal> principals = hibernateUtil.buscar(principal, pagina);
		result.include("principals", principals);

	}
}
