package teste;

public class AgendaTarefa {
	
    public static void main(String args[]) {
    	
    	long intervaloDeTempoEntreExecucoes = 5; // em segundos
    	
    	TarefaTemporizada tarefa = new TarefaTemporizada(intervaloDeTempoEntreExecucoes);
    	
        System.out.println("Tarefa agendada para cada " + intervaloDeTempoEntreExecucoes + " segundos.");
    	
    	tarefa.agendarTarefa(5);    	
    }
}