package br.com.appvagasvan.application.dto;

public class CriarPassageiroInput {
    private final String nome;
    private final String endereco;
    private final String telefone;

    public CriarPassageiroInput(String nome, String endereco, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
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