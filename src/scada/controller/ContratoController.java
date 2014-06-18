package scada.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.DocumentException;

import scada.anotacoes.Funcionalidade;
import scada.hibernate.HibernateUtil;
import scada.modelo.Aditivo;
import scada.modelo.Contrato;
import scada.modelo.Indices;
import scada.modelo.ListaCotacaoFornecedor;
import scada.modelo.TipoContrato;
import scada.sessao.SessaoGeral;
import scada.util.CommonsMail;
import scada.util.GeradorPDF;
import scada.util.Util;
import scada.util.UtilController;
import teste.HibernateUtilTest;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ContratoController {

	private final Result result;
	private SessaoGeral sessaoGeral;
	private HibernateUtil hibernateUtil;

	public ContratoController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.result = result;
		this.sessaoGeral = sessaoGeral;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}

	@Funcionalidade(filhaDe = "criarEditarContrato")
	public void criarContrato() {
		
		sessaoGeral.adicionar("idContrato", null);
		result.forwardTo(this).criarEditarContrato();
	}
	

	@Path("/contrato/editarContrato/{contrato.id}")
	@Funcionalidade(filhaDe = "criarEditarContrato")
	public void editarContrato(Contrato contrato) {

		contrato = hibernateUtil.selecionar(contrato);

		sessaoGeral.adicionar("idContrato", contrato.getId());
		result.include(contrato);
		result.forwardTo(this).criarEditarContrato();
	}

	@Funcionalidade(nome = "Criar e editar contratos")
	public void criarEditarContrato() {
		
		List<TipoContrato> tipoContrato = hibernateUtil.buscar(new TipoContrato());
		result.include("tipoContrato", tipoContrato);
		
	}
	
	@Path("/contrato/edicaoContrato/{contrato.id}")
	@Funcionalidade(filhaDe = "editContrato")
	public void edicaoContrato(Contrato contrato) {

		contrato = hibernateUtil.selecionar(contrato);

		sessaoGeral.adicionar("idContrato", contrato.getId());
		result.include(contrato);
		result.forwardTo(this).editContrato();
	}
	
	@Funcionalidade(nome = "Edição de contratos")
	public void editContrato() {
		
		List<TipoContrato> tipoContrato = hibernateUtil.buscar(new TipoContrato());
		result.include("tipoContrato", tipoContrato);
		
	}

	@Path("/contrato/excluirContrato/{contrato.id}")
	@Funcionalidade(nome = "Excluir contrato")
	public void excluirContrato(Contrato contrato) {

		hibernateUtil.deletar(contrato);
		result.include("sucesso", "Contrato excluído(a) com sucesso");
		result.forwardTo(this).listarContratos(null, null);
	}

	@Funcionalidade(filhaDe = "criarEditarContrato")
	public void salvarContrato(Contrato contrato) {
		if (Util.preenchido(sessaoGeral.getValor("idContrato"))) {
			contrato.setId((Integer) sessaoGeral.getValor("idContrato"));
		}
		
		hibernateUtil.salvarOuAtualizar(contrato);
		result.include("sucesso", "Contrato salvo(a) com sucesso");
		result.redirectTo(PrincipalController.class).criarPrincipal();
	}
	
	@Funcionalidade(filhaDe = "criarEditarContrato")
	public void salvar(Contrato contrato) {
		if (Util.preenchido(sessaoGeral.getValor("idContrato"))) {
			contrato.setId((Integer) sessaoGeral.getValor("idContrato"));
		}
		
		hibernateUtil.salvarOuAtualizar(contrato);
		result.include("sucesso", "Contrato salvo(a) com sucesso");
		result.redirectTo(PrincipalController.class).criarPrincipal();
	}

	@Funcionalidade(nome = "Contratos", modulo = "Contratos")
	public void listarContratos(Contrato contrato, Integer pagina) {

		contrato = (Contrato) UtilController.preencherFiltros(contrato, "contrato", sessaoGeral);
		if (Util.vazio(contrato)) {
			contrato = new Contrato();
		}

		List<Contrato> contratos = hibernateUtil.buscar(contrato, pagina);
		result.include("contratos", contratos);
		
		List<TipoContrato> tipoContrato = hibernateUtil.buscar(new TipoContrato());
		result.include("tipoContrato", tipoContrato);

	}
	
	@Funcionalidade(nome = "Vencimento dos contratos", modulo = "Relatórios")
	public void equalizarForn(){		
	}
	
	@Funcionalidade(nome = "Vencimento dos contratos", modulo = "Relatórios")
	public void equalizar(){	
	}
	
	@Funcionalidade(filhaDe="equalizar")
	public void aceitarIndices(Integer idContrato) throws FileNotFoundException, DocumentException, IOException{
		Contrato contrato = new Contrato(idContrato);		
		contrato = hibernateUtil.selecionar(contrato);
		
		Aditivo aditivo = new Aditivo();
		Double total = 0.0;
		
		aditivo.setContrato(contrato);
		List preco = HibernateUtilTest.executarConsultaHQL("from ListaCotacaoFornecedor where cotacao.id="+contrato.getCotacao().getId()+" and fornecedor.id="+contrato.getFornecedor().getId());
		for (Object obj: preco){
			ListaCotacaoFornecedor lcf = (ListaCotacaoFornecedor)obj;
			List indices = HibernateUtilTest.executarConsultaHQL("from Indices where fornecedor.id = " + lcf.getFornecedor().getId());
			for (Object ob: indices){
				Indices i = (Indices)ob;				
				total = total + (((lcf.getPreco() * lcf.getListaCotacao().getQuantidade())*i.getIndice())/100) + (lcf.getPreco() * lcf.getListaCotacao().getQuantidade());
			}
		}
		
		aditivo.setPrecoTotal(total);
		
		hibernateUtil.salvarOuAtualizar(aditivo);
		
		GeradorPDF.GerarContratoAditivoPDF(contrato);
		
		try {
			CommonsMail.enviaEmailComAnexo("C:/ContratosGerados/aditivo_" + contrato.getId() + "_" + contrato.getFornecedor().getCnpj() + ".pdf", contrato.getFornecedor().getEmail(),
					contrato.getFornecedor().getOperador().getLogin(), "Sistema de envio automático de contratos", "Olá!\n\nSegue em anexo o nosso contrato.");
		} catch (Exception e) {
			result.include("erro","Por falaha de conexão não foi possível enviar o econtrato por email ao fornecedor!");
			e.printStackTrace();
		}
		
		result.use(Results.json()).from("ok").serialize();
	}
}
