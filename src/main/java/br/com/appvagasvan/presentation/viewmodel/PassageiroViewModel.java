package br.com.appvagasvan.presentation.viewmodel;

import javafx.beans.property.*;

/**
 * ViewModel JavaFX para Passageiro
 * Esta classe é específica da camada de apresentação e usa Properties do JavaFX
 */
public class PassageiroViewModel {
    private final IntegerProperty id;
    private final StringProperty nome;
    private final StringProperty endereco;
    private final StringProperty telefone;
    private final BooleanProperty confirmado;

    public PassageiroViewModel(int id, String nome, String endereco, String telefone, boolean confirmado) {
        this.id = new SimpleIntegerProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.endereco = new SimpleStringProperty(endereco);
        this.telefone = new SimpleStringProperty(telefone);
        this.confirmado = new SimpleBooleanProperty(confirmado);
    }

    // Properties para binding
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public BooleanProperty confirmadoProperty() {
        return confirmado;
    }

    // Getters
    public int getId() {
        return id.get();
    }

    public String getNome() {
        return nome.get();
    }

    public String getEndereco() {
        return endereco.get();
    }

    public String getTelefone() {
        return telefone.get();
    }

    public boolean isConfirmado() {
        return confirmado.get();
    }

    // Setters
    public void setId(int id) {
        this.id.set(id);
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public void setEndereco(String endereco) {
        this.endereco.set(endereco);
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado.set(confirmado);
    }

    @Override
    public String toString() {
        return getNome();
    }
}