package taskpilot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        GerenciadorDeTarefas gerenciador = new GerenciadorDeTarefas();

        BorderPane root = new BorderPane();
        root.setTop(InterfaceUI.criarCabecalho());
        root.setCenter(InterfaceUI.criarPainelTarefas(gerenciador));

        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("TaskPilot - Gerenciador de Tarefas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
