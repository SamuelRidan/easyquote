package easyquote.controller;

import java.util.List;

import javax.servlet.jsp.tagext.ValidationMessage;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Comprador;
import easyquote.modelo.Fornecedor;
import easyquote.modelo.GrupoOperador;
import easyquote.modelo.Operador;
import easyquote.sessao.SessaoGeral;
import easyquote.util.GeradorDeMd5;
import easyquote.util.Util;
import easyquote.util.UtilController;


import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;

@Resource
public class OperadorController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private Validator validator;
	private HibernateUtil hibernateUtil;

	public OperadorController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void criarOperador() {

		sessaoGeral.adicionar("idOperador", null);
		result.forwardTo(this).criarEditarOperador();
	}

	@Path("/operador/editarOperador/{operador.id}")
	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void editarOperador(Operador operador) {

		operador = hibernateUtil.selecionar(operador);

		sessaoGeral.adicionar("idOperador", operador.getId());
		result.include(operador);
		result.forwardTo(this).criarEditarOperador();
	}

	@Funcionalidade(nome = "Criar e editar operadores")
	public void criarEditarOperador() {
		
		List<GrupoOperador> grupoOperador = hibernateUtil.buscar(new GrupoOperador());
		result.include("grupoOperador", grupoOperador);
		
	}

	@Path("/operador/excluirOperador/{operador.id}")
	@Funcionalidade(nome = "Excluir operador")
	public void excluirOperador(Operador operador) {

		Comprador comprador = new Comprador();
		comprador.setOperador(operador);
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setOperador(operador);
		
		if ((hibernateUtil.contar(comprador) > 0) || (hibernateUtil.contar(fornecedor) > 0)){
			validator.add((Message) new ValidationMessage("Não é possível excluir este operador!", "Erro!"));
			validator.onErrorForwardTo(this).listarOperadors(null, null);
		}
		
		hibernateUtil.deletar(operador);
		result.include("sucesso", "Operador excluído(a) com sucesso");
		result.forwardTo(this).listarOperadors(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarOperador")
	public void salvarOperador(Operador operador) {

		if (Util.preenchido(sessaoGeral.getValor("idOperador"))) {

			operador.setId((Integer) sessaoGeral.getValor("idOperador"));
		}
		
		String senha = GeradorDeMd5.converter(operador.getSenha());
		operador.setSenha(senha);		
		
		hibernateUtil.salvarOuAtualizar(operador);
		result.include("sucesso", "Operador salvo(a) com sucesso");
		
		if (operador.getGrupoOperador().getId() == 2){
			result.redirectTo(CompradorController.class).criarComprador();
		} else {
			if (operador.getGrupoOperador().getId() == 3){
				result.redirectTo(FornecedorController.class).criarFornecedor();
			} else {
				result.redirectTo(this).listarOperadors(new Operador(), null);
			}
		}
	}

	@Funcionalidade(nome = "Operadores", modulo = "Acesso")
	public void listarOperadors(Operador operador, Integer pagina) {

		operador = (Operador) UtilController.preencherFiltros(operador, "operador", sessaoGeral);
		if (Util.vazio(operador)) {
			operador = new Operador();
		}

		List<Operador> operadors = hibernateUtil.buscar(operador, pagina);
		result.include("operadors", operadors);
		
		List<GrupoOperador> grupo = hibernateUtil.buscar(new GrupoOperador());
		result.include("grupooperador", grupo);

	}
	
	public Operador retornaUltimoOperador(){
		Operador operador = null;
		@SuppressWarnings("rawtypes")
		List operadores = hibernateUtil.retorna1HQL("from Operador order by id desc");
		for (Object obj: operadores){
			Operador op = (Operador)obj;
			operador = hibernateUtil.selecionar(op);
		}
		
		return operador;

	}
	
}
