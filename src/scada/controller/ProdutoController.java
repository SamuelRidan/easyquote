package scada.controller;

import java.util.List;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Produto;
import scada.modelo.Seguimento;
import scada.sessao.SessaoGeral;
import scada.util.Util;
import scada.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class ProdutoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public ProdutoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void criarProduto() {

		sessaoGeral.adicionar("idProduto", null);
		result.forwardTo(this).criarEditarProduto();
	}

	@Path("/produto/editarProduto/{produto.id}")
	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void editarProduto(Produto produto) {

		produto = hibernateUtil.selecionar(produto);

		sessaoGeral.adicionar("idProduto", produto.getId());
		result.include(produto);
		result.forwardTo(this).criarEditarProduto();
	}

	@Funcionalidade(nome = "Criar e editar produtos")
	public void criarEditarProduto() {
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);
	}

	@Path("/produto/excluirProduto/{produto.id}")
	@Funcionalidade(nome = "Excluir produto")
	public void excluirProduto(Produto produto) {

		hibernateUtil.deletar(produto);
		result.include("sucesso", "Produto excluído(a) com sucesso");
		result.forwardTo(this).listarProdutos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarProduto")
	public void salvarProduto(Produto produto) {

		if (Util.preenchido(sessaoGeral.getValor("idProduto"))) {

			produto.setId((Integer) sessaoGeral.getValor("idProduto"));
		}

		hibernateUtil.salvarOuAtualizar(produto);
		result.include("sucesso", "Produto salvo(a) com sucesso");
		result.redirectTo(this).listarProdutos(new Produto(), null);
	}

	@Funcionalidade(nome = "Produtos", modulo = "Informações")
	public void listarProdutos(Produto produto, Integer pagina) {

		produto = (Produto) UtilController.preencherFiltros(produto, "produto", sessaoGeral);
		if (Util.vazio(produto)) {
			produto = new Produto();
		}

		List<Produto> produtos = hibernateUtil.buscar(produto, pagina);
		result.include("produtos", produtos);
		
		List<Seguimento> tipoSeg = hibernateUtil.buscar(new Seguimento());
		result.include("tipoSeg", tipoSeg);

	}
}
