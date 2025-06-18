package taskpilot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class GerenciadorDeTarefas {
    private List<Tarefa> tarefasPendentes = new ArrayList<>();
    private Queue<Tarefa> tarefasUrgentes = new LinkedList<>();
    private Stack<Tarefa> historicoConcluidas = new Stack<>();
    private OrdenadorDeTarefas estrategiaOrdenacao;

    public void adicionarTarefa(Tarefa t) {
        tarefasPendentes.add(t);
        if (t.getPrioridade() >= 8) {
			tarefasUrgentes.add(t);
		}
    }

    public void removerTarefa(Tarefa t) {
        tarefasPendentes.remove(t);
        tarefasUrgentes.remove(t);
    }

    public void concluirTarefa(Tarefa t) {
        t.concluir();
        tarefasPendentes.remove(t);
        tarefasUrgentes.remove(t);
        historicoConcluidas.push(t);
    }

    public List<Tarefa> listarPendentes() {
        if (estrategiaOrdenacao != null) {
            return estrategiaOrdenacao.ordenar(new ArrayList<>(tarefasPendentes));
        }
        return new ArrayList<>(tarefasPendentes);
    }

    public List<Tarefa> listarConcluidas() {
        return new ArrayList<>(historicoConcluidas);
    }

    public void setEstrategiaOrdenacao(OrdenadorDeTarefas estrategia) {
        this.estrategiaOrdenacao = estrategia;
    }

    public List<Tarefa> filtrarPorCategoria(String categoria) {
        return tarefasPendentes.stream()
            .filter(t -> t.getCategoria().equalsIgnoreCase(categoria))
            .collect(Collectors.toList());
    }

    public Queue<Tarefa> getTarefasUrgentes() {
        return new LinkedList<>(tarefasUrgentes);
    }
}
    public void editarTarefa(Tarefa tarefaOriginal, String novoNome, String novaDescricao, int novaPrioridade, String novaCategoria, LocalDate novaData) {
    tarefaOriginal.setNome(novoNome);
    tarefaOriginal.setDescricao(novaDescricao);
    tarefaOriginal.setPrioridade(novaPrioridade);
    tarefaOriginal.setCategoria(novaCategoria);
    tarefaOriginal.setData(novaData);

    tarefasUrgentes.remove(tarefaOriginal);
    if (novaPrioridade >= 8) {
        tarefasUrgentes.add(tarefaOriginal);
    }
}
