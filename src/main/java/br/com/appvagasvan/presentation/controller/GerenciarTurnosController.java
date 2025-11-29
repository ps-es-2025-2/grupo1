package br.com.appvagasvan.presentation.controller;

import br.com.appvagasvan.application.dto.CriarTurnoInput;
import br.com.appvagasvan.application.dto.ListaTurnosOutput;
import br.com.appvagasvan.application.dto.RemoverTurnoInput;
import br.com.appvagasvan.application.usecase.CriarTurnoUseCase;
import br.com.appvagasvan.application.usecase.RemoverTurnoUseCase;
import br.com.appvagasvan.application.usecase.VisualizarTurnosUseCase;
import br.com.appvagasvan.domain.exception.DomainException;
import br.com.appvagasvan.domain.turno.Horario;
import br.com.appvagasvan.domain.turno.TipoTurno;
import br.com.appvagasvan.presentation.viewmodel.TurnoViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class GerenciarTurnosController implements Initializable {

    // Use Cases
    private CriarTurnoUseCase criarTurnoUseCase;
    private VisualizarTurnosUseCase visualizarTurnosUseCase;
    private RemoverTurnoUseCase removerTurnoUseCase;

    // FXML Components
    @FXML
    private TableView<TurnoViewModel> turnosTableView;
    @FXML
    private TableColumn<TurnoViewModel, Horario> horarioColumn;
    @FXML
    private TableColumn<TurnoViewModel, TipoTurno> tipoTurnoColumn;
    @FXML
    private TableColumn<TurnoViewModel, Integer> vagasColumn;
    @FXML
    private TextField horarioTextField;
    @FXML
    private ComboBox<TipoTurno> tipoTurnoComboBox;
    @FXML
    private TextField vagasTextField;
    @FXML
    private TextField motoristaIdTextField;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnSalvar;

    private final ObservableList<TurnoViewModel> turnos = FXCollections.observableArrayList();

    public GerenciarTurnosController(CriarTurnoUseCase criarTurnoUseCase, VisualizarTurnosUseCase visualizarTurnosUseCase, RemoverTurnoUseCase removerTurnoUseCase) {
        this.criarTurnoUseCase = criarTurnoUseCase;
        this.visualizarTurnosUseCase = visualizarTurnosUseCase;
        this.removerTurnoUseCase = removerTurnoUseCase;
    }

    public GerenciarTurnosController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        horarioColumn.setCellValueFactory(new PropertyValueFactory<>("horario"));
        tipoTurnoColumn.setCellValueFactory(new PropertyValueFactory<>("tipoTurno"));
        vagasColumn.setCellValueFactory(new PropertyValueFactory<>("vagasDisponiveis"));
        turnosTableView.setItems(turnos);

        tipoTurnoComboBox.setItems(FXCollections.observableArrayList(TipoTurno.values()));

        carregarTurnos();

        turnosTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                horarioTextField.setText(newSelection.getHorario().toString());
                tipoTurnoComboBox.setValue(newSelection.getTipoTurno());
                vagasTextField.setText(String.valueOf(newSelection.getVagasDisponiveis()));
            }
        });
    }

    private void carregarTurnos() {
        turnos.clear();
        try {
            ListaTurnosOutput output = visualizarTurnosUseCase.execute();
            turnos.addAll(output.getTurnos().stream()
                    .map(t -> new TurnoViewModel(t.getId(), t.getHorario(), t.getTipoTurno(), t.getVagasDisponiveis()))
                    .collect(Collectors.toList()));
        } catch (Exception e) {
            exibirErro("Erro ao carregar turnos", e.getMessage());
        }
    }

    @FXML
    private void handleSalvar(ActionEvent event) {
        try {
            Horario horario = Horario.of(horarioTextField.getText());
            TipoTurno tipoTurno = tipoTurnoComboBox.getValue();
            int vagas = Integer.parseInt(vagasTextField.getText());
            int motoristaId = Integer.parseInt(motoristaIdTextField.getText());

            CriarTurnoInput input = new CriarTurnoInput(tipoTurno, horario.getHoraFormatada(), vagas, null, motoristaId);
            criarTurnoUseCase.execute(input);
            carregarTurnos();
            limparCampos();

        } catch (NumberFormatException e) {
            exibirErro("Erro de formato", "Vagas e ID do Motorista devem ser números.");
        } catch (DomainException e) {
            exibirErro("Erro ao salvar turno", e.getMessage());
        }
    }

    @FXML
    private void handleRemover(ActionEvent event) {
        TurnoViewModel selecionado = turnosTableView.getSelectionModel().getSelectedItem();
        if (selecionado == null) {
            exibirErro("Nenhum turno selecionado", "Selecione um turno na tabela para remover.");
            return;
        }

        try {
            RemoverTurnoInput input = new RemoverTurnoInput(selecionado.getId());
            removerTurnoUseCase.execute(input);
            carregarTurnos();
            limparCampos();
        } catch (DomainException e) {
            exibirErro("Erro ao remover turno", e.getMessage());
        }
    }

    private void limparCampos() {
        horarioTextField.clear();
        tipoTurnoComboBox.getSelectionModel().clearSelection();
        vagasTextField.clear();
        motoristaIdTextField.clear();
        turnosTableView.getSelectionModel().clearSelection();
    }

    private void exibirErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    // Setters for DI
    public void setCriarTurnoUseCase(CriarTurnoUseCase criarTurnoUseCase) {
        this.criarTurnoUseCase = criarTurnoUseCase;
    }

    public void setVisualizarTurnosUseCase(VisualizarTurnosUseCase visualizarTurnosUseCase) {
        this.visualizarTurnosUseCase = visualizarTurnosUseCase;
    }

    public void setRemoverTurnoUseCase(RemoverTurnoUseCase removerTurnoUseCase) {
        this.removerTurnoUseCase = removerTurnoUseCase;
    }
}
