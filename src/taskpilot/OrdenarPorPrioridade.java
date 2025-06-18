package taskpilot;

import java.util.Comparator;
import java.util.List;

public class OrdenarPorPrioridade implements OrdenadorDeTarefas {
    @Override
	public List<Tarefa> ordenar(List<Tarefa> tarefas) {
        tarefas.sort(Comparator.comparingInt(Tarefa::getPrioridade).reversed());
        return tarefas;
    }
}
