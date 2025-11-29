package br.com.appvagasvan.presentation.controller;

import br.com.appvagasvan.application.dto.*;
import br.com.appvagasvan.application.usecase.SimularCorridaUseCase;
import br.com.appvagasvan.application.usecase.VisualizarPassageirosConfirmadosUseCase;
import br.com.appvagasvan.domain.exception.DomainException;
import br.com.appvagasvan.infrastructure.di.ServiceLocator;
import br.com.appvagasvan.presentation.controller.GerenciarPassageirosController;
import br.com.appvagasvan.presentation.controller.GerenciarTurnosController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller DDD para a tela DriverDashboard.fxml
 * Usa Use Cases e converte DTOs para ViewModels
 */
public class DriverDashboardController implements Initializable {

    // Use Cases injetados
    private VisualizarPassageirosConfirmadosUseCase visualizarPassageirosUseCase;
    private SimularCorridaUseCase simularCorridaUseCase;
    // Outros use cases seriam injetados aqui

    // Componentes FXML
    @FXML
    private TabPane turnosTabPane;

    @FXML
    private ListView<String> manhaListView;

    @FXML
    private ListView<String> noiteListView;

    @FXML
    private ListView<String> tardeListView;

    @FXML
    private Button btnOtimizarRota;
    
    // IDs dos turnos (idealmente viriam de uma configuração ou seleção)
    private static final int TURNO_MANHA_ID = 1;
    private static final int TURNO_NOITE_ID = 2;
    private static final int TURNO_TARDE_ID = 3;

    /**
     * Construtor com injeção de dependências
     */
    public DriverDashboardController(
            VisualizarPassageirosConfirmadosUseCase visualizarPassageirosUseCase,
            SimularCorridaUseCase simularCorridaUseCase) {
        this.visualizarPassageirosUseCase = visualizarPassageirosUseCase;
        this.simularCorridaUseCase = simularCorridaUseCase;
    }

    /**
     * Construtor padrão para o FXMLLoader
     */
    public DriverDashboardController() {
        // Use cases serão injetados via setters ou factory
    }

    /**
     * Setters para injeção de Use Cases
     */
    public void setVisualizarPassageirosUseCase(VisualizarPassageirosConfirmadosUseCase useCase) {
        this.visualizarPassageirosUseCase = useCase;
    }

    public void setSimularCorridaUseCase(SimularCorridaUseCase useCase) {
        this.simularCorridaUseCase = useCase;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        carregarPassageiros();
    }

    /**
     * Carrega passageiros confirmados usando Use Cases
     */
    private void carregarPassageiros() {
        try {
            // Carregar passageiros do turno da manhã
            carregarPassageirosTurno(TURNO_MANHA_ID, manhaListView);

            // Carregar passageiros do turno da noite
            carregarPassageirosTurno(TURNO_NOITE_ID, noiteListView);

            // Carregar passageiros do turno da tarde
            carregarPassageirosTurno(TURNO_TARDE_ID, tardeListView);

        } catch (DomainException e) {
            exibirErro("Erro ao carregar passageiros", e.getMessage());
        } catch (Exception e) {
            exibirErro("Erro inesperado", "Ocorreu um erro ao carregar os dados.");
        }
    }

    /**
     * Carrega passageiros de um turno específico
     */
    private void carregarPassageirosTurno(int turnoId, ListView<String> listView) {
        if (visualizarPassageirosUseCase == null) {
            // Fallback com dados mockados se use case não estiver injetado
            carregarDadosMockados(listView, turnoId);
            return;
        }

        try {
            ListaPassageirosConfirmadosOutput output = 
                visualizarPassageirosUseCase.execute(turnoId);

            ObservableList<String> nomes = FXCollections.observableArrayList();
            for (PassageiroOutput p : output.getPassageirosConfirmados()) {
                nomes.add(p.getNome());
            }

            listView.setItems(nomes);

        } catch (DomainException e) {
            // Turno não encontrado ou sem passageiros
            listView.setItems(FXCollections.observableArrayList());
        }
    }

    /**
     * Dados mockados para desenvolvimento sem injeção completa
     */
    private void carregarDadosMockados(ListView<String> listView, int turnoId) {
        if (turnoId == TURNO_MANHA_ID) {
            listView.setItems(FXCollections.observableArrayList(
                "Laura Martins", "Gabriel Rodrigues", "Tallya Jesus"
            ));
        } else if (turnoId == TURNO_NOITE_ID) {
            listView.setItems(FXCollections.observableArrayList(
                "Gabriel Freitas", "Léia Santos"
            ));
        } else {
            listView.setItems(FXCollections.observableArrayList(
                "Carlos Pereira"
            ));
        }
    }

