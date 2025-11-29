package br.com.appvagasvan.presentation.controller;

import br.com.appvagasvan.application.dto.*;
import br.com.appvagasvan.application.usecase.*;
import br.com.appvagasvan.domain.exception.DomainException;
import br.com.appvagasvan.presentation.viewmodel.PassageiroViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.StringConverter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GerenciarPassageirosController implements Initializable {

    // Use Cases
    private CriarPassageiroUseCase criarPassageiroUseCase;
    private VisualizarPassageirosUseCase visualizarPassageirosUseCase;
    private RemoverPassageiroUseCase removerPassageiroUseCase;
    private AdicionarPassageiroAoTurnoUseCase adicionarPassageiroAoTurnoUseCase;
    private VisualizarTurnosUseCase visualizarTurnosUseCase;

    // FXML Components
    @FXML
    private TableView<PassageiroViewModel> passageirosTableView;
    @FXML
    private TableColumn<PassageiroViewModel, String> nomeColumn;
    @FXML
    private TableColumn<PassageiroViewModel, String> enderecoColumn;
    @FXML
    private TableColumn<PassageiroViewModel, String> telefoneColumn;
    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField enderecoTextField;
    @FXML
    private TextField telefoneTextField;
    @FXML
    private ComboBox<TurnoOutput> turnoComboBox;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnAdicionarAoTurno;

    private final ObservableList<PassageiroViewModel> passageiros = FXCollections.observableArrayList();

    public GerenciarPassageirosController(CriarPassageiroUseCase criarPassageiroUseCase, VisualizarPassageirosUseCase visualizarPassageirosUseCase, RemoverPassageiroUseCase removerPassageiroUseCase, AdicionarPassageiroAoTurnoUseCase adicionarPassageiroAoTurnoUseCase, VisualizarTurnosUseCase visualizarTurnosUseCase) {
        this.criarPassageiroUseCase = criarPassageiroUseCase;
        this.visualizarPassageirosUseCase = visualizarPassageirosUseCase;
        this.removerPassageiroUseCase = removerPassageiroUseCase;
        this.adicionarPassageiroAoTurnoUseCase = adicionarPassageiroAoTurnoUseCase;
        this.visualizarTurnosUseCase = visualizarTurnosUseCase;
    }

    public GerenciarPassageirosController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        enderecoColumn.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        passageirosTableView.setItems(passageiros);
        
        carregarPassageiros();
        carregarTurnos();

        passageirosTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nomeTextField.setText(newSelection.getNome());
                enderecoTextField.setText(newSelection.getEndereco());
                telefoneTextField.setText(newSelection.getTelefone());
            }
        });
    }

    private void carregarPassageiros() {
        passageiros.clear();
        try {
            ListaPassageirosOutput output = visualizarPassageirosUseCase.execute();
            passageiros.addAll(output.getPassageiros().stream()
                    .map(p -> new PassageiroViewModel(p.getId(), p.getNome(), p.getEndereco(), p.getTelefone()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            exibirErro("Erro ao carregar passageiros", e.getMessage());
        }
    }
    
    private void carregarTurnos() {
        try {
            ListaTurnosOutput output = visualizarTurnosUseCase.execute();
            turnoComboBox.setItems(FXCollections.observableArrayList(output.getTurnos()));
            turnoComboBox.setConverter(new StringConverter<TurnoOutput>() {
                @Override
                public String toString(TurnoOutput turno) {
                    return turno == null ? "" : turno.getTipoTurno() + " - " + turno.getHorario();
                }

                @Override
                public TurnoOutput fromString(String string) {
                    return null;
                }
            });
        } catch (Exception e) {
            exibirErro("Erro ao carregar turnos", e.getMessage());
        }
    }

    @FXML
    private void handleSalvar(ActionEvent event) {
        String nome = nomeTextField.getText();
        String endereco = enderecoTextField.getText();
        String telefone = telefoneTextField.getText();

        if (nome.isEmpty() || endereco.isEmpty()) {
            exibirErro("Campos obrigatórios", "Nome e Endereço são obrigatórios.");
            return;
        }

        try {
            CriarPassageiroInput input = new CriarPassageiroInput(nome, endereco, telefone);
            criarPassageiroUseCase.execute(input);
            carregarPassageiros();
            limparCampos();
        } catch (DomainException e) {
            exibirErro("Erro ao salvar passageiro", e.getMessage());
        }
    }

    @FXML
    private void handleRemover(ActionEvent event) {
        PassageiroViewModel selecionado = passageirosTableView.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            exibirErro("Nenhum passageiro selecionado", "Selecione um passageiro na tabela para remover.");
            return;
        }

        try {
            RemoverPassageiroInput input = new RemoverPassageiroInput(selecionado.getId());
            removerPassageiroUseCase.execute(input);
            carregarPassageiros();
            limparCampos();
        } catch (DomainException e) {
            exibirErro("Erro ao remover passageiro", e.getMessage());
        }
    }

    @FXML
    private void handleAdicionarAoTurno(ActionEvent event) {
        PassageiroViewModel passageiroSelecionado = passageirosTableView.getSelectionModel().getSelectedItem();
        TurnoOutput turnoSelecionado = turnoComboBox.getSelectionModel().getSelectedItem();

        if (passageiroSelecionado == null || turnoSelecionado == null) {
            exibirErro("Seleção necessária", "Selecione um passageiro e um turno.");
            return;
        }

        try {
            AdicionarPassageiroAoTurnoInput input = new AdicionarPassageiroAoTurnoInput(turnoSelecionado.getId(), passageiroSelecionado.getId());
            adicionarPassageiroAoTurnoUseCase.execute(input);
            exibirInformacao("Sucesso", "Passageiro adicionado ao turno com sucesso!");
        } catch (DomainException e) {
            exibirErro("Erro ao adicionar passageiro ao turno", e.getMessage());
        }
    }

    private void limparCampos() {
        nomeTextField.clear();
        enderecoTextField.clear();
        telefoneTextField.clear();
        passageirosTableView.getSelectionModel().clearSelection();
    }
    
    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirInformacao(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Setters for DI
    public void setCriarPassageiroUseCase(CriarPassageiroUseCase criarPassageiroUseCase) {
        this.criarPassageiroUseCase = criarPassageiroUseCase;
    }

    public void setVisualizarPassageirosUseCase(VisualizarPassageirosUseCase visualizarPassageirosUseCase) {
        this.visualizarPassageirosUseCase = visualizarPassageirosUseCase;
    }

    public void setRemoverPassageiroUseCase(RemoverPassageiroUseCase removerPassageiroUseCase) {
        this.removerPassageiroUseCase = removerPassageiroUseCase;
    }

    public void setAdicionarPassageiroAoTurnoUseCase(AdicionarPassageiroAoTurnoUseCase adicionarPassageiroAoTurnoUseCase) {
        this.adicionarPassageiroAoTurnoUseCase = adicionarPassageiroAoTurnoUseCase;
    }

    public void setVisualizarTurnosUseCase(VisualizarTurnosUseCase visualizarTurnosUseCase) {
        this.visualizarTurnosUseCase = visualizarTurnosUseCase;
    }
}
