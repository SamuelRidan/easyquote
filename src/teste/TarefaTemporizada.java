package teste;

import java.text.SimpleDateFormat;
import java.util.*;

import scada.auxiliar.AuxiliarEmail;
import scada.modelo.Comprador;
import scada.modelo.Cotacao;
import scada.util.CommonsMail;

//---------------------------------------------------------------------------------
class TarefaTemporizada extends TimerTask {

	private Timer temporizador;
	private long tempo = 1;
	private long vezes = 0;
	
	public TarefaTemporizada(long tempo, long vezes) {
		this.tempo = tempo;
		this.vezes = vezes;
		temporizador = new Timer();
	}

    public void agendarTarefa(long segundos) {
    	temporizador.schedule(new TarefaTemporizada(tempo, vezes), tempo*1000);
    }

    public void cancelarAgendamentoTarefa() {
    	temporizador.cancel();
    }
	
	public void run() {
		
		List cotacoes;
		String textoHTML;
		String txt;
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
			SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 

			System.out.println("--> Data de comparação: " + dataFormatada.format(data.getTime()));
			
			cotacoes = HibernateUtilTest.executarConsultaHQL("from Cotacao");
			
			textoHTML = AuxiliarEmail.cabecalhoHTML();
			txt = AuxiliarEmail.cabecalhoTEXTO();
			
			for (Object obj: cotacoes) {
	            Cotacao c = (Cotacao)obj;
	            if ((c.getDataLimiteResposta().getTime().before(data.getTime())) && (c.getStatus().getId() == 1)) {	            	
	            	List comp = HibernateUtilTest.executarConsultaHQL("from Comprador where operador.id = :idOperador", "idOperador", c.getResponsavel().getId());
	            	for (Object ob: comp){
	            		Comprador comprador = (Comprador)ob;		            	
	            			emailComprador = comprador.getEmail();
	            			nomeComprador = comprador.getOperador().getNome();	            			
	            	}
	            	System.out.println("entrou nesse if");
	            	textoHTML += "#" + c.getId() + " tendo como requisitante o setor: " + c.getSetor().getDescricao() + "<br>";	  
	            	txt += "#" + c.getId() + " tendo como requisitante o setor: " + c.getSetor().getDescricao() + " ";	
	            } 
	            
	            if (c.getDataLimiteResposta().getTime().before(dataAtual.getTime())){
	            	HibernateUtilTest.executarHQL("update Cotacao set status.id = :idStatus where id = :idCotacao","idStatus", 2, "idCotacao", c.getId());;
	            }
	            
	        textoHTML += AuxiliarEmail.rodapeHTML();
	        txt += AuxiliarEmail.rodapeTEXTO();
	            
			}
			
			CommonsMail.enviaEmailFormatoHtml(emailComprador, nomeComprador, "Cotações pendentes!", textoHTML, txt);
			
			System.out.println("Tarefa executada.");
			
			agendarTarefa(tempo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
 }