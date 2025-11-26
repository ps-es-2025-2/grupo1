package br.com.appvagasvan;

import br.com.appvagasvan.infrastructure.di.ServiceLocator;
import br.com.appvagasvan.presentation.controller.DriverDashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Classe principal da aplicação JavaFX com DDD
 * Responsável por inicializar o ServiceLocator e carregar a tela inicial
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Inicializar dados de teste
        inicializarDadosTeste();

        // Carregar FXML
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/br/com/appvagasvan/view/DriverDashboard.fxml")
        );

        // Configurar factory do controller com injeção de dependências
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == DriverDashboardController.class) {
                DriverDashboardController controller = new DriverDashboardController();
                
                // Injetar use cases via ServiceLocator
                ServiceLocator locator = ServiceLocator.getInstance();
                controller.setVisualizarPassageirosUseCase(
                    locator.getVisualizarPassageirosConfirmadosUseCase()
                );
                controller.setSimularCorridaUseCase(
                    locator.getSimularCorridaUseCase()
                );
                
                return controller;
            }
            return null;
        });

        Parent root = loader.load();

        // Configurar a cena principal
        Scene scene = new Scene(root, 800, 600);

        // Configurar o palco (janela principal)
        primaryStage.setTitle("Painel do Motorista - App Vagas Van (DDD)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Inicializa dados de teste no repositório in-memory
     */
    private void inicializarDadosTeste() {
        DataInitializer.initialize();
    }

    @Override
    public void stop() {
        // Limpar recursos se necessário
        System.out.println("Aplicação encerrada");
    }

    public static void main(String[] args) {
        launch(args);
    }
}