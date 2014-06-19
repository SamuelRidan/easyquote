package easyquote.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.mail.EmailException;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

import com.itextpdf.text.DocumentException;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Contrato;
import easyquote.modelo.ListaCotacaoFornecedor;
import easyquote.modelo.Pedido;
import easyquote.modelo.Principal;
import easyquote.modelo.StatusPedido;
import easyquote.sessao.SessaoGeral;
import easyquote.util.CommonsMail;
import easyquote.util.GeradorPDF;
import easyquote.util.Util;
import easyquote.util.UtilController;

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

	@SuppressWarnings("rawtypes")
	@Funcionalidade(filhaDe = "criarEditarPrincipal")
	public void criarPrincipal() {
		int idContrato = 0;
   		Double total = 0.00;
		List contrato = hibernateUtil.retorna1HQL("from Contrato order by id desc");		    		
		for (Object obj: contrato) {
			Contrato c = (Contrato)obj;
			List preco = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where cotacao.id="+c.getCotacao().getId()+" and fornecedor.id="+c.getFornecedor().getId());
			for (Object ob: preco){
				ListaCotacaoFornecedor lcf = (ListaCotacaoFornecedor)ob;
				total = total + (lcf.getPreco() * lcf.getListaCotacao().getQuantidade());
			}
			idContrato = c.getId();
		}

		sessaoGeral.adicionar("idPrincipal", null);
		result.include("contrato", idContrato);
		result.include("total", total);
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

	@SuppressWarnings("rawtypes")
	@Funcionalidade(filhaDe = "criarEditarPrincipal")
	public void salvarPrincipal(Principal principal) throws FileNotFoundException, DocumentException, IOException, EmailException {
		Pedido pedido = new Pedido();
		if (Util.preenchido(sessaoGeral.getValor("idPrincipal"))) {
			principal.setId((Integer) sessaoGeral.getValor("idPrincipal"));
		}

		List contratos = hibernateUtil.buscaPorHQL("from Contrato where id = "+ principal.getContrato().getId());
		for (Object obj: contratos){
			Contrato c = (Contrato)obj;
			hibernateUtil.executarSQL("update Cotacao set status.id = 3 where id = " + c.getCotacao().getId());
			GeradorPDF.GerarContratoPDF(c);
			try {
				CommonsMail.enviaEmailComAnexo("C:/ContratosGerados/" + c.getId() + "_" + c.getFornecedor().getCnpj() + ".pdf", c.getFornecedor().getEmail(),
						c.getFornecedor().getOperador().getLogin(), "Sistema de envio automático de contratos", "Olá!\n\nSegue em anexo o nosso contrato.");
			} catch (Exception e) {
				result.include("erro","Por falaha de conexão não foi possível enviar o econtrato por email ao fornecedor!");
				e.printStackTrace();
			}
			pedido.setCotacao(c.getCotacao().getCotacao());
			pedido.setFornecedor(c.getFornecedor());
			pedido.setStatus(new StatusPedido(1));
			
		}
		
		hibernateUtil.salvarOuAtualizar(principal);		
		hibernateUtil.salvarOuAtualizar(pedido);
		result.include("sucesso", "Contrato criado com sucesso!");
		result.redirectTo(ContratoController.class).listarContratos(new Contrato(), null);
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
