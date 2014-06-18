package scada.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.mail.EmailException;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Contrato;
import scada.modelo.Pedido;
import scada.modelo.Principal;
import scada.modelo.StatusPedido;
import scada.sessao.SessaoGeral;
import scada.util.CommonsMail;
import scada.util.GeradorPDF;
import scada.util.Util;
import scada.util.UtilController;
import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;

import com.itextpdf.text.DocumentException;

@Resource
public class PrincipalController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;
	private Validator validator;
	
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
	public void salvarPrincipal(Principal principal) throws FileNotFoundException, DocumentException, IOException, EmailException {
		Pedido pedido = new Pedido();
		if (Util.preenchido(sessaoGeral.getValor("idPrincipal"))) {
			principal.setId((Integer) sessaoGeral.getValor("idPrincipal"));
		}

		List contratos = HibernateUtilTest.executarConsultaHQL("from Contrato where id = :idContrato", "idContrato", principal.getContrato().getId());
		for (Object obj: contratos){
			Contrato c = (Contrato)obj;
			HibernateUtilTest.executarHQL("update Cotacao set status.id = :idStatus where id = :idCotacao","idStatus", 3, "idCotacao", c.getCotacao().getId());
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
