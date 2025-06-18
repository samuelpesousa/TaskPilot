package taskpilot;

import java.time.LocalDate;

public class Tarefa {
    private String nome;
    private String descricao;
    private int prioridade;
    private String categoria;
    private LocalDate data;
    private boolean concluida;

    public Tarefa(String nome, String descricao, int prioridade, String categoria, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.categoria = categoria;
        this.data = data;
        this.concluida = false;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getPrioridade() { return prioridade; }
    public String getCategoria() { return categoria; }
    public LocalDate getData() { return data; }
    public boolean isConcluida() { return concluida; }

    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPrioridade(int prioridade) { this.prioridade = prioridade; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setData(LocalDate data) { this.data = data; }
    
    public void concluir() { this.concluida = true; }
    public void desfazerConclusao() { this.concluida = false; }

    @Override
    public String toString() {
        return nome + " (" + categoria + ") - Prioridade: " + prioridade + (concluida ? " [Concluída]" : "");
    }
}
