package easyquote.controller;

import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import easyquote.anotacoes.Public;
import easyquote.hibernate.HibernateUtil;
import easyquote.sessao.SessaoGeral;

@Resource
public class AnaliseController {

	private HibernateUtil hibernateUtil;

	public AnaliseController(Result result, SessaoGeral sessaoGeral, HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}

	@Public
	public void analisar() {

		this.hibernateUtil.gerarEstatisticas();
	}
}