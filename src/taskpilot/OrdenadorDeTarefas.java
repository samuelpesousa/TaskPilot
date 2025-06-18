package taskpilot;

import java.util.List;

public interface OrdenadorDeTarefas {
    List<Tarefa> ordenar(List<Tarefa> tarefas);
}