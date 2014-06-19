package teste;

import java.util.*;

import easyquote.auxiliar.AuxiliarTexto;
import easyquote.hibernate.HibernateUtil;
import easyquote.modelo.Comprador;
import easyquote.modelo.Cotacao;
import easyquote.util.CommonsMail;


//---------------------------------------------------------------------------------
class TarefaTemporizada extends TimerTask {

	private Timer temporizador;
	private long tempo = 1;
	private HibernateUtil hibernateUtil;
	
	public TarefaTemporizada(long tempo) {
		this.tempo = tempo;
		temporizador = new Timer();
	}

    public void agendarTarefa(long segundos) {
    	temporizador.schedule(new TarefaTemporizada(tempo), tempo*1000);
    }

    public void cancelarAgendamentoTarefa() {
    	temporizador.cancel();
    }
	
	public void run() {
		
		@SuppressWarnings("rawtypes")
		List cotacoes;
		String textoHTML;
		String emailComprador = null;
		String nomeComprador = null;
		
		try {
			
			System.out.println("Executando tarefa... ");
			
			//Pegando dataAtual + 7 dias
			Date date = new Date();
			GregorianCalendar data = (GregorianCalendar) GregorianCalendar.getInstance();
			GregorianCalendar dataAtual = (GregorianCalendar) GregorianCalendar.getInstance();
			data.setTime(date);	
			dataAtual.setTime(date);
			data.add(Calendar.DAY_OF_MONTH, 7);	
			
			cotacoes = hibernateUtil.buscaPorHQL("from Cotacao");
			
			textoHTML = AuxiliarTexto.cabecalhoHTML();
			
			for (Object obj: cotacoes) {
	            Cotacao c = (Cotacao)obj;
	            if ((c.getDataLimiteResposta().getTime().before(data.getTime())) && (c.getStatus().getId() == 1)) {	            	
	            	@SuppressWarnings("rawtypes")
					List comp = hibernateUtil.buscaPorHQL("from Comprador where operador.id = "+ c.getResponsavel().getId());
	            	for (Object ob: comp){
	            		Comprador comprador = (Comprador)ob;		            	
	            			emailComprador = comprador.getEmail();
	            			nomeComprador = comprador.getOperador().getNome();	            			
	            	}
	            	textoHTML += "Cotação #" + c.getId() + " - setor requisitante: " + c.getSetor().getDescricao() + " que se encerrará dia " + c.getDataLimiteResposta().getTime() + "<br><br>";	  
	            } 
	            
	            if (c.getDataLimiteResposta().getTime().before(dataAtual.getTime())){
	            	hibernateUtil.executarSQL("update Cotacao set status.id = 2 where id = " + c.getId());
	            }	            
			}
			
			textoHTML += AuxiliarTexto.rodapeHTML();
			
			CommonsMail.enviaEmailFormatoHtml(emailComprador, nomeComprador, "Cotações pendentes!", textoHTML);
			
			System.out.println("Tarefa executada.");
			
			agendarTarefa(86400);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
 }