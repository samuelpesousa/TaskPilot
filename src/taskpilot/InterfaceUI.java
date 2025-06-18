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

        final Tarefa[] tarefaSendoEditada = {null};

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
            
            if (tarefaSendoEditada[0] == null) {
                Tarefa nova = new Tarefa(nome, desc, prioridade, categoria, data);
                gerenciador.adicionarTarefa(nova);
            } else {
                gerenciador.editarTarefa(tarefaSendoEditada[0], nome, desc, prioridade, categoria, data);
                tarefaSendoEditada[0] = null;
                adicionarBtn.setText("Adicionar Tarefa");
            }

            nomeField.clear();
            descricaoField.clear();
            categoriaField.clear();
            dataPicker.setValue(null);
            prioridadeSpinner.getValueFactory().setValue(5);
            atualizarListaTarefas(gerenciador, listaTarefasBox, nomeField, descricaoField, categoriaField, prioridadeSpinner, dataPicker, adicionarBtn, tarefaSendoEditada);
        });

        atualizarListaTarefas(gerenciador, listaTarefasBox, nomeField, descricaoField, categoriaField, prioridadeSpinner, dataPicker, adicionarBtn, tarefaSendoEditada);

        // Layout do formulário
        VBox form = new VBox(10, nomeField, descricaoField, prioridadeSpinner, categoriaField, dataPicker, adicionarBtn);
        form.setPadding(new Insets(10));
        form.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10;");
        form.setMaxWidth(400);

        layout.getChildren().addAll(form, new Label("📄 Tarefas adicionadas:"), listaTarefasBox);
        return layout;
    }
    private static void atualizarListaTarefas(GerenciadorDeTarefas gerenciador, VBox listaTarefasBox, TextField nomeField, TextField descricaoField, TextField categoriaField, Spinner<Integer> prioridadeSpinner, DatePicker dataPicker, Button adicionarBtn, final Tarefa[] tarefaSendoEditada) {
        listaTarefasBox.getChildren().clear();
        for (Tarefa t : gerenciador.listarPendentes()) {
            Label tarefaLabel = new Label(t.toString());
            Button editarBtn = new Button("Editar");
            
            editarBtn.setOnAction(ev -> {
                nomeField.setText(t.getNome());
                descricaoField.setText(t.getDescricao());
                categoriaField.setText(t.getCategoria());
                prioridadeSpinner.getValueFactory().setValue(t.getPrioridade());
                dataPicker.setValue(t.getData());
                
                tarefaSendoEditada[0] = t;
                adicionarBtn.setText("Salvar Edição");
            });
            HBox linha = new HBox(10, tarefaLabel, editarBtn);
            listaTarefasBox.getChildren().add(linha);
        }
    }
}