    @FXML
    private void handleOtimizarRota(ActionEvent event) {
        System.out.println("Ação: Visualizar Ordem de Coleta");
        
        try {
            int turnoSelecionado = obterTurnoSelecionado();
            
            if (simularCorridaUseCase != null) {
                SimularCorridaInput input = new SimularCorridaInput(turnoSelecionado);
                SimulacaoCorridaOutput output = simularCorridaUseCase.execute(input);
                
                exibirOrdemOtimizada(output);
            } else {
                exibirInformacao("Funcionalidade em desenvolvimento", 
                    "A otimização de rota será implementada em breve.");
            }
            
        } catch (DomainException e) {
            exibirErro("Erro ao otimizar rota", e.getMessage());
        } catch (Exception e) {
            exibirErro("Erro inesperado", "Não foi possível calcular a rota.");
        }
    }

    @FXML
    private void handleSimularCorrida(ActionEvent event) {
        System.out.println("Ação: Simular Corrida");
        
        try {
            int turnoSelecionado = obterTurnoSelecionado();
            
            if (simularCorridaUseCase != null) {
                SimularCorridaInput input = new SimularCorridaInput(turnoSelecionado);
                SimulacaoCorridaOutput output = simularCorridaUseCase.execute(input);
                
                exibirResultadoSimulacao(output);
            } else {
                exibirInformacao("Funcionalidade em desenvolvimento",
                    "A simulação de corrida será implementada em breve.");
            }
            
        } catch (DomainException e) {
            exibirErro("Erro ao simular corrida", e.getMessage());
        } catch (Exception e) {
            exibirErro("Erro inesperado", "Não foi possível simular a corrida.");
        }
    }

    @FXML
    private void handleGerenciarPassageiros(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/appvagasvan/view/GerenciarPassageiros.fxml"));
            
            // Configurar factory do controller com injeção de dependências
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == GerenciarPassageirosController.class) {
                    GerenciarPassageirosController controller = new GerenciarPassageirosController();
                    
                    // Injetar use cases via ServiceLocator
                    ServiceLocator locator = ServiceLocator.getInstance();
                    controller.setCriarPassageiroUseCase(locator.getCriarPassageiroUseCase());
                    controller.setVisualizarPassageirosUseCase(locator.getVisualizarPassageirosUseCase());
                    controller.setRemoverPassageiroUseCase(locator.getRemoverPassageiroUseCase());
                    controller.setAdicionarPassageiroAoTurnoUseCase(locator.getAdicionarPassageiroAoTurnoUseCase());
                    controller.setVisualizarTurnosUseCase(locator.getVisualizarTurnosUseCase());
                    
                    return controller;
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gerenciar Passageiros");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao abrir tela", "Não foi possível carregar a tela de gerenciamento de passageiros.");
        }
    }

    @FXML
    private void handleGerenciarTurnos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/br/com/appvagasvan/view/GerenciarTurnos.fxml"));
            
            // Configurar factory do controller com injeção de dependências
            loader.setControllerFactory(controllerClass -> {
                if (controllerClass == GerenciarTurnosController.class) {
                    GerenciarTurnosController controller = new GerenciarTurnosController();
                    
                    // Injetar use cases via ServiceLocator
                    ServiceLocator locator = ServiceLocator.getInstance();
                    controller.setCriarTurnoUseCase(locator.getCriarTurnoUseCase());
                    controller.setVisualizarTurnosUseCase(locator.getVisualizarTurnosUseCase());
                    controller.setRemoverTurnoUseCase(locator.getRemoverTurnoUseCase());
                    
                    return controller;
                }
                try {
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Gerenciar Turnos");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            exibirErro("Erro ao abrir tela", "Não foi possível carregar a tela de gerenciamento de turnos.");
        }
    }

    /**
     * Obtém o ID do turno atualmente selecionado
     */
    private int obterTurnoSelecionado() {
        Tab tabSelecionada = turnosTabPane.getSelectionModel().getSelectedItem();
        if (tabSelecionada.getText().contains("Manhã")) {
            return TURNO_MANHA_ID;
        } else if (tabSelecionada.getText().contains("Noite")) {
            return TURNO_NOITE_ID;
        } else {
            return TURNO_TARDE_ID;
        }
    }

    /**
     * Exibe a ordem otimizada em um dialog
     */
    private void exibirOrdemOtimizada(SimulacaoCorridaOutput output) {
        StringBuilder mensagem = new StringBuilder();
        mensagem.append("Ordem de coleta otimizada:\n\n");
        
        int posicao = 1;
        for (PassageiroOutput p : output.getOrdemColeta()) {
            mensagem.append(posicao++).append(". ")
                    .append(p.getNome())
                    .append(" - ").append(p.getEndereco())
                    .append("\n");
        }
        
        mensagem.append("\nDistância total: ").append(String.format("%.2f km", output.getDistanciaKm()));
        mensagem.append("\nTempo estimado: ").append(output.getTempoFormatado());
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ordem Otimizada de Coleta");
        alert.setHeaderText("Turno: " + output.getTipoTurno().toString());
        alert.setContentText(mensagem.toString());
        alert.showAndWait();
    }

    /**
     * Exibe o resultado da simulação
     */
    private void exibirResultadoSimulacao(SimulacaoCorridaOutput output) {
        exibirOrdemOtimizada(output); // Por enquanto, mesma visualização
    }

    /**
     * Exibe mensagem de erro
     */
    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    /**
     * Exibe mensagem informativa
     */
    private void exibirInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}