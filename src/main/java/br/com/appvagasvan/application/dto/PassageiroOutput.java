package br.com.appvagasvan.application.dto;

public class PassageiroOutput {
    private final int id;
    private final String nome;
    private final String endereco;
    private final String telefone;

    public PassageiroOutput(int id, String nome, String endereco, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
}
