package taskpilot;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.time.LocalDate;

public class InterfaceUI {

    public static Node criarCabecalho() {
        Label titulo = new Label("📋 TaskPilot - Gerenciador de Tarefas");
        titulo.setStyle("-fx-font-size: 20px; -fx-padding: 10px;");
        return titulo;
    }

    public static Node criarPainelTarefas(GerenciadorDeTarefas gerenciador) {
        VBox layout = new VBox(15);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_LEFT);

        // Campos do formulário
        TextField nomeField = new TextField();
        nomeField.setPromptText("Nome da tarefa");

        TextField descricaoField = new TextField();
        descricaoField.setPromptText("Descrição");

        Spinner<Integer> prioridadeSpinner = new Spinner<>(1, 10, 5);
        prioridadeSpinner.setEditable(true);

        TextField categoriaField = new TextField();
        categoriaField.setPromptText("Categoria");

        DatePicker dataPicker = new DatePicker();
        dataPicker.setPromptText("Data (opcional)");

        Button adicionarBtn = new Button("Adicionar Tarefa");
        VBox listaTarefasBox = new VBox(5);

        // Ação do botão
        adicionarBtn.setOnAction(e -> {
            String nome = nomeField.getText();
            String desc = descricaoField.getText();
            int prioridade = prioridadeSpinner.getValue();
            String categoria = categoriaField.getText();
            LocalDate data = dataPicker.getValue() != null ? dataPicker.getValue() : LocalDate.now();

            if (nome.isEmpty() || desc.isEmpty() || categoria.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Preencha todos os campos obrigatórios.");
                alert.show();
                return;
            }

            Tarefa nova = new Tarefa(nome, desc, prioridade, categoria, data);
            gerenciador.adicionarTarefa(nova);

            Label tarefaLabel = new Label(nova.toString());
            listaTarefasBox.getChildren().add(tarefaLabel);

            nomeField.clear();
            descricaoField.clear();
            categoriaField.clear();
            dataPicker.setValue(null);
        });

        // Layout do formulário
        VBox form = new VBox(10, nomeField, descricaoField, prioridadeSpinner, categoriaField, dataPicker, adicionarBtn);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10;");
        form.setMaxWidth(400);

        layout.getChildren().addAll(form, new Label("📄 Tarefas adicionadas:"), listaTarefasBox);
        return layout;
    }
}
