package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.modelo.Fornecedor;
import scada.hibernate.HibernateUtil;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class FornecedorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public FornecedorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarFornecedor")
	public void criarFornecedor() {

		sessaoGeral.adicionar("idFornecedor", null);
		result.forwardTo(this).criarEditarFornecedor();
	}

	@Path("/fornecedor/editarFornecedor/{fornecedor.id}")
	@Funcionalidade(filhaDe = "criarEditarFornecedor")
	public void editarFornecedor(Fornecedor fornecedor) {

		fornecedor = hibernateUtil.selecionar(fornecedor);

		sessaoGeral.adicionar("idFornecedor", fornecedor.getId());
		result.include(fornecedor);
		result.forwardTo(this).criarEditarFornecedor();
	}

	@Funcionalidade(nome = "Criar e editar fornecedors")
	public void criarEditarFornecedor() {
	}

	@Path("/fornecedor/excluirFornecedor/{fornecedor.id}")
	@Funcionalidade(nome = "Excluir fornecedor")
	public void excluirFornecedor(Fornecedor fornecedor) {

		hibernateUtil.deletar(fornecedor);
		result.include("sucesso", "Fornecedor excluído(a) com sucesso");
		result.forwardTo(this).listarFornecedors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarFornecedor")
	public void salvarFornecedor(Fornecedor fornecedor) {

		if (Util.preenchido(sessaoGeral.getValor("idFornecedor"))) {

			fornecedor.setId((Integer) sessaoGeral.getValor("idFornecedor"));
		}

		hibernateUtil.salvarOuAtualizar(fornecedor);
		result.include("sucesso", "Fornecedor salvo(a) com sucesso");
		result.redirectTo(this).listarFornecedors(new Fornecedor(), null);
	}

	@Funcionalidade(nome = "Fornecedores", modulo="Acesso")
	public void listarFornecedors(Fornecedor fornecedor, Integer pagina) {

		fornecedor = (Fornecedor) UtilController.preencherFiltros(fornecedor, "fornecedor", sessaoGeral);
		if (Util.vazio(fornecedor)) {
			fornecedor = new Fornecedor();
		}

		List<Fornecedor> fornecedors = hibernateUtil.buscar(fornecedor, pagina);
		result.include("fornecedors", fornecedors);

	}
	

	public void relatorioFornecedor(Fornecedor fornecedor, Integer pagina) {

		fornecedor = (Fornecedor) UtilController.preencherFiltros(fornecedor, "fornecedor", sessaoGeral);
		if (Util.vazio(fornecedor)) {
			fornecedor = new Fornecedor();
		}

		List<Fornecedor> fornecedors = hibernateUtil.buscar(fornecedor, pagina);
		result.include("fornecedor", fornecedors);
		

		List<Fornecedor> fornecedores = HibernateUtilTest.executarConsultaHQL("from Fornecedor order by reputacao desc");

		result.include("fornecedorOrd", fornecedores);

	}	
}
