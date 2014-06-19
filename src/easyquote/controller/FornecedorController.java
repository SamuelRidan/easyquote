package easyquote.controller;

import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Fornecedor;
import easyquote.modelo.Seguimento;
import easyquote.sessao.SessaoGeral;
import easyquote.util.Util;
import easyquote.util.UtilController;

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
		Fornecedor fornecedor = new Fornecedor();
		OperadorController oc = new OperadorController(result, sessaoGeral, hibernateUtil);
		
		sessaoGeral.adicionar("idFornecedor", null);
		fornecedor.setOperador(oc.retornaUltimoOperador());
		result.include(fornecedor);
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
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);
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
		
		OperadorController op = new OperadorController(result,sessaoGeral,hibernateUtil);
		result.redirectTo(op).criarOperador();
		
	}

	@Funcionalidade(nome = "Fornecedores", modulo="Informações")
	public void listarFornecedors(Fornecedor fornecedor, Integer pagina) {

		fornecedor = (Fornecedor) UtilController.preencherFiltros(fornecedor, "fornecedor", sessaoGeral);
		if (Util.vazio(fornecedor)) {
			fornecedor = new Fornecedor();
		}

		List<Fornecedor> fornecedors = hibernateUtil.buscar(fornecedor, pagina);
		result.include("fornecedors", fornecedors);
		
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);

	}
	

	@SuppressWarnings("unchecked")
	public void relatorioFornecedor(Fornecedor fornecedor, Integer pagina) {

		fornecedor = (Fornecedor) UtilController.preencherFiltros(fornecedor, "fornecedor", sessaoGeral);
		if (Util.vazio(fornecedor)) {
			fornecedor = new Fornecedor();
		}

		List<Fornecedor> fornecedors = hibernateUtil.buscar(fornecedor, pagina);
		result.include("fornecedor", fornecedors);
		
		List<Fornecedor> fornecedores = hibernateUtil.buscaPorHQL("from Fornecedor order by reputacao desc");
		result.include("fornecedorOrd", fornecedores);

	}	
}
