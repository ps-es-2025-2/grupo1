
package br.com.appvagasvan.presentation.viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SimulacaoViewModel {
    private final StringProperty nomeTurno;
    private final DoubleProperty distanciaKm;
    private final StringProperty tempoFormatado;
    private final ObservableList<PassageiroViewModel> ordemColeta;

    public SimulacaoViewModel() {
        this.nomeTurno = new SimpleStringProperty("");
        this.distanciaKm = new SimpleDoubleProperty(0.0);
        this.tempoFormatado = new SimpleStringProperty("");
        this.ordemColeta = FXCollections.observableArrayList();
    }

    // Properties
    public StringProperty nomeTurnoProperty() {
        return nomeTurno;
    }

    public DoubleProperty distanciaKmProperty() {
        return distanciaKm;
    }

    public StringProperty tempoFormatadoProperty() {
        return tempoFormatado;
    }

    public ObservableList<PassageiroViewModel> getOrdemColeta() {
        return ordemColeta;
    }

    // Getters
    public String getNomeTurno() {
        return nomeTurno.get();
    }

    public double getDistanciaKm() {
        return distanciaKm.get();
    }

    public String getTempoFormatado() {
        return tempoFormatado.get();
    }

    // Setters
    public void setNomeTurno(String nome) {
        this.nomeTurno.set(nome);
    }

    public void setDistanciaKm(double distancia) {
        this.distanciaKm.set(distancia);
    }

    public void setTempoFormatado(String tempo) {
        this.tempoFormatado.set(tempo);
    }

    public void setOrdemColeta(ObservableList<PassageiroViewModel> ordem) {
        this.ordemColeta.clear();
        this.ordemColeta.addAll(ordem);
    }
}