package br.com.appvagasvan.application.dto;

import br.com.appvagasvan.domain.turno.TipoTurno;

public class CriarTurnoInput {
    private final TipoTurno tipoTurno;
    private final String horario;
    private final int capacidade;
    private final String horarioLembrete;
    private final Integer motoristaId;

    public CriarTurnoInput(TipoTurno tipoTurno, String horario, int capacidade, String horarioLembrete, Integer motoristaId) {
        this.tipoTurno = tipoTurno;
        this.horario = horario;
        this.capacidade = capacidade;
        this.horarioLembrete = horarioLembrete;
        this.motoristaId = motoristaId;
    }

    public TipoTurno getTipoTurno() {
        return tipoTurno;
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
