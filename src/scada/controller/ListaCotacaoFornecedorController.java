package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.ListaCotacaoFornecedor;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ListaCotacaoFornecedorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public ListaCotacaoFornecedorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarListaCotacaoFornecedor")
	public void criarListaCotacaoFornecedor() {

		sessaoGeral.adicionar("idListaCotacaoFornecedor", null);
		result.forwardTo(this).criarEditarListaCotacaoFornecedor();
	}

	@Path("/listaCotacaoFornecedor/editarListaCotacaoFornecedor/{listaCotacaoFornecedor.id}")
	@Funcionalidade(filhaDe = "criarEditarListaCotacaoFornecedor")
	public void editarListaCotacaoFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {

		listaCotacaoFornecedor = hibernateUtil.selecionar(listaCotacaoFornecedor);

		sessaoGeral.adicionar("idListaCotacaoFornecedor", listaCotacaoFornecedor.getId());
		result.include(listaCotacaoFornecedor);
		result.forwardTo(this).criarEditarListaCotacaoFornecedor();
	}

	@Funcionalidade(nome = "Criar e editar listaCotacaoFornecedors")
	public void criarEditarListaCotacaoFornecedor() {
	}

	@Path("/listaCotacaoFornecedor/excluirListaCotacaoFornecedor/{listaCotacaoFornecedor.id}")
	@Funcionalidade(nome = "Excluir listaCotacaoFornecedor")
	public void excluirListaCotacaoFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {

		hibernateUtil.deletar(listaCotacaoFornecedor);
		result.include("sucesso", "ListaCotacaoFornecedor excluído(a) com sucesso");
		result.forwardTo(this).listarListaCotacaoFornecedors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarListaCotacaoFornecedor")
	public void salvarListaCotacaoFornecedor(ListaCotacaoFornecedor listaCotacaoFornecedor) {

		if (Util.preenchido(sessaoGeral.getValor("idListaCotacaoFornecedor"))) {

			listaCotacaoFornecedor.setId((Integer) sessaoGeral.getValor("idListaCotacaoFornecedor"));
		}

		hibernateUtil.salvarOuAtualizar(listaCotacaoFornecedor);
		result.include("sucesso", "ListaCotacaoFornecedor salvo(a) com sucesso");
		result.redirectTo(this).listarListaCotacaoFornecedors(new ListaCotacaoFornecedor(), null);
	}

//	@Funcionalidade(nome = "ListaCotacaoFornecedors", modulo = "New")
	public void listarListaCotacaoFornecedors(ListaCotacaoFornecedor listaCotacaoFornecedor, Integer pagina) {

		listaCotacaoFornecedor = (ListaCotacaoFornecedor) UtilController.preencherFiltros(listaCotacaoFornecedor, "listaCotacaoFornecedor", sessaoGeral);
		if (Util.vazio(listaCotacaoFornecedor)) {
			listaCotacaoFornecedor = new ListaCotacaoFornecedor();
		}

		List<ListaCotacaoFornecedor> listaCotacaoFornecedors = hibernateUtil.buscar(listaCotacaoFornecedor, pagina);
		result.include("listaCotacaoFornecedors", listaCotacaoFornecedors);

	}
	
}
