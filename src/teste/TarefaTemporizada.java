package teste;

import java.text.SimpleDateFormat;
import java.util.*;

import scada.modelo.Cotacao;

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
		
		try {
			
			System.out.println("Executando tarefa... ");
			
			//Pegando dataAtual + 7 dias
			Date date = new Date();
			Calendar data = Calendar.getInstance();
			data.setTime(date);		
			data.add(Calendar.DAY_OF_MONTH, 7);	
			SimpleDateFormat dataFormatada = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
						
			System.out.println("--> Data de comparação: " + dataFormatada.format(data.getTime()));
			
			cotacoes = HibernateUtilTest.executarConsultaHQL("from Cotacao");
			
			for (Object obj: cotacoes) {
	            Cotacao c = (Cotacao)obj;
	            
	            System.out.println(c.getId());
	            
			}
			
			System.out.println("Tarefa executada.");
			
			agendarTarefa(tempo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
 }