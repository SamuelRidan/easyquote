package teste;

public class AgendaTarefa {
	
    public static void main(String args[]) {
    	
    	long intervaloDeTempoEntreExecucoes = 86400, numeroDeExecucoes = 10; // em segundos
    	
    	TarefaTemporizada tarefa = new TarefaTemporizada(intervaloDeTempoEntreExecucoes, numeroDeExecucoes);
    	
        System.out.println("Tarefa agendada para cada " + intervaloDeTempoEntreExecucoes 
        					+ " segundos, " + numeroDeExecucoes + " repetições.");
    	
    	tarefa.agendarTarefa(5);    	
    }
}