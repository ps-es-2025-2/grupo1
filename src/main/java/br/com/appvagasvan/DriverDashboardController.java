
package br.com.appvagasvan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller para a tela DriverDashboard.fxml.
 * Contém a lógica de negócio para as ações do motorista.
 */
public class DriverDashboardController implements Initializable {

    @FXML
    private TabPane turnosTabPane;

    @FXML
    private ListView<String> manhaListView;

    @FXML
    private ListView<String> noiteListView;

    @FXML
    private Button btnOtimizarRota;

    @FXML
    private Button btnSimularCorrida;

    @FXML
    private Button btnGerenciarPassageiros;

    @FXML
    private Button btnGerenciarTurnos;

    /**
     * Método chamado para inicializar o controller após o FXML ser carregado.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Preenche as listas com dados de exemplo
        ObservableList<String> passageirosManha = FXCollections.observableArrayList(
            "Laura Martins", "Gabriel Rodrigues", "Tallya Jesus"
        );
        manhaListView.setItems(passageirosManha);

        ObservableList<String> passageirosNoite = FXCollections.observableArrayList(
            "Gabriel Freitas", "Léia Santos"
        );
        noiteListView.setItems(passageirosNoite);
    }

    @FXML
    private void handleOtimizarRota(ActionEvent event) {
        System.out.println("Ação: Visualizar Ordem de Coleta");
        // Lógica para otimizar a rota e exibir a ordem de coleta será implementada aqui.
    }

    @FXML
    private void handleSimularCorrida(ActionEvent event) {
        System.out.println("Ação: Simular Corrida");
        // Lógica para simular a corrida com a rota otimizada.
    }

    @FXML
    private void handleGerenciarPassageiros(ActionEvent event) {
        System.out.println("Ação: Gerenciar Passageiros");
        // Lógica para abrir uma nova janela de gerenciamento de passageiros.
    }

    @FXML
    private void handleGerenciarTurnos(ActionEvent event) {
        System.out.println("Ação: Gerenciar Turnos");
        // Lógica para abrir uma nova janela de gerenciamento de turnos.
    }
}
