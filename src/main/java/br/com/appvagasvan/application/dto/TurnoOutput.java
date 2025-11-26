package br.com.appvagasvan.application.dto;

public class TurnoOutput {
    private final int id;
    private final String nome;
    private final String horario;
    private final int capacidade;
    private final int vagasDisponiveis;
    private final String horarioLembrete;
    private final int totalAssociados;
    private final int totalConfirmados;

    public TurnoOutput(int id, String nome, String horario, int capacidade, 
                       int vagasDisponiveis, String horarioLembrete,
                       int totalAssociados, int totalConfirmados) {
        this.id = id;
        this.nome = nome;
        this.horario = horario;
        this.capacidade = capacidade;
        this.vagasDisponiveis = vagasDisponiveis;
        this.horarioLembrete = horarioLembrete;
        this.totalAssociados = totalAssociados;
        this.totalConfirmados = totalConfirmados;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getHorario() { return horario; }
    public int getCapacidade() { return capacidade; }
    public int getVagasDisponiveis() { return vagasDisponiveis; }
    public String getHorarioLembrete() { return horarioLembrete; }
    public int getTotalAssociados() { return totalAssociados; }
    public int getTotalConfirmados() { return totalConfirmados; }
}