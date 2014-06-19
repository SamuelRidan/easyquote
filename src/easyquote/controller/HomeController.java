package easyquote.controller;

import java.util.List;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import easyquote.anotacoes.Public;
import easyquote.hibernate.HibernateUtil;

@Resource
public class HomeController {
	
	private HibernateUtil hibernateUtil;
	private final Result result;

	public HomeController(Result result, HibernateUtil hibernateUtil){
		this.result = result;
		this.hibernateUtil = hibernateUtil;
		this.hibernateUtil.setResult(result);
	}
	
	@SuppressWarnings("rawtypes")
	@Public
	public void home() {
		List contratoAditivo = hibernateUtil.buscaPorHQL("from Contrato where tipoContrato<>1");
		int contrato = contratoAditivo.size();		
		result.include("contratosAditivos", contrato);
		
		List cotacoesPendentes = hibernateUtil.buscaPorHQL("from Cotacao where status.id=1");
		int cotacaoPendente = cotacoesPendentes.size();
		result.include("cotacoesPendentes", cotacaoPendente);
		
		List cotacoesAndamento = hibernateUtil.buscaPorHQL("from Pedido where status.id<>1");
		int cotacaoAndamento = cotacoesAndamento.size();
		result.include("cotacoesAndamento", cotacaoAndamento);
		
		List pedidos = hibernateUtil.buscaPorHQL("from Pedido where status.id=1");
		int pedido = pedidos.size();
		result.include("pedidos", pedido);
	}

	@Public
	public void sobre() {

	}

}
