package taskpilot;

public class Usuario {
    private String nome;
    private String email;
    private GerenciadorDeTarefas gerenciadorDeTarefas;
    
    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.gerenciadorDeTarefas = new GerenciadorDeTarefas();
    }

    public GerenciadorDeTarefas getGerenciadorDeTarefas() {
        return gerenciadorDeTarefas;
    }
    

}
