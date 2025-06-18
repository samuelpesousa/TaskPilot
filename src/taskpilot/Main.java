package taskpilot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();
        
        VBox vbox = new VBox();
        Button adicionarButton = new Button("Adicionar Tarefa");
        vbox.getChildren().add(adicionarButton);
        
        adicionarButton.setOnAction(event -> {
            Tarefa novaTarefa = new Tarefa("Estudar Matemática", "Estudar para a prova", 1, "Estudo", LocalDate.now());
            gerenciador.adicionarTarefa(novaTarefa);
            System.out.println("Tarefa adicionada: " + novaTarefa);
        });
        
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gerenciador de Tarefas");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
