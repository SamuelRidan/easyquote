package easyquote.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

import com.itextpdf.text.DocumentException;

import easyquote.anotacoes.Funcionalidade;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Aditivo;
import easyquote.modelo.Contrato;
import easyquote.modelo.Fornecedor;
import easyquote.modelo.Indices;
import easyquote.modelo.ListaCotacaoFornecedor;
import easyquote.modelo.TipoContrato;
import easyquote.sessao.SessaoGeral;
import easyquote.util.CommonsMail;
import easyquote.util.GeradorPDF;
import easyquote.util.Util;
import easyquote.util.UtilController;

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
	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(nome = "Vencimento dos contratos", modulo = "Relatórios")
	public void equalizarForn(){	
		Date date = new Date();
		GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar dataAtual = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar dataMesAdiante = (GregorianCalendar) GregorianCalendar.getInstance();
		dataAtual.setTime(date);
		dataMesAdiante.setTime(date);
		dataMesAdiante.add(Calendar.MONTH, 1);
		List<Contrato> contrato = new ArrayList<Contrato>();
		
		HibernateUtil hibernateUtil = new HibernateUtil();
		List contratos = hibernateUtil.buscaPorHQL("from Contrato where tipoContrato.id <> 1");
		for (Object obj: contratos) {
            Contrato c = (Contrato)obj;
            data.setTime(c.getInicioVigencia().getTime());
            do {
	            data.add(Calendar.MONTH, c.getPeriodicidadeReajuste());
	            if ((data.getTime().after(dataAtual.getTime())) && (data.getTime().before(dataMesAdiante.getTime())) && (c.getFimVigencia().getTime().after(dataAtual.getTime()))) {	 
	            	contrato.add(c);
	            }
            } while (data.getTime().before(c.getFimVigencia().getTime()));    
		}
		
		result.include("contratos", contrato);
	}
	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(nome = "Vencimento dos contratos", modulo = "Relatórios")
	public void equalizar(){	
		
		Date date = new Date();
		GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar dataAtual = (GregorianCalendar) GregorianCalendar.getInstance();
		GregorianCalendar dataMesAdiante = (GregorianCalendar) GregorianCalendar.getInstance();
		dataAtual.setTime(date);
		dataMesAdiante.setTime(date);
		dataMesAdiante.add(Calendar.MONTH, 1);
		
		List<Contrato> contrato = new ArrayList<Contrato>();
		List<Indices> indice = new ArrayList<Indices>();
		
		List contratos = hibernateUtil.buscaPorHQL("from Contrato where tipoContrato.id <> 1");
		for (Object obj: contratos) {
            Contrato c = (Contrato)obj;
            data.setTime(c.getInicioVigencia().getTime());
            do {
	            data.add(Calendar.MONTH, c.getPeriodicidadeReajuste());
	            if ((data.getTime().after(dataAtual.getTime())) && (data.getTime().before(dataMesAdiante.getTime())) && (c.getFimVigencia().getTime().after(dataAtual.getTime()))) {
	            	contrato.add(c);
	            	List indices = hibernateUtil.buscaPorHQL("from Indices where fornecedor.id = " + c.getFornecedor().getId());
		 			if (indices.size()!=0){
		 				for (Object ob: indices){
			 				Indices i = (Indices)ob;
			 				indice.add(i);
		 				}
		 			}
	            }
            } while (data.getTime().before(c.getFimVigencia().getTime()));    
		}
		
		List<Fornecedor> fornecedores = hibernateUtil.buscar(new Fornecedor());
		result.include("fornecedores", fornecedores);
		
		result.include("contratos", contrato);
		result.include("indices", indice);
		
	}
	
	@SuppressWarnings("rawtypes")
	@Funcionalidade(filhaDe="equalizar")
	public void aceitarIndices(Integer idContrato) throws FileNotFoundException, DocumentException, IOException{
		Contrato contrato = new Contrato(idContrato);		
		contrato = hibernateUtil.selecionar(contrato);
		
		Aditivo aditivo = new Aditivo();
		Double total = 0.0;
		
		aditivo.setContrato(contrato);
		List preco = hibernateUtil.buscaPorHQL("from ListaCotacaoFornecedor where cotacao.id="+contrato.getCotacao().getId()+" and fornecedor.id="+contrato.getFornecedor().getId());
		for (Object obj: preco){
			ListaCotacaoFornecedor lcf = (ListaCotacaoFornecedor)obj;
			List indices = hibernateUtil.buscaPorHQL("from Indices where fornecedor.id = " + lcf.getFornecedor().getId());
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
