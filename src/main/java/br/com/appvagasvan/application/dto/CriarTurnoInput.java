package br.com.appvagasvan.application.dto;

public class CriarTurnoInput {
    private final String nome;
    private final String horario;
    private final int capacidade;
    private final String horarioLembrete;
    private final Integer motoristaId;

    public CriarTurnoInput(String nome, String horario, int capacidade, String horarioLembrete, Integer motoristaId) {
        this.nome = nome;
        this.horario = horario;
        this.capacidade = capacidade;
        this.horarioLembrete = horarioLembrete;
        this.motoristaId = motoristaId;
    }

    public String getNome() {
        return nome;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public String getHorarioLembrete() {
        return horarioLembrete;
    }

    public Integer getMotoristaId() {
        return motoristaId;
    }
}
