package br.com.appvagasvan.presentation.viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TurnoViewModel {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty horario;
    private final IntegerProperty capacidade;
    private final IntegerProperty vagasDisponiveis;
    private final StringProperty horarioLembrete;
    private final ObservableList<PassageiroViewModel> passageirosConfirmados;

    public TurnoViewModel(int id, String nome, String horario, int capacidade,
                          int vagasDisponiveis, String horarioLembrete) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.horario = new SimpleStringProperty(horario);
        this.capacidade = new SimpleIntegerProperty(capacidade);
        this.vagasDisponiveis = new SimpleIntegerProperty(vagasDisponiveis);
        this.horarioLembrete = new SimpleStringProperty(horarioLembrete);
        this.passageirosConfirmados = FXCollections.observableArrayList();
    }

    // Properties
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public IntegerProperty capacidadeProperty() {
        return capacidade;
    }

    public IntegerProperty vagasDisponiveisProperty() {
        return vagasDisponiveis;
    }

    public StringProperty horarioLembreteProperty() {
        return horarioLembrete;
    }

    public ObservableList<PassageiroViewModel> getPassageirosConfirmados() {
        return passageirosConfirmados;
    }

    // Getters
    public int getId() {
        return id.get();
    }

    public String getNome() {
        return nome.get();
    }

    public String getHorario() {
        return horario.get();
    }

    public int getCapacidade() {
        return capacidade.get();
    }

    public int getVagasDisponiveis() {
        return vagasDisponiveis.get();
    }

    public String getHorarioLembrete() {
        return horarioLembrete.get();
    }

    // Setters
    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setHorario(String horario) {
        this.horario.set(horario);
    }

    public void setCapacidade(int capacidade) {
        this.capacidade.set(capacidade);
    }

    public void setVagasDisponiveis(int vagas) {
        this.vagasDisponiveis.set(vagas);
    }

    public void setHorarioLembrete(String horario) {
        this.horarioLembrete.set(horario);
    }
}
