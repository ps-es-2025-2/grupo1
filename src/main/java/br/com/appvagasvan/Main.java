
package br.com.appvagasvan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal que inicia a aplicação JavaFX.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Carrega o arquivo FXML da tela do motorista a partir da pasta resources
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/appvagasvan/view/DriverDashboard.fxml"));
        Parent root = loader.load();

        // Configura a cena principal
        Scene scene = new Scene(root, 800, 600);

        // Configura o palco (janela principal)
        primaryStage.setTitle("Painel do Motorista - App Vagas Van");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
